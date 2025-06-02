/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.items;

import br.usp.core.GameEngine;
import br.usp.view.SpriteManager;
import br.usp.view.render.GraphicsAPI;
import java.awt.Image;
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
}
