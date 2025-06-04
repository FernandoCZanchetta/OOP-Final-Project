/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.view.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;

/**
 *
 * @author Fernando
 */
public class ComboBoxFactory {
    private static final Font DEFAULT_FONT = new Font("Century Gothic", Font.BOLD, 32);
    private static final Color DEFAULT_BG = Color.ORANGE;
    private static final Color DEFAULT_FG = Color.BLACK;
    
    public static JComboBox createComboBox() {
        JComboBox comboBox = new JComboBox<>();
        
        // Tamanho fixo
        comboBox.setMaximumSize(new Dimension(750, 50));
        comboBox.setPreferredSize(new Dimension(750, 50));

        // Estilo visual
        comboBox.setFont(DEFAULT_FONT);
        comboBox.setBackground(DEFAULT_BG);
        comboBox.setForeground(DEFAULT_FG);
        comboBox.setBorder(new LineBorder(Color.BLACK, 5));
        
        return comboBox;
    }
}
