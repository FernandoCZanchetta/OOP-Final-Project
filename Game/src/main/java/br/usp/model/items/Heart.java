/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.items;

import br.usp.core.GameEngine;
import java.util.HashMap;
import java.util.Map;
import javax.vecmath.Point2d;

/**
 *
 * @author Fernando
 */
public class Heart extends Item {
    public Heart(ItemType type, Point2d position) {
        super(type, position);
    }
    
    @Override
    public void update(GameEngine engine) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> data = new HashMap<>();
        
        data.put("type", "HEART");
        data.put("x", (int) position.getX());
        data.put("y", (int) position.getY());
        data.put("isVisible", isVisible());
        
        return data;
    }
    
    public static Heart deserialize(Map<String, Object> data) {
        ItemType type = (ItemType) ItemType.valueOf((String) data.get("type"));
        int x = (int) data.get("x");
        int y = (int) data.get("y");

        Heart heart = new Heart(type, new Point2d(x, y));
        heart.setVisible((boolean) data.getOrDefault("isVisible", true));
        
        return heart;
    }
}
