/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.entity;

import br.usp.core.GameEngine;
import br.usp.model.GameObject;
import br.usp.model.SerializableObject;
import br.usp.model.map.Tile;
import br.usp.view.render.GraphicsAPI;
import java.util.Map;
import javax.vecmath.Point2d;

/**
 *
 * @author Fernando
 */
public abstract class GameCharacter extends GameObject implements SerializableObject {
    protected int maxHp;
    protected int currentHp;
    private boolean visible;
    
    public GameCharacter(Point2d position, int maxHp) {
        super.position = new Point2d(position);
        this.maxHp = maxHp;
        this.currentHp = maxHp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void heal(int amount) {
        currentHp = Math.min(maxHp, currentHp + amount);
    }

    public void damage(int amount) {
        currentHp = Math.max(0, currentHp - amount);
    }

    public boolean isDead() {
        return currentHp <= 0;
    }
    
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    public void move(double dx, double dy, double speed) {
        Point2d delta_pos = new Point2d(dx, dy);
        delta_pos.scale(speed);
        position.add(delta_pos);
    }
    
    public boolean checkCharacterCollision(GameObject object) {
        Point2d boundingBox = new Point2d(1, 1);
        boundingBox.scale(0.5);
        
        Point2d char_lower_left_corner = new Point2d(position);
        char_lower_left_corner.sub(boundingBox);
        Point2d char_upper_right_corner = new Point2d(position);
        char_upper_right_corner.add(boundingBox);
        
        Point2d tile_lower_left_corner = new Point2d(object.getPosition());
        tile_lower_left_corner.sub(boundingBox);
        Point2d tile_upper_right_corner = new Point2d(object.getPosition());
        tile_upper_right_corner.add(boundingBox);
        
        Point2d uclt_corner_diff = new Point2d(tile_lower_left_corner);
        uclt_corner_diff.sub(char_upper_right_corner);                                      // Calcula a diferença entre as quintas ? da imagem e ? do mundo
        
        Point2d lcut_corner_diff = new Point2d(tile_upper_right_corner);
        lcut_corner_diff.sub(char_lower_left_corner);                                       // Calcula a diferença entre as quintas ? da imagem e ? do mundo
        
        if(lcut_corner_diff.getX() <= 0 || lcut_corner_diff.getY() <= 0 || uclt_corner_diff.getX() >= 0 || uclt_corner_diff.getY() >= 0) {
            return false;
        }
        
        Point2d collision_vector = new Point2d(0, 0);
        // Getting the smallest step to handle the collision from the 2 vectors AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        Point2d collision_handling_vector = new Point2d();
        if (Math.abs(lcut_corner_diff.getX()) <= Math.abs(uclt_corner_diff.getX()))
          collision_handling_vector.setX(lcut_corner_diff.getX());
        else
          collision_handling_vector.setX(uclt_corner_diff.getX());

        if (Math.abs(lcut_corner_diff.getY()) <= Math.abs(uclt_corner_diff.getY()))
          collision_handling_vector.setY(lcut_corner_diff.getY());
        else
          collision_handling_vector.setY(uclt_corner_diff.getY());

        // Get the smallest X,Y or XY difference to handle the collision
        if (Math.abs(collision_handling_vector.getX()) <= Math.abs(collision_handling_vector.getY()))
          collision_vector.setX(collision_handling_vector.getX());
        if (Math.abs(collision_handling_vector.getY()) <= Math.abs(collision_handling_vector.getX()))
          collision_vector.setY(collision_handling_vector.getY());
        
        if(object instanceof Tile) {
            position.add(collision_vector);
        }
        
        return true;
    }
    
    public abstract void render(GraphicsAPI g);
    
    @Override
    public abstract void update(GameEngine engine);  // Comportamento por tick, pode ser sobrescrito
    
    @Override
    public abstract Map<String, Object> serialize();
}
