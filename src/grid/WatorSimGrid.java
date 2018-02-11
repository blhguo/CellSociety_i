package grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cell.Cell;
import cell.watorsim.EmptyCell;
import cell.watorsim.FishCell;
import cell.watorsim.SharkCell;
import cell.watorsim.WatorSimCell;

/**
 * @author Yashas Manjunatha
 * Creates and describes behavior of a Grid for the Wator Simulation.
 * Extends the Grid class.
 */
public class WatorSimGrid extends Grid{
	private Cell[][] nextGrid;
	private String[][] cellArray;
	private int[][] reproduction;
	private int[][] energy;
	private int[][] gained_energy;

	/**
	 * Initializes a Grid for the Wator Simulation
	 * @param width - number of cells in the width of the grid
	 * @param height - number of cells in the height of the grid
	 * @param cellArray - array of initial cell types
	 * @param reproduction - array with reproduction threshold information
	 * @param energy - array with energy values
	 * @param gained_energy - array with gained energy values
	 */
	public WatorSimGrid(int width, int height, String shape, String arrangement, String edge_type, String[][] cellArray, int[][] reproduction, int[][] energy, int[][] gained_energy) {
		super(width, height, shape, arrangement, edge_type);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				switch (cellArray[i][j]) {
				case "fish": 
					this.myGrid[i][j] = new FishCell(i, j, reproduction[i][j]);
					break;
				case "shark": 
					this.myGrid[i][j] = new SharkCell(i, j, reproduction[i][j], energy[i][j], gained_energy[i][j]);
					break;
				default: 
					this.myGrid[i][j] = new EmptyCell(i, j);
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
				Cell currentState = myGrid[i][j];
				//Cell nextState = myGrid[i][j].nextState(neighbors);
				//nextGrid[i][j] = nextState;

				if (currentState instanceof FishCell) {
					Cell nextState = myGrid[i][j].nextState(neighbors);
					if (!((FishCell) currentState).isEaten()) {
						updateFishCell (i, j, (FishCell) currentState, neighbors, nextState);
					}
				} else if (currentState instanceof EmptyCell) {
					if (!((EmptyCell) currentState).isOccupied()) {
						nextGrid[i][j] = new EmptyCell(i, j);
					}
				} else if (currentState instanceof SharkCell) {
					Cell nextState = myGrid[i][j].nextState(neighbors);
					if (!(nextState instanceof EmptyCell)) {
						updateSharkCell(i, j, (SharkCell) currentState, neighbors, nextState);
					} else {
						nextGrid[i][j] = nextState;
					}
				}
			}
		}

		myGrid = nextGrid;
		return nextGrid;
	}

	/**
	 * Describes update rules for shark cell.
	 * @param i - x location of cell in grid
	 * @param j - y location of cell in grid
	 * @param currentState - current state of cell
	 * @param neighbors - neighbors of the cell
	 * @param nextState - next state of the cell
	 */
	private void updateSharkCell (int i, int j, SharkCell currentState, List<Cell> neighbors, Cell nextState) {
		ArrayList<FishCell> fish_cells = new ArrayList<>();
		for(Cell cell:neighbors) {
			if ((cell instanceof FishCell) && !((FishCell) cell).isEaten()) {
				fish_cells.add((FishCell) cell);
			}
		}

		if(!fish_cells.isEmpty()) {
			switchToRandomFish(fish_cells, currentState, i, j, new EmptyCell(i, j));
		} else {
			checkAndMoveToEmpty(i, j, currentState, neighbors);
		}

		checkAndReproduce(i, j, currentState, neighbors, nextState);
	}
	
	private void checkAndReproduce (int i, int j, WatorSimCell currentState, List<Cell> neighbors, Cell nextState) {
		if(currentState.isReproducing()) {
			currentState.resetReproduction();
			ArrayList<EmptyCell> reprod_empty_cells = new ArrayList<>();
			for(Cell cell:neighbors) {
				if ((cell instanceof EmptyCell) && !((EmptyCell) cell).isOccupied()) {
					reprod_empty_cells.add((EmptyCell) cell);
				}
			}

			if(!reprod_empty_cells.isEmpty()) {
				switchToRandomEmpty(reprod_empty_cells, currentState, i, j, nextState);
			}
		}
	}
	
	private void checkAndMoveToEmpty (int i, int j, WatorSimCell currentState, List<Cell> neighbors) {
		ArrayList<EmptyCell> empty_cells = new ArrayList<>();
		for(Cell cell:neighbors) {
			if ((cell instanceof EmptyCell) && !((EmptyCell) cell).isOccupied()) {
				empty_cells.add((EmptyCell) cell);
			}
		}

		if(!empty_cells.isEmpty()) {
			switchToRandomEmpty(empty_cells, currentState, i, j, new EmptyCell(i, j));
		} else {
			nextGrid[i][j] = currentState;
		}
	}

	/**
	 * Switches shark cell with random neighboring fish cell
	 * @param fish_cells - list of neighboring fish
	 * @param currentState - current shark cell
	 * @param i - x location of cell in grid
	 * @param j - y location of cell in grid
	 * @param nextState - next state of shark cell
	 */
	private void switchToRandomFish(List<FishCell> fish_cells, SharkCell currentState, int i, int j, Cell nextState) {
		int random_number = (int) Math.random() * fish_cells.size();

		int newX = fish_cells.get(random_number).getX();
		int newY = fish_cells.get(random_number).getY();

		SharkCell movedCurrentState = new SharkCell ((SharkCell) currentState);

		movedCurrentState.gainEnergy();
		nextGrid[newX][newY] = movedCurrentState;
		movedCurrentState.setX(newX);
		movedCurrentState.setY(newY);

		fish_cells.get(random_number).setEaten(true);
		//((FishCell) myGrid[newX][newY]).setEaten(true);

		nextGrid[i][j] = nextState;
	}

	/**
	 * Describes update rules for fish cell.
	 * @param i - x location of cell in grid
	 * @param j - y location of cell in grid
	 * @param currentState - current state of cell
	 * @param neighbors - neighbors of the cell
	 * @param nextState - next state of the cell
	 */
	private void updateFishCell (int i, int j, FishCell currentState, List<Cell> neighbors, Cell nextState) {
		checkAndMoveToEmpty(i, j, currentState, neighbors);

		checkAndReproduce(i, j, currentState, neighbors, nextState);
	}

	/**
	 * Switches cell with random neighboring empty cell
	 * @param empty_cells - list of neighboring empty cells
	 * @param currentState - current state of cell
	 * @param i - x location of cell in grid
	 * @param j - y location of cell in grid
	 * @param nextState - next state of cell
	 */
	private void switchToRandomEmpty(List<EmptyCell> empty_cells, Cell currentState, int i, int j, Cell nextState) {
		int random_number = (int) Math.random() * empty_cells.size();

		int newX = empty_cells.get(random_number).getX();
		int newY = empty_cells.get(random_number).getY();

		WatorSimCell movedCurrentState;
		if (currentState instanceof FishCell) {
			movedCurrentState = new FishCell ((FishCell) currentState);
		}
		else if (currentState instanceof SharkCell) {
			movedCurrentState = new SharkCell ((SharkCell) currentState);
		}
		else {
			movedCurrentState = new EmptyCell ((EmptyCell) currentState);
		}

		nextGrid[newX][newY] = movedCurrentState;
		movedCurrentState.setX(newX);
		movedCurrentState.setY(newY);

		empty_cells.get(random_number).setOccupied(true);
		//((EmptyCell) myGrid[newX][newY]).setOccupied(true);

		nextGrid[i][j] = nextState;
	}
	
	/* (non-Javadoc)
	 * @see grid.Grid#getNumberOfCells()
	 */
	@Override
	public Map<String, Number> getNumberOfCells() {
		HashMap<String, Number> map = new HashMap<>();
		int empty = 0;
		int fish = 0;
		int shark = 0;
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				if (myGrid[i][j] instanceof EmptyCell) {
					empty++;
				} else if (myGrid[i][j] instanceof FishCell) {
					fish++;
				} else if (myGrid[i][j] instanceof SharkCell) {
					shark++;
				}
			}
		}
		map.put("Empty Cells", empty);
		map.put("Fish Cells", fish);
		map.put("Shark Cells", shark);
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
	
	public int[][] getReproductionThreshold() {
		getCurrentParameters();
		return this.reproduction;
	}
	
	public int[][] getCurrentEnergy() {
		getCurrentParameters();
		return this.energy;
	}
	
	public int[][] getGainedEnergy() {
		getCurrentParameters();
		return this.gained_energy;
	}

	/* (non-Javadoc)
	 * @see grid.Grid#getCurrentParameters()
	 */
	@Override
	public Map<String,Double[]> getCurrentParameters() {
		HashMap<String, Double[]> map = new HashMap<>();
		double fish_reprod_thresh = 0;
		double shark_reprod_thresh = 0;
		double gain_energy = 0;
		cellArray = new String[myGrid.length][myGrid[0].length];
		reproduction = new int[myGrid.length][myGrid[0].length];
		energy = new int[myGrid.length][myGrid[0].length];
		gained_energy = new int[myGrid.length][myGrid[0].length];
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				if (myGrid[i][j] instanceof EmptyCell) {
					cellArray[i][j] = "empty";
					reproduction[i][j] = 0;
					energy[i][j] = 0;
					gained_energy[i][j] = 0;
				} else if (myGrid[i][j] instanceof FishCell) {
					cellArray[i][j] = "fish";
					reproduction[i][j] = ((FishCell) myGrid[i][j]).getReproductionThreshold();
					energy[i][j] = 0;
					gained_energy[i][j] = 0;
					fish_reprod_thresh = reproduction[i][j];
				} else if (myGrid[i][j] instanceof SharkCell) {
					cellArray[i][j] = "shark";
					reproduction[i][j] = ((SharkCell) myGrid[i][j]).getReproductionThreshold();
					energy[i][j] = ((SharkCell) myGrid[i][j]).getEnergy();
					gained_energy[i][j] = ((SharkCell) myGrid[i][j]).getGainedEnergy();
					shark_reprod_thresh = reproduction[i][j];
					gain_energy = gained_energy[i][j];
				}
			}
		}
		Double[] fArray = {0.0, 100.0, fish_reprod_thresh};
		Double[] sArray = {0.0, 100.0, fish_reprod_thresh};
		Double[] eArray = {0.0, 100.0, fish_reprod_thresh};
		map.put("Fish Reproduction Threshold", fArray);
		map.put("Shark Reproduction Threshold", sArray);
		map.put("Gained Energy eating Fish", eArray);
		return map;
	}

	@Override
	public void setCurrentParameters(Map<String, Double[]> map) {
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				if (myGrid[i][j] instanceof FishCell) {
					((FishCell) myGrid[i][j]).setReproductionThreshold(map.get("Fish Reproduction Threshold")[2]);
				} else if (myGrid[i][j] instanceof SharkCell) {
					((SharkCell) myGrid[i][j]).setReproductionThreshold(map.get("Shark Reproduction Threshold")[2]);
					((SharkCell) myGrid[i][j]).setGainedEnergy(map.get("Gained Energy eating Fish")[2]);
				}
			}
		}
	}
}