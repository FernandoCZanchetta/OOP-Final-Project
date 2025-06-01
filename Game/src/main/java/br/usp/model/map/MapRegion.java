/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.map;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fernando
 */
public class MapRegion {
    private final int id;
    private final List<Tile> tiles;
    private boolean unlocked;
    
    public MapRegion(int id) {
        this.id = id;
        this.tiles = new ArrayList<>();
        this.unlocked = false;
    }
    
    public void addTile(Tile tile) {
        tiles.add(tile);
    }

    public void unlock() {
        unlocked = true;
        for (Tile tile : tiles) {
            tile.setVisible(true);
        }
    }
    
    public boolean isUnlocked() {
        return unlocked;
    }

    public List<Tile> getTiles() {
        return tiles;
    }
    
    public int getId() {
        return id;
    }
}
