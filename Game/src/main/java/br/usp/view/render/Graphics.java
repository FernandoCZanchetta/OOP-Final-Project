/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.view.render;

import br.usp.core.GameEngine;
import javax.vecmath.Point2d;

/**
 *
 * @author Fernando
 */
public class Graphics {
    private GameEngine game;
    private GraphicsAPI graphicsAPI;

    public Graphics(GameEngine game) {
        this.game = game;
    } 
    
    public void setGraphicsAPI(GraphicsAPI graphicsAPI) {
        this.graphicsAPI = graphicsAPI;
    }
    
    public void update(int width, int height, java.awt.Graphics2D g) {
        graphicsAPI.createBuffer(width, height);

        // Exemplo: desenha um retângulo fixo para debug
        // graphicsAPI.drawRect(new Point2d(20, 20), 20, 20, java.awt.Color.CYAN);

        graphicsAPI.flushBuffer(g);
    }
}
