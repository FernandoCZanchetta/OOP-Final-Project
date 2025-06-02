/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.entity;

import br.usp.core.GameEngine;
import br.usp.model.GameObject;
import br.usp.model.items.Key;
import br.usp.view.SpriteManager;
import br.usp.view.render.GraphicsAPI;
import br.usp.view.render.Renderable;
import java.awt.Image;
import java.util.HashSet;
import java.util.Set;
import javax.vecmath.Point2d;

/**
 *
 * @author Fernando
 */
public class Hero extends GameCharacter implements Renderable {
    private final Set<GameObject> inventory = new HashSet<>();
    
    public Hero(int x, int y, int maxHp) {
        super(x, y, maxHp);
    }

    public void pickUpKey(Key key) {
        if(!inventory.contains(key)) {
            inventory.add(key);
        }
    }

    public boolean hasKeyFor(String regionId) {
        for (GameObject o : inventory) {
            if (o instanceof Key k) {
                if(k.getUnlocksRegionId().equals(regionId)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    @Override
    public void render(GraphicsAPI g) {
        Image sprite = SpriteManager.getSprite("hero_steady");
        Point2d tilePos = new Point2d(position.getX(), position.getY());
        g.drawSprite(sprite, tilePos);
        
        /*FOR DEBUGGING POURPOSES*/
        //g.drawRect(position, TILE_SIZE, TILE_SIZE, Color.RED);
    }
    
    @Override
    public void update(GameEngine engine) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
