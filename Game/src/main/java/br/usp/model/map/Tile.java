/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.map;

import br.usp.core.GameEngine;
import br.usp.model.GameObject;
import static br.usp.util.GameConstants.TILE_SIZE;
import br.usp.view.SpriteManager;
import br.usp.view.render.GraphicsAPI;
import java.awt.Color;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import javax.vecmath.Point2d;

/**
 *
 * @author Fernando
 */
public class Tile extends GameObject {
    private TileType type;
    private String Id = null;              // Utilizado em Tiles Especiais (Doors)
    private boolean visible;

    public Tile(TileType type, Point2d position) {
        super.position = position;
        this.type = type;
        this.visible = true;
    }

    public TileType getType() {
        return type;
    }
    
    public void changeType(TileType newType) {
        this.type = newType;
    }

    public String getId() {
        return Id;
    }
    
    public void changeId(String newID) {
        this.Id = newID;
    }
    
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void render(GraphicsAPI g, TileType type) {
        String spriteName = switch (type) {
            case WALL -> "wall";
            case FLOOR -> "floor";
            case DOOR -> "door";
            default -> "null";
        };
        
        Point2d tilePos = new Point2d(position.getX(), position.getY());
        
        if(visible) {
            Image sprite = SpriteManager.getSprite(spriteName);
            g.drawSprite(sprite, tilePos);
        } else {
            Color fogColor = switch (Id.toLowerCase()) {
                case "red" -> new Color(255, 100, 100, 100);
                case "blue" -> new Color(100, 100, 255, 100);
                case "yellow" -> new Color(255, 255, 150, 100);
                case "green" -> new Color(100, 255, 100, 100);
                default -> new Color(200, 200, 200, 100);
            };
            
            g.drawRect(tilePos, TILE_SIZE, TILE_SIZE, fogColor);
        }
    }
    
    @Override
    public void update(GameEngine engine) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> data = new HashMap<>();
        
        data.put("x", (int) position.getX());
        data.put("y", (int) position.getY());
        data.put("type", type.name());
        data.put("id", Id);
        data.put("visible", visible);
        
        return data;
    }
    
    public static Tile deserialize(Map<String, Object> data) {
        int x = (int) data.get("x");
        int y = (int) data.get("y");
        TileType type = TileType.valueOf((String) data.get("type"));
        String id = (String) data.get("id");
        boolean visible = (boolean) data.get("visible");

        Tile tile = new Tile(type, new Point2d(x, y));
        tile.changeId(id);
        tile.setVisible(visible);
        return tile;
    }
}
