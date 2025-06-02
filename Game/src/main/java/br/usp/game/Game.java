/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.usp.game;

import br.usp.core.GameEngine;
import javax.swing.SwingUtilities;

/**
 *
 * @author Fernando
 */
public class Game {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameEngine game = GameEngine.getGameEngineInstance();
            
            game.loadSprites();
            
            game.run();  
        });
    }
}
