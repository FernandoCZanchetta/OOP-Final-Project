/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.view.render;

import static br.usp.util.GameConstants.MAX_MAP_HEIGHT;
import static br.usp.util.GameConstants.MAX_MAP_WIDTH;
import static br.usp.util.GameConstants.TILE_SIZE;
import static br.usp.util.GameConstants.WINDOW_HEIGHT;
import static br.usp.util.GameConstants.WINDOW_WIDTH;
import javax.vecmath.Point2d;

/**
 *
 * @author Fernando
 */
public class Camera {
    private static Camera instance;
    private Point2d position;
    
    public Camera() {
        position = new Point2d(0, 0);
        instance = this;
    }
    
    public static Camera getCameraInstance() {
        return instance;
    }
    
    public void follow(Point2d heroPosition) {
        // Centraliza a câmera em relação ao herói
        position = new Point2d(heroPosition);
        
        // Limita a posição da câmera para não sair dos limites do mapa
        checkCollision();
    }
    
    public void checkCollision() {
        Point2d window_pos = new Point2d(WINDOW_WIDTH, WINDOW_HEIGHT);
        window_pos.scale(0.5 / TILE_SIZE);
        
        Point2d cam_lower_left_corner = new Point2d(position);
        cam_lower_left_corner.sub(window_pos);
        Point2d cam_upper_right_corner = new Point2d(position);
        cam_upper_right_corner.add(window_pos);
        
        Point2d map_lower_left_corner = new Point2d(0, 0);
        Point2d map_upper_right_corner = new Point2d(MAX_MAP_WIDTH, MAX_MAP_HEIGHT);
        
        Point2d ll_corner_diff = new Point2d(cam_lower_left_corner);
        ll_corner_diff.sub(map_lower_left_corner);                                      // Calcula a diferença entre as quintas ? da imagem e ? do mundo
        
        Point2d uu_corner_diff = new Point2d(cam_upper_right_corner);
        uu_corner_diff.sub(map_upper_right_corner);                                      // Calcula a diferença entre as quintas ? da imagem e ? do mundo
    
        if(ll_corner_diff.getX() > 0) {
            ll_corner_diff.setX(0);
        }
        
        if(ll_corner_diff.getY() > 0) {
            ll_corner_diff.setY(0);
        }
        
        if(uu_corner_diff.getX() < 0) {
            uu_corner_diff.setX(0);
        }
        
        if(uu_corner_diff.getY() < 0) {
            uu_corner_diff.setY(0);
        }
        
        position.sub(ll_corner_diff);
        position.sub(uu_corner_diff);
    }
    
    public Point2d getPosition() {
        return position;
    }
}
