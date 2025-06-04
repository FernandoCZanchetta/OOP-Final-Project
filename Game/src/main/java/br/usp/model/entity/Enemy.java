/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.entity;

import br.usp.core.GameEngine;
import javax.vecmath.Point2d;

/**
 *
 * @author Fernando
 */
public abstract class Enemy extends GameCharacter {
    protected int baseDamage = 1;
    protected int patrolLength = 0;
    protected PatrolDirections direction;
    protected int patrolProgress = 0;
    protected int patrolOrientation = 1;            // Forward 1, Backwards -1
    protected double moveTimer = 0;
    protected double moveInterval;                  // Segundos entre cada passo
    
    public Enemy(Point2d position, int maxHp, int patrolLength, PatrolDirections direction, double moveInterval) {
        super(position, maxHp);
        this.patrolLength = patrolLength;
        this.direction = direction;
        this.moveInterval = moveInterval;
    }
    
    public abstract void performBehavior(GameEngine engine, Hero hero);
}
