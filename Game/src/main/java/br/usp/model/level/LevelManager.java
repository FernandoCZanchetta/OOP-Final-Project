/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.level;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Fernando
 */
public class LevelManager {
    private static final HashMap<String, LevelData> maps = new HashMap<>();
    private static final HashMap<String, LevelData> saves = new HashMap<>();
    
    public static void loadMap(String name, String path) throws IOException {
        if (!maps.containsKey(name)) {
            LevelData map = LevelLoader.loadMap(path);
            maps.put(name, map);
        }
    }
    
    public static LevelData getMapData(String mapName) {
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
    
    public static void saveLevel(LevelData data, String path) throws IOException {      
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), data);
    }
    
    public static void loadSave(String name, String path) throws IOException {
        if (!saves.containsKey(name)) {
            LevelData save = LevelLoader.laodSave(path);
            saves.put(name, save);
        }
    }
}
