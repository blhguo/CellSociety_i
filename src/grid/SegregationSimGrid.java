package grid;

import java.util.ArrayList;
import java.util.List;
import cell.Cell;
import cell.segregation.EmptyCell;
import cell.segregation.OCell;
import cell.segregation.XCell;

/**
 * @author Yashas Manjunatha
 * Creates and describes behavior of a Grid for the Segregation Simulation.
 * Extends the Grid class.
 */
public class SegregationSimGrid extends Grid{

	/**
	 * Initializes a Grid for the Segregation Simulation
	 * @param width - number of cells in the width of the grid
	 * @param height - number of cells in the height of the grid
	 * @param cellArray - array of initial cell types
	 * @param threshold - array of satisfaction threshold values
	 */
	public SegregationSimGrid(int width, int height, String[][] cellArray, double[][] threshold) {
		super(width, height);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				switch (cellArray[i][j]) {
					case "x": 
						this.myGrid[i][j] = new XCell(threshold[i][j]);
						break;
					case "o":
						this.myGrid[i][j] = new OCell(threshold[i][j]);
						break;
					default: 
						this.myGrid[i][j] = new EmptyCell();
						break;
				}
			}
		}
	}
	
	
	private Cell[][] nextGrid;
	/* (non-Javadoc)
	 * @see grid.Grid#updateGrid()
	 */
	@Override
	public Cell[][] updateGrid() {
		nextGrid = new Cell[this.myGrid.length][this.myGrid[0].length];
		for (int i = 0; i < nextGrid.length; i++) {
			for (int j = 0; j < nextGrid[0].length; j++) {
				ArrayList<Cell> neighbors = new ArrayList<Cell>();
				this.addNeighbors(neighbors, myGrid, i , j);
				Cell nextState = myGrid[i][j].nextState(neighbors);
				if (!(nextState instanceof EmptyCell))
					nextGrid[i][j] = nextState;
				else if (!(myGrid[i][j] instanceof EmptyCell)) {
					placeInEmpty(myGrid[i][j]);
					nextGrid[i][j] = nextState;
				} else if (!((EmptyCell) myGrid[i][j]).isTaken())
					nextGrid[i][j] = nextState;
			}
		}
		myGrid = nextGrid;
		return nextGrid;
	}
	
	/**
	 * Places a cell in the first empty cell available
	 * @param cell - cell to be placed
	 */
	private void placeInEmpty (Cell cell) {
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				if (myGrid[i][j] instanceof EmptyCell && !((EmptyCell) myGrid[i][j]).isTaken()) {
					nextGrid[i][j] = cell;
					((EmptyCell) myGrid[i][j]).setTaken(true);
					return;
				}
			}
		}		
	}
	
	/* (non-Javadoc)
	 * @see grid.Grid#addNeighbors(java.util.List, cell.Cell[][], int, int)
	 */
	@Override
	protected void addNeighbors(List<Cell> neighbors, Cell[][] grid, int i, int j) {
		if (inGrid(i-1,j))
			neighbors.add(grid[i-1][j]);
		if (inGrid(i+1,j))
			neighbors.add(grid[i+1][j]);
		if (inGrid(i,j-1))
			neighbors.add(grid[i][j-1]);
		if (inGrid(i,j+1))
			neighbors.add(grid[i][j+1]);
		if (inGrid(i-1,j-1))
			neighbors.add(grid[i-1][j-1]);
		if (inGrid(i+1,j+1))
			neighbors.add(grid[i+1][j+1]);
		if (inGrid(i+1,j-1))
			neighbors.add(grid[i+1][j-1]);
		if (inGrid(i-1,j+1))
			neighbors.add(grid[i-1][j+1]);
	}

}