/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.view.ui;

import br.usp.core.GameEngine;
import br.usp.io.ZipSaver;
import br.usp.model.GameObject;
import br.usp.model.entity.GameCharacter;
import br.usp.model.items.Item;
import br.usp.view.layout.MainFrame;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Fernando
 */
public class DebuggerPanel extends JPanel {
    private JComboBox<GameObject> objectSelector;
    
    public DebuggerPanel(MainFrame mainFrame) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.DARK_GRAY);
        //TO-DO: DEIXAR BACKGOURND E TALS BONITO

        JLabel label = TextFactory.createLabel("DEBBUGER MENU");
        
        objectSelector = ComboBoxFactory.createComboBox();
        refreshObjectList();
             
        JButton resumeButton = ButtonFactory.createButton("Continuar Jogo");
        JButton startButton = ButtonFactory.createButton("Novo Jogo");
        
        JButton refreshButton = ButtonFactory.createButton("Atualizar Lista");
        JButton saveObjectButton = ButtonFactory.createButton("Salvar Objeto JSON");
        JButton zipObjectButton = ButtonFactory.createButton("Zipar Objeto JSON");
        
        JButton exitButton = ButtonFactory.createButton("Sair");

        resumeButton.addActionListener(e -> mainFrame.resumeGame());
        startButton.addActionListener(e -> mainFrame.startGame());
        refreshButton.addActionListener(e -> refreshObjectList());
        saveObjectButton.addActionListener(e -> this.saveSelectedObjectAsJson());
        zipObjectButton.addActionListener(e -> this.zipLastSavedObject());
        exitButton.addActionListener(e -> System.exit(0));

        this.add(Box.createVerticalGlue());
        this.add(label);
        this.add(Box.createRigidArea(new Dimension(0, 80)));
        this.add(resumeButton);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(startButton);
        this.add(Box.createRigidArea(new Dimension(0, 70)));
        this.add(objectSelector);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(refreshButton);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(saveObjectButton);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(zipObjectButton);
        this.add(Box.createRigidArea(new Dimension(0, 70)));
        this.add(exitButton);
        this.add(Box.createVerticalGlue());
    }
    
    private void refreshObjectList() {
        objectSelector.removeAllItems();

        for (GameCharacter entity : GameEngine.getGameEngineInstance().getEntityMap().getEntities()) {
            objectSelector.addItem(entity);
        }
        for (Item item : GameEngine.getGameEngineInstance().getItemMap().getItems()) {
            objectSelector.addItem(item);
        }
    }
    
    private void saveSelectedObjectAsJson() {
        GameObject selected = (GameObject) objectSelector.getSelectedItem();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Nenhum objeto selecionado.");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar Objeto JSON");
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File saveFile = fileChooser.getSelectedFile();
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(saveFile, selected.serialize());
                JOptionPane.showMessageDialog(this, "Objeto salvo com sucesso!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
            }
        } else if (result == JFileChooser.CANCEL_OPTION) {
            return;
        }
    }

    private void zipLastSavedObject() {
        File jsonFile = null;
        
        JFileChooser openFileChooser = new JFileChooser();
        openFileChooser.setDialogTitle("Qual JSON deseja zipar?");
        int openResult = openFileChooser.showOpenDialog(this);
        if (openResult == JFileChooser.APPROVE_OPTION) {
            jsonFile = openFileChooser.getSelectedFile();
            if(!jsonFile.getPath().endsWith(".json")) {
                JOptionPane.showMessageDialog(this, "Não é um arquivo JSON!");
                return;
            }
        } else if (openResult == JFileChooser.CANCEL_OPTION) {
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar ZIP do Objeto");
        int result = fileChooser.showSaveDialog(this);
        if (result != JFileChooser.APPROVE_OPTION) return;

        File zipFile = fileChooser.getSelectedFile();

        ZipSaver.zipJson(jsonFile.getAbsolutePath(), zipFile.getAbsolutePath());
        
        JOptionPane.showMessageDialog(this, "ZIP criado com sucesso!");
    }
}
