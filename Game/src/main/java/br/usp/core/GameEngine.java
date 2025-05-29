/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.core;

import br.usp.io.InputAPI;
import br.usp.io.SwingInputAPI;
import br.usp.model.entity.Hero;
import br.usp.model.map.TileMap;
import br.usp.view.SpriteManager;
import br.usp.view.layout.MainFrame;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

/**
 *
 * @author Fernando
 */
public class GameEngine {
    //Singleton Instance for GameEngine
    private static GameEngine instance = null;
    
    private GameState gameState = GameState.MAIN_MENU;
    private SwingInputAPI input;
    private MainFrame mainFrame;
    private TileMap tileMap;
    private Hero hero;
    
    private final GameTime gameTime;
    
    private GameEngine() {
        this.gameTime = new GameTime();
    }
    
    public static GameEngine getGameEngineInstance() {
        if(instance == null) {
            instance = new GameEngine();
        }
        
        return instance;
    }
    
    public void loadSprites() {
        //ADICIONAR OUTRAS SPRITES AQUI E VER SE DÁ DE ANIMAR ELAS
        SpriteManager.loadSprite("hero_steady", "sprites/hero_steady.png");
        SpriteManager.loadSprite("wall", "sprites/wall.png");
        SpriteManager.loadSprite("floor", "sprites/floor.png");
    }
    
    public void run() {
        this.tileMap = new TileMap(new Dimension(20, 15));
        this.hero = new Hero(1, 1, 5);
        this.input = new SwingInputAPI();
        this.mainFrame = new MainFrame();
        startGame();
    }
    
    public void startGame() {
            this.gameState = GameState.RUNNING;
            gameTime.start();
    } 
    
    public void pauseGame() {
        if (gameState == GameState.RUNNING) {
            this.gameState = GameState.PAUSED;
            gameTime.pause();
        }
    }
    
    public void resumeGame() {
        if (gameState == GameState.PAUSED) {
            this.gameState = GameState.RUNNING;
            gameTime.resume();
        }
    }
    
    public void resetGame() {
        gameTime.reset();
        gameState = GameState.MAIN_MENU;
    }
    
    public void update() {
        if (gameState != GameState.RUNNING) return;
        
        if (input.getKeyPressed(KeyEvent.VK_W)) {
            this.getHero().getPosition().moveUp();
            //System.out.println("Moveu para cima!");
        }
        if (input.getKeyPressed(KeyEvent.VK_S)) {
            this.getHero().getPosition().moveDown();
            //System.out.println("Moveu para baixo!");
        }
        if (input.getKeyPressed(KeyEvent.VK_A)) {
            this.getHero().getPosition().moveLeft();
            //System.out.println("Moveu para esquerda!");
        }
        if (input.getKeyPressed(KeyEvent.VK_D)) {
            this.getHero().getPosition().moveRight();
            //System.out.println("Moveu para direita!");
        }
        if (input.getKeyPressed(KeyEvent.VK_ESCAPE)) {
            this.pauseGame();
            mainFrame.pauseGame();
            System.out.println("Game pausado!");
        }
    }
    
    public MainFrame getMainFrame() {
        return mainFrame;
    }
    
    public SwingInputAPI getInput() {
        return input;
    }
    
    public TileMap getTileMap() {
        return tileMap;
    }

    public Hero getHero() {
        return hero;
    }
    
    public GameTime getGameTime() {
        return gameTime;
    }
    
    public GameState getState() {
        return gameState;
    }

    public boolean isRunning() {
        return gameState == GameState.RUNNING;
    }

    public boolean isPaused() {
        return gameState == GameState.PAUSED;
    }
}
