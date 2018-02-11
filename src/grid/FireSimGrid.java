package grid;

import java.util.HashMap;
import java.util.Map;

import cell.firesim.EmptyCell;
import cell.firesim.FireCell;
import cell.firesim.FireSimCell;
import cell.firesim.TreeCell;

/**
 * @author Yashas Manjunatha
 * Creates a Grid for the Fire Simulation.
 * Extends the Grid class.
 */
public class FireSimGrid extends Grid {
	private String[][] cellArray;
	
	/**
	 * Initializes a Grid for the Fire Simulation
	 * @param width - number of cells in the width of the grid
	 * @param height - number of cells in the height of the grid
	 * @param cellArray - array of initial cell types
	 * @param probCatch - probability of tree cell catching on fire
	 * @param probLightning - probability of tree cell getting struck by lightning
	 * @param probGrow - probability of tree cell growing in empty cell
	 */
	public FireSimGrid(int width, int height, String shape, String arrangement, String edge_type, String[][] cellArray, double probCatch, double probLightning, double probGrow) {
		super(width, height, shape, arrangement, edge_type);
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

	/* (non-Javadoc)
	 * @see grid.Grid#getNumberOfCells()
	 */
	@Override
	public Map<String, Number> getNumberOfCells() {
		HashMap<String, Number> map = new HashMap<>();
		int empty = 0;
		int fire = 0;
		int tree = 0;
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				if (myGrid[i][j] instanceof EmptyCell) {
					empty++;
				} else if (myGrid[i][j] instanceof FireCell) {
					fire++;
				} else if (myGrid[i][j] instanceof TreeCell) {
					tree++;
				}
			}
		}
		map.put("Empty Cells", empty);
		map.put("Fire Cells", fire);
		map.put("Tree Cells", tree);
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
	
	public double getFireProb() {
		return ((FireSimCell) myGrid[0][0]).getFireProb();
	}
	
	public double getLightningProb() {
		return ((FireSimCell) myGrid[0][0]).getLightningProb();
	}
	
	public double getProbGrow() {
		return ((FireSimCell) myGrid[0][0]).getProbGrow();
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
				if (myGrid[i][j] instanceof EmptyCell) {
					cellArray[i][j] = "empty";
				} else if (myGrid[i][j] instanceof FireCell) {
					cellArray[i][j] = "fire";
				} else if (myGrid[i][j] instanceof TreeCell) {
					cellArray[i][j] = "tree";
				}
			}
		}
		Double[] pC = {0.0, 1.0, getFireProb()};
		map.put("probCatch", pC);
		Double[] pL = {0.0, 1.0, getLightningProb()};
		map.put("probLightning", pL);
		Double[] pG = {0.0, 1.0, getProbGrow()};
		map.put("probGrow", pG);
		return map;
	}

	/* (non-Javadoc)
	 * @see grid.Grid#setCurrentParameters(java.util.Map)
	 */
	@Override
	public void setCurrentParameters(Map<String, Double[]> map) {
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				((FireSimCell) myGrid[i][j]).setFireProb(map.get("probCatch")[2]);
				((FireSimCell) myGrid[i][j]).setLightningProb(map.get("probLightning")[2]);
				((FireSimCell) myGrid[i][j]).setProbGrow(map.get("probGrow")[2]);
			}
		}
	}
}