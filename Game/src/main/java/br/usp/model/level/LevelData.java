/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.level;

import br.usp.model.SerializableObject;
import br.usp.model.entity.EntityMap;
import br.usp.model.entity.GameCharacter;
import br.usp.model.items.Item;
import br.usp.model.items.ItemMap;
import br.usp.model.map.MapRegion;
import br.usp.model.map.Tile;
import br.usp.model.map.TileMap;
import br.usp.model.map.TileType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Fernando
 */
public class LevelData implements SerializableObject {
    private int width;
    private int height;
    private Integer[][] tiles;
    private Integer[][] regionIds;
    private List<Map<String, Object>> doorMetadata;
    private List<Map<String, Object>> itemData;
    private List<Map<String, Object>> entityData;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Integer[][] getTiles() {
        return tiles;
    }

    public Integer[][] getRegionIds() {
        return regionIds;
    }
    
    public List<Map<String, Object>> getDoorMetadata() {
        return doorMetadata;
    }
    
    public List<Map<String, Object>> getItemData() {
        return itemData;
    }
    
    public List<Map<String, Object>> getEntityData() {
        return entityData;
    } 

    @Override
    public Map<String, Object> serialize() {
        throw new UnsupportedOperationException("Implementado serialização pela função generateLevelData().");
    }
    
    public static LevelData deserialize(Map<String, Object> data) {
        LevelData levelData = new LevelData();

        levelData.width = (int) data.get("width");
        levelData.height = (int) data.get("height");

        levelData.tiles = ((List<List<Integer>>) data.get("tiles"))
            .stream()
            .map(row -> row.toArray(new Integer[0]))
            .toArray(Integer[][]::new);

        levelData.regionIds = ((List<List<Integer>>) data.get("regionIds"))
            .stream()
            .map(row -> row.toArray(new Integer[0]))
            .toArray(Integer[][]::new);

        levelData.doorMetadata = (List<Map<String, Object>>) data.get("doorMetadata");
        levelData.itemData = (List<Map<String, Object>>) data.get("itemData");
        levelData.entityData = (List<Map<String, Object>>) data.get("entityData");

        return levelData;
    }
    
    public static LevelData generateLevelData(TileMap tileMap, EntityMap entities, ItemMap items, int width, int height) {
        LevelData data = new LevelData();
        
        data.width = width;
        data.height = height;

        // Serializa o grid de tiles
        Integer[][] tiles = new Integer[height][width];
        for (Tile tile : tileMap.getTiles()) {
            int x = (int) tile.getPosition().getX();
            int y = (int) tile.getPosition().getY();
            tiles[y][x] = switch(tile.getType() ) {
                case FLOOR -> 0;
                case WALL -> 1;
                case DOOR -> 2;
                default -> 0;
            };
        }
        data.tiles = tiles;
        

        // Serializa a matriz de regiões
        Integer[][] regionMatrix = new Integer[height][width];
        for (Tile tile : tileMap.getTiles()) {
            int x = (int) tile.getPosition().getX();
            int y = (int) tile.getPosition().getY();
            
            regionMatrix[y][x] = switch(tile.getId()) {
                case "fogless" -> 0;
                case "yellow" -> 1;
                case "red" -> 2;
                case "blue" -> 3;
                case "green" -> 4;
                default -> 0;
            };
        }

        data.regionIds = regionMatrix;
        
        // Serializa as portas (se tiver portaMetadata separado)
        List<Map<String, Object>> doorData = new ArrayList<>();
        for (Tile tile : tileMap.getTiles()) {
            if (tile.getType() == TileType.DOOR) {
                Map<String, Object> doorInfo = new HashMap<>();
                doorInfo.put("x", (int) tile.getPosition().getX());
                doorInfo.put("y", (int) tile.getPosition().getY());
                doorInfo.put("regionId", tile.getId());
                doorData.add(doorInfo);
            }
        }
        data.doorMetadata = doorData;

        // Serializa os itens
        List<Map<String, Object>> itemData = new ArrayList<>();
        for (Item item : items.getItems()) {
            if (item instanceof SerializableObject serializable) {
                itemData.add(serializable.serialize());
            }
        }
        data.itemData = itemData;

        // Serializa as entidades (herói, inimigos)
        List<Map<String, Object>> entityData = new ArrayList<>();
        for (GameCharacter entity : entities.getEntities()) {
            if (entity instanceof SerializableObject serializable) {
                entityData.add(serializable.serialize());
            }
        }
        data.entityData = entityData;

        return data;
    }
}
