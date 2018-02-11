package grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	private Cell[][] nextGrid;
	private String[][] cellArray;
	private double[][] threshold;

	/**
	 * Initializes a Grid for the Segregation Simulation
	 * @param width - number of cells in the width of the grid
	 * @param height - number of cells in the height of the grid
	 * @param cellArray - array of initial cell types
	 * @param threshold - array of satisfaction threshold values
	 */
	public SegregationSimGrid(int width, int height, String shape, String[][] cellArray, double[][] threshold) {
		super(width, height, shape, "all");
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

	/* (non-Javadoc)
	 * @see grid.Grid#updateGrid()
	 */
	@Override
	public Cell[][] updateGrid() {
		nextGrid = new Cell[this.myGrid.length][this.myGrid[0].length];
		for (int i = 0; i < nextGrid.length; i++) {
			for (int j = 0; j < nextGrid[0].length; j++) {
				ArrayList<Cell> neighbors = new ArrayList<>();
				this.addNeighbors(neighbors, myGrid, i , j);
				Cell nextState = myGrid[i][j].nextState(neighbors);
				if (!(nextState instanceof EmptyCell)) {
					nextGrid[i][j] = nextState;
				}
				else if (!(myGrid[i][j] instanceof EmptyCell)) {
					placeInEmpty(myGrid[i][j]);
					nextGrid[i][j] = nextState;
				} else if (!((EmptyCell) myGrid[i][j]).isTaken()) {
					nextGrid[i][j] = nextState;
				}
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
	 * @see grid.Grid#getNumberOfCells()
	 */
	@Override
	public Map<String, Number> getNumberOfCells() {
		HashMap<String, Number> map = new HashMap<>();
		int empty = 0;
		int o = 0;
		int x = 0;
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				if (myGrid[i][j] instanceof EmptyCell) {
					empty++;
				} else if (myGrid[i][j] instanceof OCell) {
					o++;
				} else if (myGrid[i][j] instanceof XCell) {
					x++;
				}
			}
		}
		map.put("Empty Cells", empty);
		map.put("O Cells", o);
		map.put("X Cells", x);
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
	
	public double[][] getThreshold() {
		getCurrentParameters();
		return this.threshold;
	}

	/* (non-Javadoc)
	 * @see grid.Grid#getCurrentParameters()
	 */
	@Override
	public Map<String,Double> getCurrentParameters() {
		HashMap<String, Double> map = new HashMap<>();
		double o_cell_threshold = 0;
		double x_cell_threshold = 0;
		cellArray = new String[myGrid.length][myGrid[0].length];
		threshold = new double[myGrid.length][myGrid[0].length];
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				if (myGrid[i][j] instanceof EmptyCell) {
					cellArray[i][j] = "empty";
					threshold[i][j] = 0;
				} else if (myGrid[i][j] instanceof OCell) {
					cellArray[i][j] = "o";
					threshold[i][j] = ((OCell) myGrid[i][j]).getThreshold();
					o_cell_threshold = threshold[i][j];
				} else if (myGrid[i][j] instanceof XCell) {
					cellArray[i][j] = "x";
					threshold[i][j] = ((XCell) myGrid[i][j]).getThreshold();
					x_cell_threshold = threshold[i][j];
				}
			}
		}
		map.put("X Cell Threshold", x_cell_threshold);
		map.put("O Cell Threshold", o_cell_threshold);
		return map;
	}

	@Override
	public void setCurrentParameters(Map<String, Double> map) {
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				if (myGrid[i][j] instanceof OCell) {
					((OCell) myGrid[i][j]).setThreshold(map.get("O Cell Threshold"));
				} else if (myGrid[i][j] instanceof XCell) {
					((XCell) myGrid[i][j]).setThreshold(map.get("X Cell Threshold"));
				}
			}
		}
	}
}