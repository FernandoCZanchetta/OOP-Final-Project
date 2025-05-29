/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.map;

import static br.usp.util.GameConstants.TILE_SIZE;
import br.usp.util.Position;
import br.usp.view.SpriteManager;
import br.usp.view.render.GraphicsAPI;
import br.usp.view.render.Renderable;
import java.awt.Image;

/**
 *
 * @author Fernando
 */
public class Tile {//implements Renderable {
    private final TileType type;
    private boolean visible;
    //private Position position;

    public Tile(TileType type){ //,Position position) {
        //this.position = position;
        this.type = type;
        this.visible = true;    //MUDAR DPS PRO NEGÓCIO DO INVISIVEL E MAPA OFUSCADO
    }

    public TileType getType() {
        return type;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    //CONSERTAR SPRITES
//    @Override
//    public void render(GraphicsAPI g) {
//        if(!visible) {
//            return;
//        }
//        
//        String spriteName = switch (type) {
//            case WALL -> "wall";
//            case FLOOR -> "floor";
//            default -> "null";
//        };
//        
//        Image sprite = SpriteManager.getSprite(spriteName);
//        Position tilePos = new Position(position.getRow() * TILE_SIZE, position.getColumn() * TILE_SIZE);
//        g.drawSprite(sprite, position);
//    }
}
