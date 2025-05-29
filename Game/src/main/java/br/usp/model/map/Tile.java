/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.map;

import br.usp.view.SpriteManager;
import br.usp.view.render.GraphicsAPI;
import java.awt.Image;
import javax.vecmath.Point2d;

/**
 *
 * @author Fernando
 */
public class Tile {
    private final TileType type;
    private boolean visible;
    private Point2d position;

    public Tile(TileType type, Point2d position) {
        this.position = position;
        this.type = type;
        this.visible = true;    //MUDAR DPS PRO NEGÓCIO DO INVISIVEL E MAPA OFUSCADO
    }

    public TileType getType() {
        return type;
    }
    
    public Point2d getPosition() {
        return position;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void render(GraphicsAPI g, TileType type) {
        if(!visible) {
            return;
        }
        
        String spriteName = switch (type) {
            case WALL -> "wall";
            case FLOOR -> "floor";
            default -> "null";
        };
        
        Image sprite = SpriteManager.getSprite(spriteName);
        Point2d tilePos = new Point2d(position.getX(), position.getY());
        g.drawSprite(sprite, tilePos);
    }
}
