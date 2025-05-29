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
import javax.swing.JPanel;

/**
 *
 * @author Fernando
 */
public class PauseMenuPanel extends JPanel {
    
    public PauseMenuPanel(MainFrame mainFrame) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.setBackground(Color.DARK_GRAY);
            //TO-DO: DEIXAR BACKGOURND E TALS BONITO

            
            JButton resumeButton = ButtonFactory.createButton("Continuar Jogo");
            JButton startButton = ButtonFactory.createButton("Novo Jogo");
            
            JButton saveButton = ButtonFactory.createButton("Salvar Jogo");
            JButton loadButton = ButtonFactory.createButton("Carregar Jogo");
            
            JButton exitButton = ButtonFactory.createButton("Sair");
            //TO-DO: RANKING E NOVOS BOTÕES?
            //TO-DO: DEIXAR BOTÕES BONITOS (startButton.setBorder(new Border());) lá na FACTORY

            
            resumeButton.addActionListener(e -> mainFrame.resumeGame());
            startButton.addActionListener(e -> mainFrame.startGame());
            //saveButton.addActionListener(e -> ?()); IMPLEMENTAR LÓGICA PARA SALVAR FASE
            //loadButton.addActionListener(e -> ?()); IMPLEMENTAR LÓGICA PARA CARREGAR FASE
            exitButton.addActionListener(e -> System.exit(0));

            this.add(Box.createVerticalGlue());
            this.add(resumeButton);
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
