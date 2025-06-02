/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model;

import br.usp.core.GameEngine;
import java.util.Map;
import javax.vecmath.Point2d;

/**
 *
 * @author Fernando
 */
public abstract class GameObject implements SerializableObject {
    protected Point2d position;
    
    public Point2d getPosition() {
        return position;
    }
    
    public abstract void update(GameEngine engine);

    @Override
    public abstract Map<String, Object> serialize();
}
