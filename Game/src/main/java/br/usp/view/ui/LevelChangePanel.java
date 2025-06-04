/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.view.ui;

import br.usp.view.layout.MainFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Fernando
 */
public class LevelChangePanel extends JPanel {
    
    public LevelChangePanel(MainFrame mainFrame) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.DARK_GRAY);
        //TO-DO: DEIXAR BACKGOURND E TALS BONITO

        JLabel label = LabelFactory.createLabel("Parabéns! Fase Completa!");
                
        JButton nextLeveButton = ButtonFactory.createButton("Próxima Fase");
        JButton startButton = ButtonFactory.createButton("Novo Jogo");

        JButton saveButton = ButtonFactory.createButton("Salvar Jogo");
        JButton loadButton = ButtonFactory.createButton("Carregar Jogo");

        JButton exitButton = ButtonFactory.createButton("Sair");


        nextLeveButton.addActionListener(e -> mainFrame.startNextLevel());
        startButton.addActionListener(e -> mainFrame.startGame());
        saveButton.addActionListener(e -> mainFrame.saveGame());
        loadButton.addActionListener(e -> mainFrame.loadGame());
        exitButton.addActionListener(e -> System.exit(0));

        this.add(Box.createVerticalGlue());
        this.add(label);
        this.add(Box.createRigidArea(new Dimension(0, 70)));
        this.add(nextLeveButton);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(startButton);
        this.add(Box.createRigidArea(new Dimension(0, 50)));
        this.add(saveButton);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(loadButton);
        this.add(Box.createRigidArea(new Dimension(0, 50)));
        this.add(exitButton);
        this.add(Box.createVerticalGlue());
    }
}
