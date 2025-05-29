/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.view;

import br.usp.core.GameEngine;
import br.usp.core.GameLoop;
import br.usp.io.SwingInputAPI;
import br.usp.model.entity.Hero;
import br.usp.model.map.Tile;
import br.usp.model.map.TileMap;
import static br.usp.util.GameConstants.*;
import br.usp.util.Position;
import br.usp.view.layout.MainFrame;
import br.usp.view.render.Graphics;
import br.usp.view.render.GraphicsAPI;
import br.usp.view.render.SwingGraphicsAPI;
import java.awt.Color;
//import br.usp.view.render.SwingGraphicsAPI;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.vecmath.Point2i;

/**
 *
 * @author Fernando
 */
public class GamePanel extends JPanel {
    private final GameEngine engine = GameEngine.getGameEngineInstance();
    
    private final SwingGraphicsAPI graphicsAPI = new SwingGraphicsAPI();
    private final SwingInputAPI input;
    private final MainFrame mainframe;
    private final GameLoop loop;
    //private final Graphics graphics;
    //private final SwingGraphicsAPI swingGraphicsAPI;
    
    private long pauseCooldown = System.currentTimeMillis(); //REMOVER JUNTO COM O UPDATE E POR EM LUGAR MELHOR
  
    public GamePanel(MainFrame mainframe) {
        //graphics = new Graphics(engine);
        //swingGraphicsAPI = new SwingGraphicsAPI();
        //graphics.setGraphicsAPI(swingGraphicsAPI);
        
        this.mainframe = mainframe;
        this.input = engine.getInput();
        
        input.attachToComponent(this);

        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setFocusable(true);
        
        this.loop = new GameLoop(engine, this, GAME_FPS);
        loop.start();
    }
    
    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
    
        BufferedImage buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bufferGraphics = buffer.createGraphics();
        graphicsAPI.setGraphicsContext(bufferGraphics);

        TileMap tileMap = engine.getTileMap();
        Hero hero = engine.getHero();

        for (int y = 0; y < tileMap.getHeight(); y++) {
            for (int x = 0; x < tileMap.getWidth(); x++) {
                Tile tile = tileMap.getTile(new Point2i(x, y));
                if (!tile.isVisible()) continue;
                
                //QUANDO TIVER SPRITE
                //tile.render(graphicsAPI);
                
                Color color = switch (tile.getType()) {
                    case WALL -> Color.DARK_GRAY;
                    case FLOOR -> Color.LIGHT_GRAY;
                    default -> Color.BLACK; //VER SE DÁ PRA REMOVER ISSO
                };
                
                Position pos = new Position(y, x);
                graphicsAPI.drawRect(pos, TILE_SIZE, TILE_SIZE, color);
                //g.fillRect(x * TileMap.TILE_SIZE, y * TileMap.TILE_SIZE, TileMap.TILE_SIZE, TileMap.TILE_SIZE);
            }
        }
        
        //LEMBRAR Q O PROF QUER UM ARRAYLIST COM O HERO NO ZERO PARA RENDIZAÇAO
        // Player
//        g.setColor(Color.RED);
//        Position p = hero.getPosition();
//        g.fillOval(p.getColumn() * TileMap.TILE_SIZE, p.getRow() * TileMap.TILE_SIZE, TileMap.TILE_SIZE, TileMap.TILE_SIZE);
        
        hero.render(graphicsAPI);
        
        bufferGraphics.dispose();
        
        g.drawImage(buffer, 0, 0, null);

        this.setFocusable(true);
        this.requestFocusInWindow();
    }
    
    //DEFINITIVAMENTE TIRAR ISSO DAQUI
    
    
//    @Override
//    protected void paintComponent(java.awt.Graphics g) {
//        super.paintComponent(g);
//
//        Graphics2D g2d = (Graphics2D) g;
//        //graphics.update(getWidth(), getHeight(), g2d);
//    }
}
