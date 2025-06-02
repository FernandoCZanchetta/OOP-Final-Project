/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.vecmath.Point2d;

/**
 *
 * @author Fernando
 */
public class MapData { //POSSIVELMENTE RENOMEAR PARA LEVEL DATA E REALOCAR!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private int width;
    private int height;
    private Integer[][] tiles;
    private Integer[][] regionIds;
    private Point2d heroSpawnPoint;
    private List<Map<String, Object>> doorMetadata;
    private List<Map<String, Object>> itemData; 

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Integer[][] getTiles() {
        return tiles;
    }

    public Integer[][] getRegionIds() {
        return regionIds;
    }
    
    public Point2d getHeroSpawnPoint() {
        return heroSpawnPoint;
    }
    
    public List<Map<String, Object>> getDoorMetadata() {
        return doorMetadata;
    }
    
    public List<Map<String, Object>> getItemData() {
        return itemData;
    }
}
