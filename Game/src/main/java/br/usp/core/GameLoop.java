/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.core;

import javax.swing.JComponent;
import javax.swing.Timer;

/**
 *
 * @author Fernando
 */
public class GameLoop {
    private final Timer timer;
    private final JComponent panel;
    
    public GameLoop(GameEngine engine, JComponent panel, int fps) {
        this.panel = panel;
        this.timer = new Timer(1000 / fps, e -> {
            engine.update();
            panel.repaint();
        });
    }
    
    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }
    
    public boolean isRunning() {
        return timer.isRunning();
    }
}
