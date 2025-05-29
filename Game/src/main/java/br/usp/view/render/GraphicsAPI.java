/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.view.render;

import br.usp.util.Position;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.vecmath.Point2i;

/**
 *
 * @author Fernando
 */
public interface GraphicsAPI {
    public void createBuffer(int width, int height);

    public void drawRect(Position position, int width, int height, Color color);

    public void drawSprite(Image image, Position position);

    public void drawText(String text, Position position, Color color, Font font);

    public void flushBuffer(Graphics2D g);
}
