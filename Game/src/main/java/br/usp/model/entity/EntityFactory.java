/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.entity;

import static br.usp.model.entity.EntityType.HERO;
import static br.usp.model.entity.EntityType.MELEE;
import java.util.Map;
import javax.vecmath.Point2d;

/**
 *
 * @author Fernando
 */
public class EntityFactory {
    public static GameCharacter createEntity(Map<String, Object> data) {
        String typeStr = (String) data.get("type");
        int x = (int) data.get("x");
        int y = (int) data.get("y");

        EntityType type = EntityType.valueOf(typeStr);
        Point2d pos = new Point2d(x, y);
                
        return switch (type) {
            case HERO -> new Hero(pos, 5);

            case MELEE -> {
                PatrolDirections direction = PatrolDirections.valueOf(String.valueOf(data.getOrDefault("direction", "NONE")));
                int patrolLength = (int) data.getOrDefault("patrolLength", 0);
                double moveInterval = ((Number) data.getOrDefault("moveInterval", 1)).doubleValue();
                yield new MeleeEnemy(pos, 0, patrolLength, direction, moveInterval);
            }

            default -> throw new IllegalArgumentException("Tipo de entidade desconhecido: " + type);
        };
    }
    
    public static GameCharacter loadEntityFromSave(Map<String, Object> data) {
        String typeStr = (String) data.get("type");
        
        EntityType type = EntityType.valueOf(typeStr);
        
        switch (type) {
            case HERO -> {
                return Hero.deserialize(data);
            }
            case MELEE -> {
                return MeleeEnemy.deserialize(data);
            }
            default -> throw new IllegalArgumentException("Unknown entity type: " + type);
        }
    }
}
