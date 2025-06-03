/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.util;

/**
 *
 * @author Fernando
 */
public final class GameConstants {
    private GameConstants() {};
    
    // Limites do mundo
    public static final int MAX_MAP_WIDTH = 100;
    public static final int MAX_MAP_HEIGHT = 100;

    // Tamanho do tile
    public static final int TILE_SIZE = 64;

    // Tamanho da janela
    public static final int WINDOW_WIDTH = 960;
    public static final int WINDOW_HEIGHT = 768;

    // Taxa FPS e Delta Time
    public static final int GAME_FPS = 60;
    public static final double GAME_DELTA_TIME = 1.0 / GAME_FPS;
    
    
    // Player
    public static final int HERO_MAX_HP = 5;
}
