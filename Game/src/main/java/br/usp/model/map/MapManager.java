/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.map;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Fernando
 */
public class MapManager {
    private static final HashMap<String, MapData> maps = new HashMap<>();
    
    public static void loadMap(String name, String path) throws IOException {
        if (!maps.containsKey(name)) {
            MapData map = MapLoader.loadMap(path);
            maps.put(name, map);
        }
    }
    
    public static MapData getMapData(String mapName) {
        return maps.get(mapName);
    }
    
    public static void preloadAllMaps(String directoryPath) throws IOException {
        for(File f : (new File(directoryPath)).listFiles()) {
            if (!f.isDirectory()) {
                //System.out.println(f.getName().replace(".json", "") + " " + f.getPath());
                loadMap(f.getName().replace(".json", ""), f.getPath());
            }
        }
    }
    
    public void clearMaps() {
        maps.clear();
    }
}
