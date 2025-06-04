/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.usp.io;

import javax.swing.JFrame;

import javax.vecmath.Point2d;
import javax.vecmath.Point2i;

/**
 *
 * @author Fernando
 */
public interface InputAPI {
    public Point2d getMousePos(JFrame screen);
    
    public Point2i getMouseTile();

    public boolean getMouseRightClick();

    public boolean getMouseLeftClick();

    public boolean getKeyPressed(int key_code);

    public void subscribeToDND(DNDSubscriber subscriber);

    public void unsubscribeToDND(DNDSubscriber subscriber);
}
