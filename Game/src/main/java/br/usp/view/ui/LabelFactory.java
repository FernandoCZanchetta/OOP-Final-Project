/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.view.ui;

import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 *
 * @author Fernando
 */
public class LabelFactory {
    private static final Font DEFAULT_FONT = new Font("Century Gothic", Font.BOLD, 32);
    private static final Color DEFAULT_BG = Color.LIGHT_GRAY;
    private static final Color DEFAULT_FG = Color.RED;
    
    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(DEFAULT_FONT);
        label.setBackground(DEFAULT_BG);
        label.setForeground(DEFAULT_FG);
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return label;
    }
}
