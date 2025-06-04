/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.map;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.vecmath.Point2d;

/**
 *
 * @author Fernando
 */
public class Portal extends Tile {
    private Set<String> requiredKeys = new HashSet<>();

    public Portal(TileType type, Point2d position) {
        super(type, position);
        super.setVisible(true);
    }
    
    public Portal(Tile tile) {
        super(tile.getType(), tile.getPosition());
        super.setVisible(tile.isVisible());
    }
    
    public Set<String> getRequiredKeys() {
        return requiredKeys;
    }
    
    public void addRequiredKey(String newKey) {
        this.requiredKeys.add(newKey);
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> data;
        
        data = super.serialize();
        
        data.put("requiredKeys", new ArrayList<>(requiredKeys));
        
        return data;
    }
    
    public static Portal deserialize(Map<String, Object> data) {
        Tile tile = Tile.deserialize(data);
        Portal portal = new Portal(tile);
        
        List<String> keyIds = (List<String>) data.get("requiredKeys");
        portal.requiredKeys.addAll(keyIds);
        
        return portal;
    }
}
