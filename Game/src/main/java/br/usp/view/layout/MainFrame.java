/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.view.layout;

import br.usp.core.GameEngine;
import br.usp.model.level.LevelData;
import br.usp.model.level.LevelLoader;
import br.usp.model.level.LevelManager;
import br.usp.view.GamePanel;
import br.usp.view.ui.MainMenuPanel;
import br.usp.view.ui.PauseMenuPanel;
import java.awt.CardLayout;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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

    public void loadGame() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Carregar Jogo");
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                LevelData loadedData = LevelLoader.laodSave(selectedFile.getAbsolutePath());
                if (loadedData != null) {
                    //engine.loadLevelFromData(loadedData); // Você implementa isso
                    System.out.println("Daria para carregar um jogo IMPLEMENTAR NA ENGINE!");
                    JOptionPane.showMessageDialog(null, "Jogo carregado com sucesso!");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar: " + ex.getMessage());
            }
        }
    }
    
    public void saveGame() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar Jogo");
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                LevelData levelData = LevelData.generateLevelData(
                    engine.getTileMap(),
                    engine.getEntityMap(), // Lista de GameCharacters
                    engine.getItemMap(),
                    engine.getMapWidth(),
                    engine.getMapHeight()
                );
                LevelManager.saveLevel(levelData, fileToSave.getAbsolutePath());
                JOptionPane.showMessageDialog(null, "Jogo salvo com sucesso!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage());
            }
        }
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
