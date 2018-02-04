package grid;

import java.util.HashSet;
import cell.Cell;
import javafx.scene.paint.Color;

public abstract class Grid {
	protected Cell[][] myGrid;
	private int myWidth;
	private int myHeight;
	
	public Grid (int width, int height) {
		myWidth = width;
		myHeight = height;
		myGrid = new Cell[myWidth][myHeight];
	}
	
	/*
	public Grid (Cell[][] grid) {
		myWidth = grid.length;
		myHeight = grid[0].length;
		myGrid = grid;
	}
	*/
	
	public Cell[][] getCellArray() {
		return myGrid;
	}
	
	public Cell[][] updateGrid() {
		Cell[][] nextGrid = new Cell[myWidth][myHeight];
		for (int i = 0; i < myWidth; i++) {
			for (int j = 0; j < myHeight; j++) {
				HashSet<Cell> neighbors = new HashSet<Cell>();
				this.addNeighbors(neighbors, myGrid, i , j);
				nextGrid[i][j] = myGrid[i][j].nextState(neighbors);
			}
		}
		myGrid = nextGrid;
		return nextGrid;
	}
	
	protected void addNeighbors(HashSet<Cell> neighbors, Cell[][] grid, int i, int j) {
		if (inGrid(i-1,j))
			neighbors.add(grid[i-1][j]);
		if (inGrid(i+1,j))
			neighbors.add(grid[i+1][j]);
		if (inGrid(i,j-1))
			neighbors.add(grid[i][j-1]);
		if (inGrid(i,j+1))
			neighbors.add(grid[i][j+1]);
	}
	
	public boolean inGrid (int x, int y) {
		return (x >= 0 && x <= myWidth && y >= 0 && y <= myHeight);
	}
	
	public Color[][] getVisuals() {
		Color[][] colorGrid = new Color[myWidth][myHeight];
		for (int i = 0; i < myWidth; i++) {
			for (int j = 0; j < myHeight; j++) {
				colorGrid[i][j] = myGrid[i][j].getDisplayColor();
			}
		}
		return colorGrid;
	}
}
