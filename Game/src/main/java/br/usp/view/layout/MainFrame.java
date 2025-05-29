/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.view.layout;

import br.usp.core.GameEngine;
import br.usp.view.GamePanel;
import br.usp.view.ui.MainMenuPanel;
import br.usp.view.ui.PauseMenuPanel;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Fernando
 */
public class MainFrame extends JFrame {
    private final GameEngine engine = GameEngine.getGameEngineInstance();
    private CardLayout layout;
    private JPanel cardPanel;
    
    public static final String GAME_PANEL = "GAME";
    public static final String MENU_PANEL = "MENU";
    public static final String PAUSE_PANEL = "PAUSE";
    
    public MainFrame() {
        super("Tile Maze Game");

        layout = new CardLayout();
        cardPanel = new JPanel(layout);

        // Adiciona os painéis
        cardPanel.add(new MainMenuPanel(this), MENU_PANEL);
        cardPanel.add(new PauseMenuPanel(this), PAUSE_PANEL);
        cardPanel.add(new GamePanel(this), GAME_PANEL);

        this.setContentPane(cardPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void showPanel(String name) {
        layout.show(cardPanel, name);
    }

    public void startGame() {
        for (Component comp : cardPanel.getComponents()) {
            if (comp instanceof GamePanel) {
                cardPanel.remove(comp);
                break;
            }
        }
        
        GamePanel newGamePanel = new GamePanel(this);
        cardPanel.add(newGamePanel, GAME_PANEL);
        
        engine.startGame();
        showPanel(GAME_PANEL);
        
        newGamePanel.setFocusable(true);
        newGamePanel.requestFocusInWindow();
    }

    public void returnToMenu() {
        showPanel(MENU_PANEL);
    }
    
    public void pauseGame() {
        engine.pauseGame(); // Define estado como PAUSED
        showPanel(PAUSE_PANEL);
    }
    
    public void resumeGame() {
        engine.resumeGame(); // Define estado como RUNNING
        showPanel(GAME_PANEL);
    }
}
