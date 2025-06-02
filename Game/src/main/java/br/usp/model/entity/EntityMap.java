/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.entity;

import br.usp.model.level.LevelData;
import br.usp.model.map.MapRegion;
import br.usp.model.map.MapRegionManager;
import br.usp.model.map.Tile;
import br.usp.model.map.TileMap;
import br.usp.model.map.TileType;
import java.util.ArrayList;
import java.util.Map;
import javax.vecmath.Point2d;

/**
 *
 * @author Fernando
 */
public class EntityMap {
    private final ArrayList<GameCharacter> gameEntities;

    public EntityMap() {
        this.gameEntities = new ArrayList<>();
    }

    public void loadFromData(LevelData data, TileMap map, MapRegionManager regionManager) {
        gameEntities.clear();
        
        if(data.getEntityData()!= null) {
            for (Map<String, Object> entityData : data.getEntityData()) {
                GameCharacter currentEntity = EntityFactory.createEntity(entityData);
                
                Point2d pos = currentEntity.getPosition();
                
                Tile itemTile = null;
                for(Tile t : map.getTiles()) {
                    if(t.getPosition().equals(pos)) {
                        itemTile = t;
                        break;
                    }
                }
                
                if(!(itemTile.getType() == TileType.FLOOR)) {
                    System.out.println("Entidade inserido em Tile do Tipo " + itemTile.getType() + " em " + itemTile.getPosition());
                    System.out.println("ATENÇÃO!");
                }
                
                gameEntities.add(currentEntity);
                

                MapRegion region = regionManager.getRegion(itemTile.getId());
                region.addEnemy(currentEntity);
                
                currentEntity.setVisible(region.isUnlocked());
            }
        }
    }
    
    public void loadFromSave(LevelData data, TileMap map, MapRegionManager regionManager) {
        gameEntities.clear();
        
        if(data.getEntityData()!= null) {
            for (Map<String, Object> entityData : data.getEntityData()) {
                GameCharacter currentEntity = EntityFactory.loadEntityFromSave(entityData);
                
                Point2d pos = currentEntity.getPosition();
                
                Tile itemTile = null;
                for(Tile t : map.getTiles()) {
                    if(t.getPosition().equals(pos)) {
                        itemTile = t;
                        break;
                    }
                }
                
                gameEntities.add(currentEntity);
                

                MapRegion region = regionManager.getRegion(itemTile.getId());
                region.addEnemy(currentEntity);
                
                currentEntity.setVisible(region.isUnlocked());
            }
        }
    }
    
    public ArrayList<GameCharacter> getEntities() {
        return gameEntities;
    }
}
