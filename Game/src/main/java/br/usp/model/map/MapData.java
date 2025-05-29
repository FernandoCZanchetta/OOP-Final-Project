/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.map;

import java.util.ArrayList;
import javax.vecmath.Point2d;

/**
 *
 * @author Fernando
 */
public class MapData {
    private int width;
    private int height;
    private Integer[][] tiles;
    private Point2d heroSpawnPoint;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Integer[][] getTiles() {
        return tiles;
    }

    public Point2d getHeroSpawnPoint() {
        return heroSpawnPoint;
    }
}
