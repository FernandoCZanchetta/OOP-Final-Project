/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.map;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.vecmath.Point2d;

/**
 *
 * @author Fernando
 */
public class TileMap {
    private final Dimension d;
    private final ArrayList<Tile> tiles;

    public TileMap(Dimension d) {
        this.d = d;
        this.tiles = new ArrayList<>(); //VER SE VOU USAR UM LIMITE MAXIMO COMO ESTA NAS CONST OU SE VAI SER LIVRE (LEMBRAR Q TEM LIMITE NO POS)

        //TO-DO: IMPLEMENTAR UM MAPA DECENTE!!!!!!!!!!!!!!!!!!!
        
        generateSampleMap(); //Teste

    }

    private void generateSampleMap() {
        for (int y = 0; y < d.height; y++) {
            for (int x = 0; x < d.width; x++) {
                if (x == 0 || y == 0 || x == d.width - 1 || y == d.height - 1 || (x == 4 && y > 1)) {
                    tiles.add(new Tile(TileType.WALL, new Point2d(x, y))); //Adiciona as paredes da borda
                } else {
                    tiles.add(new Tile(TileType.FLOOR, new Point2d(x, y))); //Adiciona o chão normal
                }
            }
        }
    }
    
    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public Dimension getDimension() {
        return d;
    }
    
    public int getHeight() {
        return d.height;
    }
    
    public int getWidth() {
        return d.width;
    }
}