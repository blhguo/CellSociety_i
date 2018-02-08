package grid;

import java.util.ArrayList;
import java.util.List;

import cell.Cell;
import javafx.scene.paint.Color;

/**
 * @author Yashas Manjunatha
 * Abstract Grid class that creates and describes grid behavior for simulations.
 * Create subclass of this for a new simulation type.
 */
public abstract class Grid {
	protected Cell[][] myGrid;
	private int myWidth;
	private int myHeight;
	
	/**
	 * Creates a new Grid object.
	 * @param width - number of cells in the width of the grid
	 * @param height - number of cells in the height of the grid
	 */
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
	
	/**
	 * @return the 2D array of cells of the grid
	 */
	public Cell[][] getCellArray() {
		return myGrid;
	}
	
	/**
	 * Updates the cell states of every cell in the grid to the next state of the cell.
	 * @return the 2D array of cells of the next state grid
	 */
	public Cell[][] updateGrid() {
		Cell[][] nextGrid = new Cell[myWidth][myHeight];
		for (int i = 0; i < myWidth; i++) {
			for (int j = 0; j < myHeight; j++) {
				List<Cell> neighbors = new ArrayList<>();
				this.addNeighbors(neighbors, myGrid, i , j);
				nextGrid[i][j] = myGrid[i][j].nextState(neighbors);
			}
		}
		myGrid = nextGrid;
		return nextGrid;
	}
	
	/**
	 * Adds all the neighbors of a cell to a list
	 * @param neighbors - list of the cells neighbors
	 * @param grid - the grid of cells
	 * @param i - x location of cell in grid
	 * @param j - y location of cell in grid
	 */
	protected void addNeighbors(List<Cell> neighbors, Cell[][] grid, int i, int j) {
		if (inGrid(i-1,j)) {
			neighbors.add(grid[i-1][j]);
		}
		if (inGrid(i+1,j)) {
			neighbors.add(grid[i+1][j]);
		}
		if (inGrid(i,j-1)) {
			neighbors.add(grid[i][j-1]);
		}
		if (inGrid(i,j+1)) {
			neighbors.add(grid[i][j+1]);
		}
	}
	
	/**
	 * Checks to see if cell location is in the bounds of the grid
	 * @param x - x location of cell in grid
	 * @param y - y location of cell in grid
	 * @return if location is in the bounds of the grid
	 */
	public boolean inGrid (int x, int y) {
		return (x >= 0 && x < myWidth && y >= 0 && y < myHeight);
	}
	
	/**
	 * Gets the visuals of the grid
	 * @return a 2D array of colors of the cells
	 */
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
