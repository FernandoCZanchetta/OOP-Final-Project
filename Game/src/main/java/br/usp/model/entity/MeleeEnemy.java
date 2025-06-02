/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.entity;

import br.usp.core.GameEngine;
import static br.usp.util.GameConstants.GAME_DELTA_TIME;
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
public class MeleeEnemy extends Enemy {
    public MeleeEnemy(Point2d position, int maxHp, int patrolLenght, PatrolDirections direction, double moveInterval) {
        super(position, maxHp, patrolLenght, direction, moveInterval);
        this.baseDamage = 1;
    }

    @Override
    public void performBehavior(GameEngine engine, Hero hero) {
        if(this.checkCharacterCollision(hero)) {
            hero.damage(baseDamage);
        }
    }
    
    @Override
    public void render(GraphicsAPI g) {
        Image sprite = SpriteManager.getSprite("melee_enemy");
        Point2d tilePos = new Point2d(position.getX(), position.getY());
        g.drawSprite(sprite, tilePos);
        
        /*FOR DEBUGGING POURPOSES*/
        //g.drawRect(position, TILE_SIZE, TILE_SIZE, Color.GREEN);
    }
    
    @Override
    public void update(GameEngine engine) {
        moveTimer += GAME_DELTA_TIME;
        
        System.out.println(this.patrolOrientation);
        System.out.println(this.patrolProgress);
        System.out.println(this.position);
        if (moveTimer >= moveInterval) {
            moveTimer = 0;

            if(direction.equals(PatrolDirections.HORIZONTAL)) {
                this.move(super.patrolOrientation, 0, 1);
            } else {
                this.move(0, super.patrolOrientation, 1);
            }

            super.patrolProgress += 1;
            if (super.patrolProgress >= super.patrolLength) {
                super.patrolProgress = 0;
                super.patrolOrientation *= -1;              // Inverte a direção
            }
        }
        
        performBehavior(engine, engine.getHero());
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> data = new HashMap<>();
        
        data.put("type", "MELEE");
        data.put("x", (int) position.getX());
        data.put("y", (int) position.getY());
        data.put("baseDamage", baseDamage);
        data.put("maxHp", maxHp);
        data.put("currentHp", currentHp);
        data.put("patrolLength", patrolLength);
        data.put("direction", direction.name());
        data.put("patrolProgress", patrolProgress);
        data.put("patrolOrientation", patrolOrientation);
        data.put("moveTimer", moveTimer);
        data.put("moveInterval", moveInterval);
        data.put("isVisible", isVisible());
        
        return data;
    }
    
    public static MeleeEnemy deserialize(Map<String, Object> data) {
        int x = (int) data.get("x");
        int y = (int) data.get("y");
        int baseDamage = (int) data.get("baseDamage");
        int maxHp = (int) data.get("maxHp");
        int currentHp = (int) data.get("currentHp");
        int patrolLength = (int) data.get("patrolLength");
        PatrolDirections direction = PatrolDirections.valueOf((String) data.get("direction"));
        int patrolProgress = (int) data.get("patrolProgress");
        int patrolOrientation = (int) data.get("patrolOrientation");
        double moveTimer = (double) data.get("moveTimer");
        double moveInterval = (double) data.get("moveInterval");
        boolean isVisible = (boolean) data.get("isVisible");

        MeleeEnemy enemy = new MeleeEnemy(new Point2d(x, y), baseDamage, patrolLength, direction, moveInterval);
        enemy.maxHp = maxHp;
        enemy.currentHp = currentHp;
        enemy.patrolProgress = patrolProgress;
        enemy.patrolOrientation = patrolOrientation;
        enemy.moveTimer = moveTimer;
        enemy.setVisible(isVisible);

        return enemy;
    }
}
