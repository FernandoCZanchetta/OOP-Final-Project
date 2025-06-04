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
import javax.swing.JTextPane;

/**
 *
 * @author Fernando
 */
public class CreditsPanel extends JPanel {
    
    public CreditsPanel(MainFrame mainFrame) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.DARK_GRAY);
        //TO-DO: DEIXAR BACKGOURND E TALS BONITO

        JLabel title = TextFactory.createAlternativeTitle("Creditos!");
        
        JTextPane creditsText = TextFactory.createTextPane(
            """
            <html>
                <head>
                    <style>
                        body {
                            text-align: center;
                            font-family: Century Gothic;
                            font-size: 32pt;
                            color: white;
                            background: transparent;
                            margin: 0;
                            padding: 0;
                        }
                    </style>
                </head>
                <body>
                    <div style='text-align: center;'>
                        Desenvolvido por:<br><br>
                        Fernando Clarindo<br>Fernando Zanchetta<br>Ricardo Dias<br><br>
                    </div>
                </body>
            </html>
            """
        );
                
        JButton backButton = ButtonFactory.createButton("Voltar ao Menu");

        JButton exitButton = ButtonFactory.createButton("Sair");


        backButton.addActionListener(e -> mainFrame.returnToMenu());
        exitButton.addActionListener(e -> System.exit(0));

        this.add(Box.createVerticalGlue());
        this.add(Box.createRigidArea(new Dimension(0, 80)));
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(creditsText);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(backButton);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(exitButton);
        this.add(Box.createRigidArea(new Dimension(0, 80)));
        this.add(Box.createVerticalGlue());
    }
}
