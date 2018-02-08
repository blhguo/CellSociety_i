package grid;

import java.util.List;
import cell.Cell;
import cell.GOLsim.AliveCell;
import cell.GOLsim.DeadCell;

/**
 * @author Yashas Manjunatha
 * Creates a Grid for the GOL Simulation.
 * Extends the Grid class.
 */
public class GOLSimGrid extends Grid{

	/**
	 * Initializes a Grid for the GOL Simulation
	 * @param width - number of cells in the width of the grid
	 * @param height - number of cells in the height of the grid
	 * @param cellArray - array of initial cell types
	 */
	public GOLSimGrid(int width, int height, String[][] cellArray) {
		super(width, height);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				switch (cellArray[i][j]) {
					case "alive": 
						this.myGrid[i][j] = new AliveCell();
						break;
					default: 
						this.myGrid[i][j] = new DeadCell();
						break;
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
