/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.core;

import br.usp.io.SwingInputAPI;
import br.usp.model.entity.Enemy;
import br.usp.model.entity.EntityMap;
import br.usp.model.entity.GameCharacter;
import br.usp.model.entity.Hero;
import br.usp.model.items.Item;
import br.usp.model.items.ItemMap;
import br.usp.model.items.ItemType;
import br.usp.model.items.Key;
import br.usp.model.level.LevelData;
import br.usp.model.level.LevelManager;
import br.usp.model.map.MapRegionManager;
import br.usp.model.map.Tile;
import br.usp.model.map.TileMap;
import br.usp.model.map.TileType;
import static br.usp.util.GameConstants.GAME_DELTA_TIME;
import br.usp.view.SpriteManager;
import br.usp.view.layout.MainFrame;
import br.usp.view.render.Camera;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.IOException;

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
    private Camera camera;
    private MapRegionManager mapRegionManager;
    private TileMap tileMap;
    private ItemMap itemMap;
    private EntityMap entityMap;
    private LevelData mapData;
    private Hero hero;
    
    private final GameTime gameTime;
    private int map_width;
    private int map_height;
    
    private boolean was_escape_pressed;
    
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
        /*Game Character Sprites*/
        SpriteManager.loadSprite("hero_steady", "sprites/hero_steady.png");
        
        /*Tiles' Sprites*/
        SpriteManager.loadSprite("wall", "sprites/tiles/wall.png");
        SpriteManager.loadSprite("floor", "sprites/tiles/floor.png");
        SpriteManager.loadSprite("door", "sprites/tiles/door.png");
        
        /*Keys' Sprites*/
        SpriteManager.loadSprite("yellow_key", "sprites/keys/yellow_key.png");
        SpriteManager.loadSprite("red_key", "sprites/keys/red_key.png");
        SpriteManager.loadSprite("blue_key", "sprites/keys/blue_key.png");
        SpriteManager.loadSprite("green_key", "sprites/keys/green_key.png");
        
        /*Enemies Sprites*/
        SpriteManager.loadSprite("melee_enemy", "sprites/enemies/melee_enemy.png");
    }
    
    public void run() {
        try {
            LevelManager.preloadAllMaps("maps");                         //IMPLEMENTAR TROCAR OS MAPAS DE VEZ EM QUANDO
        } catch (IOException ex) {
            System.out.println("Problema na leitura dos arquivos dos mapas!");
        }
        this.mapData = LevelManager.getMapData("map01");
        this.tileMap = new TileMap(new Dimension(mapData.getWidth(), mapData.getHeight()));
        this.itemMap = new ItemMap();
        this.entityMap = new EntityMap();
        this.mapRegionManager = new MapRegionManager();
        this.tileMap.loadFromData(mapData, mapRegionManager);
        this.itemMap.loadFromData(mapData, tileMap, mapRegionManager);
        this.entityMap.loadFromData(mapData, tileMap, mapRegionManager);
        this.hero = Hero.getHeroInstace();
        this.input = new SwingInputAPI();
        this.mainFrame = new MainFrame();
        this.camera = new Camera();
        
        map_width = mapData.getWidth();
        map_height = mapData.getHeight();
        
        startGame();
    }
    
    public void load(LevelData data) {
        this.mapData = data;
        
        this.tileMap = new TileMap(new Dimension(mapData.getWidth(), mapData.getHeight()));
        this.itemMap = new ItemMap();
        this.entityMap = new EntityMap();
        this.mapRegionManager = new MapRegionManager();
        
        
        this.tileMap.loadFromData(mapData, mapRegionManager);
        this.itemMap.loadFromData(mapData, tileMap, mapRegionManager);
        this.entityMap.loadFromSave(mapData, tileMap, mapRegionManager);
        
        // Atualiza a instância do hero
        this.hero = Hero.getHeroInstace();
        
        this.input = new SwingInputAPI();
        this.camera = new Camera();
        
        map_width = mapData.getWidth();
        map_height = mapData.getHeight();
        
        // FUTURAMENTE GUARDAR O CRONOMETRO JUNTO NO LEVEL
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
    
    public void loadGame(LevelData data) {
        this.gameState = GameState.RUNNING;
        // VOLTAR POSICAO DO TIMER ANTERIOR SE DER
        load(data);
    }
    
    public void resetGame() {
        gameTime.reset();
        gameState = GameState.MAIN_MENU;
    }
    
    public void update() {                      //THIS IS THE MAIN GAME LOOP
        double horizontal_moviment = 0;
        double vertical_moviment = 0;
        
        if (gameState != GameState.RUNNING) return;
        
        if (input.getKeyPressed(KeyEvent.VK_W) || input.getKeyPressed(KeyEvent.VK_UP)) {
            vertical_moviment--;
            //System.out.println("Moveu para cima!");
        }
        if (input.getKeyPressed(KeyEvent.VK_S) || input.getKeyPressed(KeyEvent.VK_DOWN)) {
            vertical_moviment++;
            //System.out.println("Moveu para baixo!");
        }
        if (input.getKeyPressed(KeyEvent.VK_A)  || input.getKeyPressed(KeyEvent.VK_LEFT)) {
            horizontal_moviment--;
            //System.out.println("Moveu para esquerda!");
        }
        if (input.getKeyPressed(KeyEvent.VK_D)  || input.getKeyPressed(KeyEvent.VK_RIGHT)) {
            horizontal_moviment++;
            //System.out.println("Moveu para direita!");
        }
        hero.move(horizontal_moviment, vertical_moviment, 5 * GAME_DELTA_TIME);     // Velocidade de 5 Tiles por segundo
        
        if (input.getKeyPressed(KeyEvent.VK_ESCAPE) && !was_escape_pressed) {
            this.pauseGame();
            mainFrame.pauseGame();
            System.out.println("Game pausado!");
        }
        
        was_escape_pressed = input.getKeyPressed(KeyEvent.VK_ESCAPE);
        
        for(Tile t : tileMap.getTiles()) {
            if(t.getType() == TileType.WALL) {
                hero.checkCharacterCollision(t);
            } else if(t.getType() == TileType.DOOR) {
                if(hero.checkCharacterCollision(t)) {
                    if(hero.hasKeyFor(t.getKeyId())) {
                        t.changeType(TileType.FLOOR);
                        MapRegionManager.getMapRegionManagerInstance().unlockRegion(t.getKeyId());
                    } 
                }
                
            }
        }
        
        for(Item item : itemMap.getItems()) {
            if(item.getType() == ItemType.KEY) {
                if(hero.checkCharacterCollision(item)) {
                    hero.pickUpKey((Key) item);    
                    item.setVisible(false);
                    // A região já está sendo desbloqueada pelos Tiles no for anterior
                } 
            }
        }
        
        for(GameCharacter entity : entityMap.getEntities()) {
            if(entity instanceof Enemy enemy) {
                enemy.update(this);
            }
        }
        
        camera.follow(hero.getPosition());
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
    
    public ItemMap getItemMap() {
        return itemMap;
    }
    
    public EntityMap getEntityMap() {
        return entityMap;
    }
    
    public MapRegionManager getMapRegionManager() {
        return mapRegionManager;
    }

    public LevelData getLevelData() {
        return mapData;
    }
    
    public Hero getHero() {
        return hero;
    }
    
    public GameTime getGameTime() {
        return gameTime;
    }

    public int getMapWidth() {
        return map_width;
    }

    public int getMapHeight() {
        return map_height;
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
