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
import javax.swing.JTextPane;

/**
 *
 * @author Fernando
 */
public class TextFactory {
    private static final Font DEFAULT_FONT = new Font("Century Gothic", Font.BOLD, 32);
    private static final Font TITLE_FONT = new Font("Century Gothic", Font.BOLD, 40);
    private static final Color DEFAULT_BG = Color.LIGHT_GRAY;
    private static final Color ALTERNATIVE_BG = Color.BLACK;
    private static final Color DEFAULT_FG = Color.RED;
    private static final Color ALTERNATIVE_FG = Color.GREEN;
    
    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(DEFAULT_FONT);
        label.setBackground(DEFAULT_BG);
        label.setForeground(DEFAULT_FG);
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return label;
    }
    
    public static JLabel createTitle(String text) {
        JLabel label = new JLabel(text);
        label.setFont(TITLE_FONT);
        label.setBackground(DEFAULT_BG);
        label.setForeground(DEFAULT_FG);
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return label;
    }
    
    public static JLabel createAlternativeTitle(String text) {
        JLabel label = new JLabel(text);
        label.setFont(TITLE_FONT);
        label.setBackground(ALTERNATIVE_BG);
        label.setForeground(ALTERNATIVE_FG);
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return label;
    }
    
    public static JTextPane createTextPane(String text) {
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setText(text);
        textPane.setFont(DEFAULT_FONT);
        textPane.setBackground(ALTERNATIVE_BG);
        textPane.setForeground(ALTERNATIVE_FG);
        textPane.setEditable(false);
        textPane.setOpaque(false);
        textPane.setHighlighter(null);
        textPane.setAlignmentX(CENTER_ALIGNMENT);
        return textPane;
    }
}
