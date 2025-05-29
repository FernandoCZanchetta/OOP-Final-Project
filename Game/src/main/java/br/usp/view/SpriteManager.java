/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.view;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author Fernando
 */
public class SpriteManager {
    private static final HashMap<String, Image> sprites = new HashMap<>();
    
    public static void loadSprite(String name, String path) {
        try {
            Image img = ImageIO.read(new File(path));
            sprites.put(name, img);
        } catch (IOException | NullPointerException e) {
            System.err.println("Erro ao carregar sprite: " + path);
        }
    }
    
    public static Image getSprite(String name) {
        return sprites.get(name);
    }
}
