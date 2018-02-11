package grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	private String cell_shape;
	private String neighbor_arrangement;
	private Neighbors neighbors_object;

	/**
	 * Creates a new Grid object.
	 * @param width - number of cells in the width of the grid
	 * @param height - number of cells in the height of the grid
	 * @param shape
	 * @param arrangement
	 */
	public Grid (int width, int height, String shape, String arrangement) {
		myWidth = width;
		myHeight = height;
		myGrid = new Cell[myWidth][myHeight];
		this.cell_shape = shape;
		this.neighbor_arrangement = arrangement;
		neighbors_object = new Neighbors(cell_shape, neighbor_arrangement);
	}
	
	public Grid (int width, int height) {
		this(width, height, "square", "cardinal");
	}
	
	public Grid (int width, int height, String shape) {
		this(width, height, shape, "cardinal");
	}
	
	public String getShape() {
		return this.cell_shape;
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
		neighbors_object.addNeighbors(neighbors, grid, i, j);
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
	
	/**
	 * Gets the number of the different types of cells in a simulation during the current state
	 * @return a Map with a key string with the type of cell and value of the number of that type of cell
	 */
	public abstract Map<String, Number> getNumberOfCells();
	
	/**
	 * @return string array of cell types
	 */
	public abstract String[][] getArray();
	
	/**
	 * Updates various grid parameters with the current state values
	 */
	public abstract Map<String,Double> getCurrentParameters();
	
	public abstract void setCurrentParameters(Map<String,Double> map);
}
