/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.view.render;

import static br.usp.util.GameConstants.*;
import br.usp.util.Position;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.WindowConstants;
import javax.vecmath.Point2i;

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
    public void drawRect(Position position, int width, int height, Color color) {
        if (bufferGraphics != null) {
            bufferGraphics.setColor(color);
            bufferGraphics.fillRect(position.getColumn() * TILE_SIZE, position.getRow() * TILE_SIZE, width, height);
        }
    }

    @Override
    public void drawSprite(Image image, Position position) {
        if (image != null && bufferGraphics != null) {
            bufferGraphics.drawImage(image, position.getColumn(), position.getRow(), null);
        }
    }

    @Override
    public void drawText(String text, Position position, Color color, Font font) {
        if (bufferGraphics != null) {
            bufferGraphics.setColor(color);
            bufferGraphics.setFont(font);
            bufferGraphics.drawString(text, position.getColumn(), position.getRow());
        }
    }

    @Override
    public void flushBuffer(Graphics2D g) {
        if (buffer != null && g != null) {
            g.drawImage(buffer, 0, 0, null);
        }
    }
}
