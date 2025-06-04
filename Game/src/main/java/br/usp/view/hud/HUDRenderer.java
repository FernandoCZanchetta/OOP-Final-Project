/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.view.hud;

import br.usp.core.GameEngine;
import br.usp.core.GameTime;
import br.usp.model.GameObject;
import br.usp.model.entity.Hero;
import br.usp.model.items.Key;
import br.usp.view.SpriteManager;
import br.usp.view.render.GraphicsAPI;
import br.usp.view.render.Renderable;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.util.HashSet;
import java.util.Set;
import javax.vecmath.Point2d;

/**
 *
 * @author Fernando
 */
public class HUDRenderer implements Renderable {
    private static final long HEART_BLINK_DURATION_MS = 1000;
    private Hero hero = GameEngine.getGameEngineInstance().getHero();
    private GameTime gameTime = GameEngine.getGameEngineInstance().getGameTime();

    private void renderHearts(GraphicsAPI g) {
        int maxHealth = hero.getMaxHp();
        int currentHealth = hero.getCurrentHp();

        int heartSize = 32;
        int spacing = 5;
        int x = 10;
        int y = 10;
        
        for (int i = 0; i < maxHealth; i++) {
            if(shouldBlinkHearts() && i >= currentHealth) continue;
                
            String spriteKey = (i < currentHealth) ? "heart_full" : "heart_empty";
            Image heart = SpriteManager.getSprite(spriteKey);
            g.drawImage(heart, new Point2d(x + i * (heartSize + spacing), y), heartSize, heartSize);
        }
    }
    
    private void renderKeys(GraphicsAPI g) {
        Set<GameObject> objs = hero.getInventory();
        Set<Key> keys = new HashSet<>();

        for(GameObject go : objs) {
            if(go instanceof Key k) {
                keys.add(k);
            }
        }
        
        
        int keySize = 32;
        int spacing = 5;
        int screenPadding = 10;
        int y = 10;
        
        // Começar a desenhar da direita para a esquerda
        int x = GameEngine.getGameEngineInstance().getMainFrame().getWidth() - screenPadding - keySize - spacing;

        for (Key key : keys) {
            String id = key.getUnlocksRegionId();
            String spriteKey = id.toLowerCase() + "_key" ;
            Image keySprite = SpriteManager.getSprite(spriteKey);

            if (keySprite != null) {
                g.drawImage(keySprite, new Point2d(x, y), keySize, keySize);
                x -= keySize + spacing;
            }
        }
    }
    
    private void renderTimer(GraphicsAPI g) {
        long totalSeconds = gameTime.getElapsedTimeSeconds();
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;

        String timeText = String.format("%02d:%02d", minutes, seconds);

        Font font = new Font("Century Gothic", Font.BOLD, 24);

        FontMetrics metrics = g.getFontMetrics(font);
        int textWidth = metrics.stringWidth(timeText);
        int iconSize = 24;

        int frameWidth = GameEngine.getGameEngineInstance().getMainFrame().getWidth();

        // Total width: icon + space + text
        int totalWidth = iconSize + 8 + textWidth;

        int x = (frameWidth - totalWidth) / 2;
        int y = 10 + metrics.getAscent();  // Y para alinhar com topo

        // Sombra do texto (levemente deslocada)
        Color shade_color = Color.BLACK;
        g.drawText(timeText, new Point2d(x + iconSize + 9, y + 1), shade_color, font, true);

        // Texto branco sobre a sombra
        Color text_color = Color.WHITE;
        g.drawText(timeText, new Point2d(x + iconSize + 8, y), text_color, font, true);

        // Ícone do relógio
        Image clock = SpriteManager.getSprite("clock");
        if (clock != null) {
            g.drawImage(clock, new Point2d(x, y - iconSize + 5), iconSize, iconSize);
        }
    }
    
    public boolean shouldBlinkHearts() {
        long elapsed = System.currentTimeMillis() - hero.getLastDamageTime();
        return elapsed < HEART_BLINK_DURATION_MS && (elapsed / 200) % 2 == 0; // pisca a cada 200ms
    }
    
    @Override
    public void render(GraphicsAPI g) {
        this.renderHearts(g);
        this.renderKeys(g);
        this.renderTimer(g);
    }
    
}
