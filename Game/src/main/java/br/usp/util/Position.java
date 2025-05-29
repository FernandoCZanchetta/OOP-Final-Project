/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.util;

import static br.usp.util.GameConstants.*;
import javax.vecmath.Point2i;

/**
 *
 * @author Fernando
 */
public class Position {
    private int row;
    private int column;
    private Point2i coordinates;
    
    private int previousRow;
    private int previousColumn;
    
    public Position(int row, int column) {
        this.setPosition(row, column);
    }
    
    public Position(Point2i p) {
        this.setPosition(p.x, p.y);
    }
    
    public Position(Position p) {
        this.setPosition(p.row, p.column);
    }
    
    public boolean setPosition(int row, int column) {
        if (row < 0 || row >= MAX_MAP_HEIGHT)
            return false;
        previousRow = this.row;
        this.row = row;

        if (column < 0 || column >= MAX_MAP_WIDTH)
            return false;
        previousColumn = this.column;
        this.column = column;

        return true;
    }

    public Point2i getCoordinates() {
        return coordinates;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPreviousRow() {
        return previousRow;
    }

    public int getPreviousColumn() {
        return previousColumn;
    }
    
    public boolean returnToPreviousPosition() {
        return this.setPosition(previousRow, previousColumn);
    }
    
    public boolean copy(Position p) {
        return this.setPosition(p.getRow(), p.getColumn());
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        
        if (!(o instanceof Position p)) return false;
        
        return (row == p.getRow() && column == p.getColumn());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.row;
        hash = 11 * hash + this.column;
        return hash;
    }
    
    public boolean move(int dx, int dy) {
        return setPosition(this.row + dx, this.column + dy);
    }
    
    public boolean moveUp() {
        return move(-1, 0);
    }

    public boolean moveDown() {
        return move(1, 0);
    }

    public boolean moveLeft() {
        return move(0, -1);
    }

    public boolean moveRight() {
        return move(0, 1);
    }
}
