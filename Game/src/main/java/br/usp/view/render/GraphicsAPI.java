/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.view.render;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.vecmath.Point2d;

/**
 *
 * @author Fernando
 */
public interface GraphicsAPI {
    public void createBuffer(int width, int height);
    
    public FontMetrics getFontMetrics(Font font);

    public void drawRect(Point2d position, int width, int height, Color color);

    public void drawSprite(Image image, Point2d position);

    public void drawImage(Image image, Point2d position, int imageWidth, int imageHeight);
    
    public void drawText(String text, Point2d position, Color color, Font font, boolean isFixed);

    public void flushBuffer(Graphics2D g);
}
