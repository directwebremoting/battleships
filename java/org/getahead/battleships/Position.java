package org.getahead.battleships;

import java.util.Random;

/**
 * A board position
 */
public class Position {

    public Position(int row, int col) {
	this.row = row;
	this.col = col;
    }

    public Position() {
	setRow(random.nextInt(MAX_ROW));
	setCol(random.nextInt(MAX_COL));
    }

    public void setCol(int col) {
	if (col >= MAX_COL) throw new IllegalArgumentException("Column to big");
	if (col < 0) throw new IllegalArgumentException("Column to small");

	this.col = col;
    }

    public int getCol() {
	return col;
    }

    public void setRow(int row) {
	if (row >= MAX_ROW) throw new IllegalArgumentException("Row to big");
	if (row < 0) throw new IllegalArgumentException("Row to small");

	this.row = row;
    }

    public int getRow() {
	return row;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)  return false;
	if (obj == this)  return true;

	if (!this.getClass().equals(obj.getClass())) {
	    return false;
	}

	Position that = (Position) obj;

	return this.getRow() == that.getRow() && this.getCol() == that.getCol();
    }

    @Override
    public int hashCode() {
	return getRow() * getCol();
    }

    @Override
    public String toString() {
	return "(" + row + "," + col + ")";
    }

    private int row;

    private int col;

    protected static final int MAX_ROW = 7;

    protected static final int MAX_COL = 8;

    public static final Position FULL_GRID = new Position(MAX_ROW, MAX_COL);

    protected static Random random = new Random();
}
