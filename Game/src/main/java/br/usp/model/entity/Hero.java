/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.entity;

import static br.usp.util.GameConstants.*;
import br.usp.util.Position;
import br.usp.view.SpriteManager;
import br.usp.view.render.GraphicsAPI;
import br.usp.view.render.Renderable;
import java.awt.Color;
import java.awt.Image;

/**
 *
 * @author Fernando
 */
public class Hero extends GameCharacter implements Renderable {

    public Hero(int x, int y, int maxHp) {
        super(x, y, maxHp);
    }

    @Override
    public void render(GraphicsAPI g) {
        //VER O PROBLEMA DA SPRITE
        //Image sprite = SpriteManager.getSprite("hero_steady");
        //Position tilePos = new Position(position.getRow() * TILE_SIZE, position.getColumn() * TILE_SIZE);
        //g.drawSprite(sprite, tilePos);
        g.drawRect(position, TILE_SIZE, TILE_SIZE, Color.RED);
    }
    
    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
