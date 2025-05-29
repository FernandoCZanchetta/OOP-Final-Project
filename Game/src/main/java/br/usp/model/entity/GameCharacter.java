/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.entity;

import br.usp.model.map.Tile;
import static br.usp.util.GameConstants.*;
import java.io.Serializable;
import javax.vecmath.Point2d;

/**
 *
 * @author Fernando
 */
public abstract class GameCharacter implements Serializable {
    protected Point2d position;
    protected int maxHp;
    protected int currentHp;
    //ADICIONAR IMAGEM E TALS E OQ FALTAR DO EX DO PROFESSOR
    
    public GameCharacter(int x, int y, int maxHp) {
        this.position = new Point2d(x, y);
        this.maxHp = maxHp;
        this.currentHp = maxHp;
    }
    
    public Point2d getPosition() {
        return position;
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
    
    public void move(int dx, int dy) {
        position.add(new Point2d(dx, dy));
    }
    
    public void checkCharacterCollision(Tile tile) {
        Point2d boundingBox = new Point2d(1, 1);
        boundingBox.scale(0.5);
        
        Point2d char_lower_left_corner = new Point2d(position);
        char_lower_left_corner.sub(boundingBox);
        Point2d char_upper_right_corner = new Point2d(position);
        char_upper_right_corner.add(boundingBox);
        
        Point2d tile_lower_left_corner = new Point2d(tile.getPosition());
        tile_lower_left_corner.sub(boundingBox);
        Point2d tile_upper_right_corner = new Point2d(tile.getPosition());
        tile_upper_right_corner.add(boundingBox);
        
        Point2d uclt_corner_diff = new Point2d(tile_lower_left_corner);
        uclt_corner_diff.sub(char_upper_right_corner);                                      // Calcula a diferença entre as quintas ? da imagem e ? do mundo
        
        Point2d lcut_corner_diff = new Point2d(tile_upper_right_corner);
        lcut_corner_diff.sub(char_lower_left_corner);                                       // Calcula a diferença entre as quintas ? da imagem e ? do mundo
        
        if(lcut_corner_diff.getX() < 0 || lcut_corner_diff.getY() < 0 || uclt_corner_diff.getX() > 0 || uclt_corner_diff.getY() > 0) {
            return;
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
        
        position.add(collision_vector);
    }
    
    public abstract void update();  // Comportamento por tick, pode ser sobrescrito
}
