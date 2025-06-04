/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.level;

import br.usp.model.GameObject;
import br.usp.model.entity.MeleeEnemy;
import br.usp.model.items.Heart;
import br.usp.model.items.Key;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    
    public static GameObject parseObjectFromJSON(String jsonFilePath) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Carrega o JSON como um Map genérico
            Map<String, Object> data = mapper.readValue(new File(jsonFilePath), Map.class);

            System.out.println(data.keySet());
            
            String type = (String) data.get("type");
            if (type == null) {
                System.err.println("Erro: Campo 'type' ausente em " + jsonFilePath);
                return null;
            }
            
            return switch (type) {
                case "KEY" -> Key.deserialize(data);
                case "HEART" -> Heart.deserialize(data);
                case "MELEE" -> MeleeEnemy.deserialize(data);
                default -> {
                    System.err.println("Tipo desconhecido: " + type);
                    yield null;
                }
            };

        } catch (IOException e) {
            System.err.println("Erro ao ler JSON: " + e.getMessage());
        }
        
        return null;
    }
}
