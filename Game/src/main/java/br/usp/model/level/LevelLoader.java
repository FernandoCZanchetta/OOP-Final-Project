/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.level;

import br.usp.model.level.LevelData;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Fernando
 */
public class LevelLoader {
    public static LevelData loadMap(String path) throws IOException {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(path));
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonData, LevelData.class);
        } catch (IOException e) {
            System.out.println("Erro na leitura do mapa " + path + "(" + e.getMessage() + ")");
            return null;
        }
    }
    
    public static LevelData laodSave(String path) throws IOException {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(path));
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonData, LevelData.class);
        } catch (IOException e) {
            System.out.println("Erro na leitura do save " + path + "(" + e.getMessage() + ")");
            return null;
        }
    }
}
