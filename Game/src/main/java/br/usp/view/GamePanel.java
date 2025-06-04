/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.view;

import br.usp.core.GameEngine;
import br.usp.core.GameLoop;
import br.usp.io.SwingInputAPI;
import br.usp.model.entity.EntityMap;
import br.usp.model.entity.GameCharacter;
import br.usp.model.items.Item;
import br.usp.model.items.ItemMap;
import br.usp.model.map.Tile;
import br.usp.model.map.TileMap;
import static br.usp.util.GameConstants.*;
import br.usp.view.hud.HUDRenderer;
import br.usp.view.layout.MainFrame;
import br.usp.view.render.SwingGraphicsAPI;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Fernando
 */
public class GamePanel extends JPanel {
    private final GameEngine engine = GameEngine.getGameEngineInstance();
    
    private final SwingGraphicsAPI graphicsAPI = new SwingGraphicsAPI();
    private final HUDRenderer hudRenderer = new HUDRenderer();
    private final SwingInputAPI input;
    private final MainFrame mainframe;
    private final GameLoop loop;
    
    private long pauseCooldown = System.currentTimeMillis(); //REMOVER JUNTO COM O UPDATE E POR EM LUGAR MELHOR
  
    public GamePanel(MainFrame mainframe) {        
        this.mainframe = mainframe;
        this.input = engine.getInput();
        
        input.attachToComponent(this);

        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setFocusable(true);
        
        this.loop = new GameLoop(engine, this, GAME_FPS);
        loop.start();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        BufferedImage buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bufferGraphics = buffer.createGraphics();
        graphicsAPI.setGraphicsContext(bufferGraphics);

        TileMap tileMap = engine.getTileMap();
        ItemMap itemMap = engine.getItemMap();
        EntityMap entityMap = engine.getEntityMap();
        
        for(Tile tile : tileMap.getTiles()) {
            //if (!tile.isVisible()) continue;              // Removido para implementar a névoa nas regiões!
            
            tile.render(graphicsAPI, tile.getType());
        }
        
        for (Item item : itemMap.getItems()) {
            if(!item.isVisible()) continue;
            
            item.render(graphicsAPI, item.getType());
        }
        
        for (GameCharacter entity : entityMap.getEntities()) {
            if(!entity.isVisible()) continue;
            
            entity.render(graphicsAPI);
        }        
        
        hudRenderer.render(graphicsAPI);
        
        bufferGraphics.dispose();
        
        g.drawImage(buffer, 0, 0, null);

        this.setFocusable(true);
        this.requestFocusInWindow();
    }
}
