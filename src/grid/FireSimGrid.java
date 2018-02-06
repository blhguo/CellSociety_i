package grid;

import cell.firesim.EmptyCell;
import cell.firesim.FireCell;
import cell.firesim.TreeCell;

/**
 * @author Yashas Manjunatha
 * Creates a Grid for the Fire Simulation.
 * Extends the Grid class.
 */
public class FireSimGrid extends Grid {
	/**
	 * Initializes a Grid for the Fire Simulation
	 * @param width - number of cells in the width of the grid
	 * @param height - number of cells in the height of the grid
	 * @param cellArray - array of initial cell types
	 * @param probCatch - probability of tree cell catching on fire
	 * @param probLightning - probability of tree cell getting struck by lightning
	 * @param probGrow - probability of tree cell growing in empty cell
	 */
	public FireSimGrid(int width, int height, String[][] cellArray, double probCatch, double probLightning, double probGrow) {
		super(width, height);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				switch (cellArray[i][j]) {
					case "fire":
						this.myGrid[i][j] = new FireCell(probCatch, probLightning, probGrow);
						break;
					case "tree": 
						this.myGrid[i][j] = new TreeCell(probCatch, probLightning, probGrow);
						break;
					default: 
						this.myGrid[i][j] = new EmptyCell(probCatch, probLightning, probGrow);
						break;
				}
			}
		}
	}
}