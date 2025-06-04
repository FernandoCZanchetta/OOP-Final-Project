/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.core;

import br.usp.io.DNDObjectSpawner;
import br.usp.io.SwingInputAPI;
import br.usp.model.GameObject;
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
import br.usp.model.map.Portal;
import br.usp.model.map.Tile;
import br.usp.model.map.TileMap;
import br.usp.model.map.TileType;
import static br.usp.util.GameConstants.GAME_DELTA_TIME;
import static br.usp.util.GameConstants.TILE_SIZE;
import br.usp.view.SpriteManager;
import br.usp.view.layout.MainFrame;
import br.usp.view.render.Camera;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.vecmath.Point2d;

/**
 *
 * @author Fernando
 */
public class GameEngine {
    //Singleton Instance for GameEngine
    private static GameEngine instance = null;
    
    private GameState gameState = GameState.MAIN_MENU;
    private SwingInputAPI input;
    private DNDObjectSpawner spawner;
    private MainFrame mainFrame;
    private Camera camera;
    private MapRegionManager mapRegionManager;
    private TileMap tileMap;
    private ItemMap itemMap;
    private EntityMap entityMap;
    private LevelData mapData;
    private Hero hero;
    
    private final GameTime gameTime;
    private int currentLevel = 1;
    private int map_width;
    private int map_height;
    private Point2d camera_initial_position;
    
    private boolean was_escape_pressed;
    private boolean was_delete_pressed;
    
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
        /*Game Character Sprites*/
        SpriteManager.loadSprite("hero_steady", "sprites/hero_steady.png");
        
        /*Tiles' Sprites*/
        SpriteManager.loadSprite("wall", "sprites/tiles/wall.png");
        SpriteManager.loadSprite("floor", "sprites/tiles/floor.png");
        SpriteManager.loadSprite("door", "sprites/tiles/door.png");
        SpriteManager.loadSprite("portal", "sprites/tiles/portal.png");
        
        /*Items Sprites*/
        SpriteManager.loadSprite("heart", "sprites/items/heart.png");
            /*Keys' Sprites*/
            SpriteManager.loadSprite("yellow_key", "sprites/items/keys/yellow_key.png");
            SpriteManager.loadSprite("red_key", "sprites/items/keys/red_key.png");
            SpriteManager.loadSprite("blue_key", "sprites/items/keys/blue_key.png");
            SpriteManager.loadSprite("green_key", "sprites/items/keys/green_key.png");
        
        /*Enemies Sprites*/
        SpriteManager.loadSprite("melee_enemy", "sprites/enemies/melee_enemy.png");
        
        /*HUD Sprites*/
        SpriteManager.loadSprite("heart_full", "sprites/hud/heart_full.png");
        SpriteManager.loadSprite("heart_empty", "sprites/hud/heart_empty.png");
        SpriteManager.loadSprite("clock", "sprites/hud/clock.png");
        
    }
    
    public void run() {
        try {
            LevelManager.preloadAllMaps("maps");
        } catch (IOException ex) {
            System.out.println("Problema na leitura dos arquivos dos mapas!");
        }
        this.mapData = LevelManager.getMapData("map01");
        this.tileMap = new TileMap(new Dimension(mapData.getWidth(), mapData.getHeight()));
        this.itemMap = new ItemMap();
        this.entityMap = new EntityMap();
        this.mapRegionManager = new MapRegionManager();
        
        this.map_width = mapData.getWidth();
        this.map_height = mapData.getHeight();
        
        this.tileMap.loadFromData(mapData, mapRegionManager);
        this.itemMap.loadFromData(mapData, tileMap, mapRegionManager);
        this.entityMap.loadFromData(mapData, tileMap, mapRegionManager);
        
        this.hero = Hero.getHeroInstace();
        
        
        this.input = SwingInputAPI.getInputAPIInstance();
        this.spawner = new DNDObjectSpawner(this.input);
        this.spawner.start();
        
        this.mainFrame = new MainFrame();
        this.camera = new Camera(this.map_width, this.map_height);
        
        startGame();
    }
    
    public void loadMap(LevelData data) {
        this.mapData = data;
        
        this.tileMap = new TileMap(new Dimension(mapData.getWidth(), mapData.getHeight()));
        this.itemMap = new ItemMap();
        this.entityMap = new EntityMap();
        this.mapRegionManager = new MapRegionManager();
        
        this.map_width = mapData.getWidth();
        this.map_height = mapData.getHeight();
        
        this.tileMap.loadFromData(mapData, mapRegionManager);
        this.itemMap.loadFromData(mapData, tileMap, mapRegionManager);
        this.entityMap.loadFromData(mapData, tileMap, mapRegionManager);
        
        // Atualiza a instância do hero
        this.hero = Hero.getHeroInstace();
        
        this.camera = new Camera(this.map_width, this.map_height);
        
        // FUTURAMENTE GUARDAR O CRONOMETRO JUNTO NO LEVEL
        startGame();
    }
    
    public void loadSave(LevelData data) {
        this.mapData = data;
        
        this.tileMap = new TileMap(new Dimension(mapData.getWidth(), mapData.getHeight()));
        this.itemMap = new ItemMap();
        this.entityMap = new EntityMap();
        this.mapRegionManager = new MapRegionManager();
        
        this.map_width = mapData.getWidth();
        this.map_height = mapData.getHeight();
        this.currentLevel = mapData.getCurrentLevel();
        
        this.tileMap.loadFromData(mapData, mapRegionManager);
        this.itemMap.loadFromData(mapData, tileMap, mapRegionManager);
        this.entityMap.loadFromSave(mapData, tileMap, mapRegionManager);
        
        // Atualiza a instância do hero
        this.hero = Hero.getHeroInstace();
        
        this.camera = new Camera(this.map_width, this.map_height);
        
        // FUTURAMENTE GUARDAR O CRONOMETRO JUNTO NO LEVEL
        startGame();
    }
    
    public void loadGameObjectFromJson(String jsonFilePath, Point2d mapPos) {
        Point2d tempObjectTilePosition = new Point2d((int) Math.round(mapPos.x / TILE_SIZE) - 1, (int) Math.round(mapPos.y / TILE_SIZE) - 1);
  
        // Corrige o local de inserção baseado na camera
        tempObjectTilePosition.add(camera.getPosition());
        tempObjectTilePosition.sub(camera_initial_position);
        
        Point2d objectTilePosition = new Point2d((int) Math.round(tempObjectTilePosition.x), (int) Math.round(tempObjectTilePosition.y));
        
        GameObject obj = LevelManager.parseObjectFromJSON(jsonFilePath);
        
        // Decide onde inserir
        if (obj instanceof Item item) {
            item.getPosition().set(objectTilePosition);
            item.setVisible(true);
            this.getItemMap().addItem(item);
        } else if (obj instanceof GameCharacter entity) {
            entity.getPosition().set(objectTilePosition);
            this.getEntityMap().addEntity(entity);
        } else {
            System.err.println("Objeto desconhecido, não foi possível adicionar.");
        }
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
        loadSave(data);
    }
    
    public void nextGame(LevelData data) {
        this.gameState = GameState.RUNNING;
        loadMap(data);
    }
    
    public void resetGame() {
        gameTime.reset();
        gameState = GameState.MAIN_MENU;
    }
    
    public void handleHeroDeath() {
        this.pauseGame();
        this.getMainFrame().showPanel(MainFrame.GAME_OVER_PANEL);
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
        
        if (input.getKeyPressed(KeyEvent.VK_DELETE) && !was_delete_pressed) {
            this.pauseGame();
            mainFrame.showPanel(MainFrame.DEBUGGER_PANEL);
            System.out.println("Modo Debbuger!");
        }
        
        was_escape_pressed = input.getKeyPressed(KeyEvent.VK_ESCAPE);
        was_delete_pressed = input.getKeyPressed(KeyEvent.VK_DELETE);
        
        for(Tile t : tileMap.getTiles()) {
            if(null != t.getType()) switch (t.getType()) {
                case WALL:
                    hero.checkCharacterCollision(t);
                    break;
                case DOOR:
                    if(hero.checkCharacterCollision(t)) {
                        if(hero.hasKeyFor(t.getKeyId())) {
                            t.changeType(TileType.FLOOR);
                            MapRegionManager.getMapRegionManagerInstance().unlockRegion(t.getKeyId());
                        }
                    }   break;
                case PORTAL:
                    if(hero.checkCharacterCollision(t)) {
                        if(hero.canActivatePortal(((Portal) t).getRequiredKeys())) {
                            this.gameState = GameState.LEVEL_COMPLETE;
                            this.getMainFrame().showPanel(MainFrame.LEVEL_CHANGE_PANEL);
                        } else {
                            System.out.println("Hero não tem todas as chaves necessárias!");
                        }
                    }   break;
                default:
                    break;
            }
        }
        
        for(Item item : itemMap.getItems()) {
            if(item.getType() == ItemType.KEY) {
                if(hero.checkCharacterCollision(item)) {
                    hero.pickUpKey((Key) item);    
                    item.setVisible(false);
                    // A região já está sendo desbloqueada pelos Tiles no for anterior
                }
            } else if(item.getType() == ItemType.HEART) {
                if(hero.checkCharacterCollision(item)) {
                    if(item.isVisible() && hero.getCurrentHp() < hero.getMaxHp()) {
                        hero.heal(1);
                        item.setVisible(false);
                    }
                }
            }
        }
        
        for(GameCharacter entity : entityMap.getEntities()) {
            if(entity instanceof Enemy enemy) {
                enemy.update(this);
            }
        }
        
        camera.follow(hero.getPosition());
        if(camera_initial_position == null) {
            camera_initial_position = new Point2d(camera.getPosition());
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
    
    public int getCurrentLevel() {
        return currentLevel;
    }
    
    public void setCurrentLevel(int newCurrentLevel) {
        this.currentLevel = newCurrentLevel;
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
