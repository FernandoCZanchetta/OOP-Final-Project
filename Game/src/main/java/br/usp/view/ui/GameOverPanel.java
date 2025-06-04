/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.view.ui;

import br.usp.view.layout.MainFrame;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Fernando
 */
public class GameOverPanel extends JPanel {
    
    public GameOverPanel(MainFrame mainFrame) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.DARK_GRAY);
        //TO-DO: DEIXAR BACKGOURND E TALS BONITO

        JLabel label = LabelFactory.createLabel("GAME OVER!");
                
        JButton retryButton = ButtonFactory.createButton("Recomeçar");
        JButton startButton = ButtonFactory.createButton("Novo Jogo");

        JButton loadButton = ButtonFactory.createButton("Carregar Jogo");

        JButton exitButton = ButtonFactory.createButton("Sair");


        retryButton.addActionListener(e -> mainFrame.retryLevel());
        startButton.addActionListener(e -> mainFrame.startGame());
        loadButton.addActionListener(e -> mainFrame.loadGame());
        exitButton.addActionListener(e -> System.exit(0));

        this.add(Box.createVerticalGlue());
        this.add(label);
        this.add(Box.createRigidArea(new Dimension(0, 70)));
        this.add(retryButton);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(startButton);
        this.add(Box.createRigidArea(new Dimension(0, 50)));
        this.add(loadButton);
        this.add(Box.createRigidArea(new Dimension(0, 50)));
        this.add(exitButton);
        this.add(Box.createVerticalGlue());
    }
}
