package grid;

import java.util.HashMap;
import java.util.Map;

import cell.GOLsim.AliveCell;
import cell.GOLsim.DeadCell;

/**
 * @author Yashas Manjunatha
 * Creates a Grid for the GOL Simulation.
 * Extends the Grid class.
 */
public class GOLSimGrid extends Grid{
	private String[][] cellArray;

	/**
	 * Initializes a Grid for the GOL Simulation
	 * @param width - number of cells in the width of the grid
	 * @param height - number of cells in the height of the grid
	 * @param cellArray - array of initial cell types
	 */
	public GOLSimGrid(int width, int height, String shape, String arrangement, String edge_type, String[][] cellArray) {
		super(width, height, shape, arrangement, edge_type);
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
	 * @see grid.Grid#getNumberOfCells()
	 */
	@Override
	public Map<String, Number> getNumberOfCells() {
		HashMap<String, Number> map = new HashMap<>();
		int alive = 0;
		int dead = 0;
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				if (myGrid[i][j] instanceof AliveCell) {
					alive++;
				} else if (myGrid[i][j] instanceof DeadCell) {
					dead++;
				}
			}
		}
		map.put("Alive Cells", alive);
		map.put("Dead Cells", dead);
		return map;
	}

	/* (non-Javadoc)
	 * @see grid.Grid#getArray()
	 */
	@Override
	public String[][] getArray() {
		getCurrentParameters();
		return this.cellArray;
	}

	/* (non-Javadoc)
	 * @see grid.Grid#getCurrentParameters()
	 */
	@Override
	public Map<String,Double[]> getCurrentParameters() {
		HashMap<String, Double[]> map = new HashMap<>();
		cellArray = new String[myGrid.length][myGrid[0].length];
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				if (myGrid[i][j] instanceof AliveCell) {
					cellArray[i][j] = "alive";
				} else if (myGrid[i][j] instanceof DeadCell) {
					cellArray[i][j] = "dead";
				}
			}
		}
		return map;
	}

	/* (non-Javadoc)
	 * @see grid.Grid#setCurrentParameters(java.util.Map)
	 */
	@Override
	public void setCurrentParameters(Map<String, Double[]> map) {
		// GOL Simulation has no parameters to set.
		
	}
}
