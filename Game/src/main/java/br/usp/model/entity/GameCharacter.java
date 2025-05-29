/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.entity;

import br.usp.util.Position;
import java.io.Serializable;

/**
 *
 * @author Fernando
 */
public abstract class GameCharacter implements Serializable {
    protected Position position;
    protected int maxHp;
    protected int currentHp;
    //ADICIONAR IMAGEM E TALS E OQ FALTAR DO EX DO PROFESSOR
    
    public GameCharacter(int x, int y, int maxHp) {
        this.position = new Position(x, y);
        this.maxHp = maxHp;
        this.currentHp = maxHp;
    }
    
    public Position getPosition() {
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
        position.move(dx, dy);
    }
    
    public abstract void update();  // Comportamento por tick, pode ser sobrescrito
}
