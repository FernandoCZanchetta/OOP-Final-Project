///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package br.usp.view.render;
//
//import static br.usp.util.GameConstants.*;
//import br.usp.util.Position;
//
///**
// *
// * @author Fernando
// */
//public class Camera {
//    private Position position;
//    private int viewWidth, viewHeight;
//    
//    public Camera(int viewWidth, int viewHeight) {
//        this.viewWidth = viewWidth;
//        this.viewHeight = viewHeight;
//        position = new Position(0, 0);
//    }
//    
//    public void follow(Position heroPosition) {
//        // Centraliza a câmera em relação ao herói
//        position.setPosition(heroPosition.getRow() - (WINDOW_HEIGHT / TILE_SIZE) / 2, heroPosition.getColumn() - (WINDOW_WIDTH / TILE_SIZE) / 2);
//        
//        // Limita a posição da câmera para não sair dos limites do mapa
//        // Supondo que o mapa tenha dimensões widthMap e heightMap
//        position.setPosition(Math.max(0, Math.min(position.getRow(), MAX_MAP_HEIGHT - (WINDOW_HEIGHT / TILE_SIZE))), Math.max(0, Math.min(position.getColumn(), MAX_MAP_WIDTH - (WINDOW_WIDTH / TILE_SIZE))));
//    }
//    
//    public Position getPosition() {
//        return position;
//    }
//    
//    public int getX() {
//        return this.position.getColumn();
//    }
//    
//    public int getY() {
//        return this.position.getRow();
//    }
//}
