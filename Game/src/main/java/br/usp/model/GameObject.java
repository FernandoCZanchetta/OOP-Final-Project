/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model;

import br.usp.core.GameEngine;
import javax.vecmath.Point2d;

/**
 *
 * @author Fernando
 */
public abstract class GameObject {
    protected Point2d position;
    
    public abstract void update(GameEngine engine);
}
