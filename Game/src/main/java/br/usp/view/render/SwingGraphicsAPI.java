/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.view.render;

import static br.usp.util.GameConstants.TILE_SIZE;
import static br.usp.util.GameConstants.WINDOW_HEIGHT;
import static br.usp.util.GameConstants.WINDOW_WIDTH;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.vecmath.Point2d;

/**
 *
 * @author Fernando
 */
public class SwingGraphicsAPI implements GraphicsAPI {
    // GraphicsAPI
    private Graphics2D bufferGraphics;
    private BufferedImage buffer;
    private Graphics current_frame;
    
    public void setGraphicsContext(Graphics2D g) {
        this.bufferGraphics = g;
    }

    @Override
    public FontMetrics getFontMetrics(Font font) {
        return bufferGraphics.getFontMetrics(font);
    }
    
    @Override
    public void createBuffer(int width, int height) {
        if (buffer == null || buffer.getWidth() != width || buffer.getHeight() != height) {
            buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            bufferGraphics = buffer.createGraphics();

            // Configura antialias e rendering quality
            bufferGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            bufferGraphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        }
        // Limpa o buffer
        bufferGraphics.setColor(Color.BLACK);
        bufferGraphics.fillRect(0, 0, width, height);
    }

    @Override
    public void drawRect(Point2d position, int width, int height, Color color) {
        if (bufferGraphics != null) {
            bufferGraphics.setColor(color);
            Point2d camera_origin = new Point2d(Camera.getCameraInstance().getPosition());
            Point2d win_center = new Point2d(WINDOW_WIDTH, WINDOW_HEIGHT);
            win_center.scale(0.5 / TILE_SIZE);
            camera_origin.sub(win_center);
            Point2d pos = new Point2d(position);
            pos.sub(camera_origin);
            bufferGraphics.fillRect((int) Math.round(pos.getX()) * TILE_SIZE, (int) Math.round(pos.getY()) * TILE_SIZE, width, height);
        }
    }

    @Override
    public void drawSprite(Image image, Point2d position) {
        if (image != null && bufferGraphics != null) {
            Point2d camera_origin = new Point2d(Camera.getCameraInstance().getPosition());
            Point2d win_center = new Point2d(WINDOW_WIDTH, WINDOW_HEIGHT);
            win_center.scale(0.5 / TILE_SIZE);
            camera_origin.sub(win_center);
            Point2d pos = new Point2d(position);
            pos.sub(camera_origin);
            bufferGraphics.drawImage(image, (int) Math.round(pos.getX()) * TILE_SIZE, (int) Math.round(pos.getY()) * TILE_SIZE, null);
        }
    }
    
    @Override
    public void drawImage(Image image, Point2d position, int imageWidth, int imageHeight) {
        if (image != null && bufferGraphics != null) {
            bufferGraphics.drawImage(image, (int) position.getX(), (int) position.getY(), imageWidth, imageHeight, null);
        }
    }

    @Override
    public void drawText(String text, Point2d position, Color color, Font font, boolean isFixed) {
        if (bufferGraphics != null) {
            bufferGraphics.setColor(color);
            bufferGraphics.setFont(font);
            if(!isFixed) {
                Point2d camera_origin = new Point2d(Camera.getCameraInstance().getPosition());
                Point2d win_center = new Point2d(WINDOW_WIDTH, WINDOW_HEIGHT);
                win_center.scale(0.5 / TILE_SIZE);
                camera_origin.sub(win_center);
                Point2d pos = new Point2d(position);
                pos.sub(camera_origin);
                bufferGraphics.drawString(text, (int) Math.round(pos.getX()), (int) Math.round(pos.getY()));
            } else {
                bufferGraphics.drawString(text, (int) position.getX(), (int) position.getY());
            }
            
        }
    }

    @Override
    public void flushBuffer(Graphics2D g) {
        if (buffer != null && g != null) {
            g.drawImage(buffer, 0, 0, null);
        }
    }
}