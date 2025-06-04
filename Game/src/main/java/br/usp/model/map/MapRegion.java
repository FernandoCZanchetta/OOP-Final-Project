/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.map;

import br.usp.model.entity.GameCharacter;
import br.usp.model.items.Item;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fernando
 */
public class MapRegion {
    private final String id;
    private final List<Tile> tiles;
    private final List<Item> items;
    private final List<GameCharacter> entities;
    private boolean unlocked;
    
    public MapRegion(String id) {
        this.id = id;
        this.tiles = new ArrayList<>();
        this.items = new ArrayList<>();
        this.entities = new ArrayList<>();
        this.unlocked = false;
    }
    
    public void addTile(Tile tile) {
        tiles.add(tile);
    }
    
    public void addItem(Item item) {
        items.add(item);
    }
    
    public void addEnemy(GameCharacter entity) {
        entities.add(entity);
    }

    public void unlock() {
        unlocked = true;
        
        for (Tile tile : tiles) {
            tile.setVisible(true);
        }
        
        for (Item item : items) {
            item.setVisible(true);
        }
        
        for(GameCharacter entity : entities) {
            entity.setVisible(true);
        }
    }
    
    public boolean isUnlocked() {
        return unlocked;
    }

    public List<Tile> getTiles() {
        return tiles;
    }
    
    public List<Item> getItems() {
        return items;
    }
    
    public List<GameCharacter> getEntities() {
        return entities;
    }
    
    public String getId() {
        return id;
    }
}
