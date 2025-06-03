/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.items;

import br.usp.core.GameEngine;
import br.usp.view.SpriteManager;
import br.usp.view.render.GraphicsAPI;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import javax.vecmath.Point2d;

/**
 *
 * @author Fernando
 */
public class Key extends Item {
    private final String unlocksRegionId;

    public Key(String unlocksRegionId, ItemType type, Point2d position) {
        super(type, position);
        this.unlocksRegionId = unlocksRegionId;
    }
    
    public String getUnlocksRegionId() {
        return unlocksRegionId;
    }

    @Override
    public void update(GameEngine engine) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void render(GraphicsAPI g, ItemType type) {
        if(!isVisible()) {
            return;
        }
        
        String spriteName = switch (unlocksRegionId) {
            case "yellow" -> "yellow_key";
            case "red" -> "red_key";
            case "blue" -> "blue_key";
            case "green" -> "green_key";
            default -> "null";
        };
        
        Image sprite = SpriteManager.getSprite(spriteName);
        Point2d tilePos = new Point2d(this.getPosition().getX(), this.getPosition().getY());
        g.drawSprite(sprite, tilePos);
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> data = new HashMap<>();
        
        data.put("type", "KEY");
        data.put("x", (int) position.getX());
        data.put("y", (int) position.getY());
        data.put("regionId", unlocksRegionId);
        data.put("isVisible", isVisible());
        
        return data;
    }
    
    public static Key deserialize(Map<String, Object> data) {
        ItemType type = (ItemType) ItemType.valueOf((String) data.get("type"));
        int x = (int) data.get("x");
        int y = (int) data.get("y");
        String regionId = (String) data.get("regionId");

        Key key = new Key(regionId, type, new Point2d(x, y));
        key.setVisible((boolean) data.getOrDefault("isVisible", true));
        
        return key;
    }
}
