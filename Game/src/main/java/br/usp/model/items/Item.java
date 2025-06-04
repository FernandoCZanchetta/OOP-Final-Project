/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.items;

import br.usp.model.GameObject;
import br.usp.view.SpriteManager;
import br.usp.view.render.GraphicsAPI;
import java.awt.Image;
import javax.vecmath.Point2d;

/**
 *
 * @author Fernando
 */
public abstract class Item extends GameObject {
    private ItemType type;
    private boolean visible;

    public Item(ItemType type, Point2d position) {
        super.position = position;
        this.type = type;
        this.visible = true;
    }

    public ItemType getType() {
        return type;
    }
    
    public void changeType(ItemType newType) {
        this.type = newType;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void render(GraphicsAPI g, ItemType type) {
        if(!visible) {
            return;
        }
        
        String spriteName = switch (type) {
            case HEART -> "heart";
            default -> "null";
        };
        
        Image sprite = SpriteManager.getSprite(spriteName);
        Point2d tilePos = new Point2d(position.getX(), position.getY());
        g.drawSprite(sprite, tilePos);
    }
}
