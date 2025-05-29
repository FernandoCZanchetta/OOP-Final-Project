/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.io;

import static br.usp.util.GameConstants.*;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.vecmath.Point2d;
import javax.vecmath.Point2i;

/**
 *
 * @author Fernando
 */
public class SwingInputAPI extends JFrame implements InputAPI, KeyListener, MouseListener, DropTargetListener {
    private final Map<Integer, Boolean> keyboard_map = new HashMap<>();
    private Point2d mousePos = new Point2d(0, 0);
    private boolean leftClick = false;
    private boolean rightClick = false;
    private DropTarget drop_target;
    private ArrayList<DNDSubscriber> dnd_subscribers = new ArrayList<>();

    public void attachToComponent(JComponent component) {
        component.addKeyListener(this);
        component.addMouseListener(this);

        new java.awt.dnd.DropTarget(component, this);
        component.setFocusable(true);
        component.requestFocusInWindow();
    }

    @Override
    public Point2d getMousePos() {
        Point pos = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(pos, this);
        return new Point2d(pos.getX(), pos.getY());
    }
    
    @Override
    public Point2i getMouseTile() {
        Point pos = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(pos, this);
        return new Point2i((int)(pos.getX() / TILE_SIZE), (int)(pos.getY() / TILE_SIZE));
    }

    @Override
    public boolean getMouseRightClick() {
        return rightClick;
    }

    @Override
    public boolean getMouseLeftClick() {
        return leftClick;
    }

    @Override
    public boolean getKeyPressed(int keyCode) {
        if(!keyboard_map.containsKey(keyCode)) {
            return false;
        }
        return keyboard_map.get(keyCode);
    }

    @Override
    public void subscribeToDND(DNDSubscriber subscriber) {
        dnd_subscribers.add(subscriber);
    }

    @Override
    public void unsubscribeToDND(DNDSubscriber subscriber) {
        dnd_subscribers.remove(subscriber);
    }

    // KeyListener
    public void keyPressed(KeyEvent e) {
        keyboard_map.put(e.getKeyCode(), true);
    }

    public void keyReleased(KeyEvent e) {
        keyboard_map.put(e.getKeyCode(), false);
    }

    public void keyTyped(KeyEvent e) {}

    // MouseListener
    public void mousePressed(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON3: {
                rightClick = true;
                break;
            }
            case MouseEvent.BUTTON1: {
                leftClick = true;
                break;
            }
            default: break;
        }
    }

    public void mouseReleased(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON3: {
                rightClick = false;
                break;
            }
            case MouseEvent.BUTTON1: {
                leftClick = false;
                break;
            }
            default: break;
        }
    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}

    // MouseMotionListener
    public void mouseMoved(MouseEvent e) {
        mousePos.set(e.getX(), e.getY());
    }

    public void mouseDragged(MouseEvent e) {
        mousePos.set(e.getX(), e.getY());
    }

    // DropTargetListener
    @Override
    public synchronized void drop(java.awt.dnd.DropTargetDropEvent dtde) {
        Transferable t = dtde.getTransferable();
        if (t.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
            dtde.acceptDrop(DnDConstants.ACTION_MOVE);
            try {
                ArrayList<File> data = (ArrayList<File>) t.getTransferData(DataFlavor.javaFileListFlavor);
                for (File file : data) {
                    for (DNDSubscriber sub : dnd_subscribers) {
                        sub.receiveDNDFile(file);
                    }
                }
                dtde.dropComplete(true);
            } catch (UnsupportedFlavorException ufe) {
              System.err.printf("Issue with flavor type %s\n", ufe.getMessage());
            } catch (IOException ioe) {
              System.err.println("Issue with IO: " + ioe.getMessage());
            }
        }
        
        dtde.dropComplete(false);
    }

    public void dragEnter(java.awt.dnd.DropTargetDragEvent dtde) {
        if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
            dtde.acceptDrag(DnDConstants.ACTION_MOVE);
        } else {
            dtde.rejectDrag();
        }   
    }
    public void dragOver(java.awt.dnd.DropTargetDragEvent dtde) {
        dragEnter(dtde);
    }
    public void dropActionChanged(java.awt.dnd.DropTargetDragEvent dtde) {}
    public void dragExit(java.awt.dnd.DropTargetEvent dte) {}
}
