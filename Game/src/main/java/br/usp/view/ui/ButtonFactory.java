/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.view.ui;

import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 *
 * @author Fernando
 */
public class ButtonFactory {
    private static final Font DEFAULT_FONT = new Font("Century Gothic", Font.BOLD, 32);
    private static final Color DEFAULT_BG = Color.LIGHT_GRAY;
    private static final Color DEFAULT_FG = Color.BLACK;
    
    public static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(DEFAULT_FONT);
        button.setBackground(DEFAULT_BG);
        button.setForeground(DEFAULT_FG);
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return button;
    }
    
    public static JButton createTransparentButton(String text) {
        JButton button = createButton(text);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        return button;
    }
}
