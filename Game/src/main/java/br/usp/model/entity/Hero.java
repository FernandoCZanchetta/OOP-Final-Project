/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.entity;

import br.usp.core.GameEngine;
import br.usp.model.GameObject;
import br.usp.model.SerializableObject;
import br.usp.model.items.ItemFactory;
import br.usp.model.items.Key;
import br.usp.view.SpriteManager;
import br.usp.view.render.GraphicsAPI;
import br.usp.view.render.Renderable;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.vecmath.Point2d;

/**
 *
 * @author Fernando
 */
public class Hero extends GameCharacter implements Renderable {
    private static Hero instance;
    private final Set<GameObject> inventory = new HashSet<>();
    
    public Hero(Point2d position, int maxHp) {
        super(position, maxHp);
        currentHp = maxHp;
        instance = this;
    }
    
    public static Hero getHeroInstace() {
        return instance;
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
    public boolean isVisible() {
        return true;
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

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> data = new HashMap<>();
        
        data.put("type", "HERO");
        data.put("x", (int) position.getX());
        data.put("y", (int) position.getY());
        data.put("maxHp", maxHp);
        data.put("currentHp", currentHp);
        
        List<Map<String, Object>> invList = new ArrayList<>();
        for(GameObject obj : inventory) {
            if(obj instanceof SerializableObject so) {
                invList.add(so.serialize());
            } else {
                System.err.println("Objeto no inventário não é serializável: " + obj);
            }
        }
        data.put("inventory", invList);
        
        return data;
    }
    
    public static Hero deserialize(Map<String, Object> data) {
        int _x = (int) data.get("x");
        int _y = (int) data.get("y");
        int _maxHp = (int) data.getOrDefault("maxHp", 5);
        int _currentHp = (int) data.getOrDefault("currentHp", _maxHp);

        Point2d pos = new Point2d(_x, _y);
        
        Hero hero = new Hero(pos, _maxHp);
        hero.currentHp = _currentHp;

        // Deserializar inventário
        List<Map<String, Object>> _invList = (List<Map<String, Object>>) data.get("inventory");
        if(_invList != null) {
            for(Map<String, Object> itemData : _invList) {
                GameObject item = ItemFactory.loadItemFromSave(itemData);
                hero.inventory.add(item);
            }
        }

        return hero;
    }
}
