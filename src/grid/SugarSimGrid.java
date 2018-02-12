package grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cell.Cell;
import cell.sugarsim.AgentCell;
import cell.sugarsim.EmptyCell;
import cell.sugarsim.SugarSimCell;

/**
 * @author Yashas Manjunatha
 * Creates and describes behavior of a Grid for the Sugar Simulation.
 * Extends the Grid class.
 */
public class SugarSimGrid extends Grid{
	private Cell[][] nextGrid;
	private String[][] cellArray;
	private int[][] patchSugarArray;
	private int[][] patchMaxSugarArray;
	private int[][] patchTickArray;
	private int[][] agentSugarArray;
	private int[][] agentMetabolismArray;
	private int[][] agentVisionArray;
	private int growRate;
	private int growInterval;

	/**
	 * Sets up Sugar Simulation Grid.
	 * @param width - number of cells in the width of the grid
	 * @param height - number of cells in the height of the grid
	 * @param shape - shape of cells
	 * @param arrangement - neighbor arrangement type
	 * @param edge_type - edge type
	 * @param cellArray - array of initial cell types
	 * @param patch_sugar - patch sugar
	 * @param max_sugar - max of patch sugar
	 * @param sugarGBR - sugar grow back rate
	 * @param sugarGBI - sugar grow back interval
	 * @param tick - time
	 * @param agent_sugar - agent sugar value
	 * @param sugarMetabolism - agent sugar metabolism
	 * @param vision - agent vision
	 */
	public SugarSimGrid(int width, int height, String shape, String arrangement, String edge_type, String[][] cellArray, int[][] patch_sugar, int[][] max_sugar, int sugarGBR, int sugarGBI, int[][] tick, int[][] agent_sugar, int[][] sugarMetabolism, int[][] vision) {
		super(width, height, shape, arrangement, edge_type);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				switch (cellArray[i][j]) {
				case "agent": 
					this.myGrid[i][j] = new AgentCell(i, j, patch_sugar[i][j], max_sugar[i][j], sugarGBR, sugarGBI, tick[i][j], agent_sugar[i][j], sugarMetabolism[i][j], vision[i][j]);
					break;
				default: 
					this.myGrid[i][j] = new EmptyCell(i, j, patch_sugar[i][j], max_sugar[i][j], sugarGBR, sugarGBI, tick[i][j]);
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
				
				if (myGrid[i][j] instanceof AgentCell && nextState instanceof AgentCell) {
					ArrayList<EmptyCell> empty_cells = new ArrayList<>();
					int vision_count = 0;
					for(Cell cell:neighbors) {
						if ((cell instanceof EmptyCell) && !((EmptyCell) cell).isOccupied() && vision_count <= ((AgentCell) myGrid[i][j]).getVision()) {
							empty_cells.add((EmptyCell) cell);
						}
					}
					
					int max_sugar = 0;
					for(EmptyCell cell:empty_cells) {
						if (cell.getPatchSugar() > max_sugar) {
							max_sugar = cell.getPatchSugar();
						}
					}
					
					EmptyCell chosen = null;
					for(EmptyCell cell:empty_cells) {
						if (cell.getPatchSugar() == max_sugar) {
							chosen = cell;
						}
					}
					switchCells(chosen, (AgentCell) myGrid[i][j]);
				} else {
					nextGrid[i][j] = nextState;
				}
				
			}
		}
		myGrid = nextGrid;
		return nextGrid;
	}

	private void switchCells(EmptyCell cell, AgentCell agentCell) {
		nextGrid[cell.getX()][cell.getY()] = new AgentCell(cell.getX(), cell.getY(), cell.getPatchSugar(), cell.getMaxSugar(), cell.getSugarGBR(), cell.getSugarGBI(), cell.getTick(), agentCell.getAgentSugar(), agentCell.getSugarMetabolism(), agentCell.getVision());
		nextGrid[agentCell.getX()][agentCell.getY()] = new EmptyCell(agentCell.getX(), agentCell.getY(), agentCell.getPatchSugar(), agentCell.getMaxSugar(), agentCell.getSugarGBR(), agentCell.getSugarGBI(), agentCell.getTick());
		((EmptyCell) myGrid[cell.getX()][cell.getY()]).setOccupied(true);
		((AgentCell) nextGrid[cell.getX()][cell.getY()]).eatSugar();
	}

	/* (non-Javadoc)
	 * @see grid.Grid#getNumberOfCells()
	 */
	@Override
	public Map<String, Number> getNumberOfCells() {
		HashMap<String, Number> map = new HashMap<>();
		//int empty = 0;
		int agent = 0;
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				if (myGrid[i][j] instanceof EmptyCell) {
					//empty++;
				} else if (myGrid[i][j] instanceof AgentCell) {
					agent++;
				}
			}
		}
		//map.put("Empty Cells", empty);
		map.put("Agent Cells", agent);
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
	
	public int[][] getPatchSugarArray() {
		getCurrentParameters();
		return this.patchSugarArray;
	}
	
	public int[][] getPatchMaxSugarArray() {
		getCurrentParameters();
		return this.patchMaxSugarArray;
	}
	
	public int[][] getPatchTickArray() {
		getCurrentParameters();
		return this.patchTickArray;
	}
	
	public int[][] getAgentSugarArray() {
		getCurrentParameters();
		return this.agentSugarArray;
	}
	
	public int[][] getAgentMetabolismArray() {
		getCurrentParameters();
		return this.agentMetabolismArray;
	}
	
	public int[][] getAgentVisionArray() {
		getCurrentParameters();
		return this.agentVisionArray;
	}
	
	public int getGrowRate() {
		getCurrentParameters();
		return this.growRate;
	}
	
	public int getGrowInterval() {
		getCurrentParameters();
		return this.growInterval;
	}

	/* (non-Javadoc)
	 * @see grid.Grid#getCurrentParameters()
	 */
	@Override
	public Map<String, Double[]> getCurrentParameters() {
		HashMap<String, Double[]> map = new HashMap<>();
		cellArray = new String[myGrid.length][myGrid[0].length];
		patchSugarArray = new int[myGrid.length][myGrid[0].length];
		patchMaxSugarArray = new int[myGrid.length][myGrid[0].length];
		patchTickArray = new int[myGrid.length][myGrid[0].length];
		agentSugarArray = new int[myGrid.length][myGrid[0].length];
		agentMetabolismArray = new int[myGrid.length][myGrid[0].length];
		agentVisionArray = new int[myGrid.length][myGrid[0].length];
		double growrate = 1;
		double growinterval = 1;
		double agent_metablism = 0;
		double vision = 4;
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				patchSugarArray[i][j] = ((SugarSimCell) myGrid[i][j]).getPatchSugar();
				patchMaxSugarArray[i][j] = ((SugarSimCell) myGrid[i][j]).getMaxSugar();
				patchTickArray[i][j] = ((SugarSimCell) myGrid[i][j]).getTick();
				growRate = ((SugarSimCell) myGrid[i][j]).getSugarGBR();
				growrate = growRate;
				growInterval = ((SugarSimCell) myGrid[i][j]).getSugarGBI();
				growinterval = growInterval;
				if (myGrid[i][j] instanceof EmptyCell) {
					cellArray[i][j] = "empty";
				} else if (myGrid[i][j] instanceof AgentCell) {
					cellArray[i][j] = "agent";
					agentSugarArray[i][j] = ((AgentCell) myGrid[i][j]).getAgentSugar();
					agentMetabolismArray[i][j] = ((AgentCell) myGrid[i][j]).getSugarMetabolism();
					agent_metablism = agentMetabolismArray[i][j];
					agentVisionArray[i][j] = ((AgentCell) myGrid[i][j]).getVision();
					vision = agentVisionArray[i][j];
				}
			}
		}
		Double[] gbr = {0.0, 4.0, growrate};
		map.put("sugarGrowBackRate", gbr);
		Double[] gbi = {1.0, 10.0, growinterval};
		map.put("sugarGrowBackInterval", gbi);
		Double[] meta = {1.0, 6.0, agent_metablism};
		map.put("agentMetabolism", meta);
		Double[] vis = {1.0, 4.0, vision};
		map.put("agentVision", vis);
		return map;
	}

	/* (non-Javadoc)
	 * @see grid.Grid#setCurrentParameters(java.util.Map)
	 */
	@Override
	public void setCurrentParameters(Map<String, Double[]> map) {
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				((SugarSimCell) myGrid[i][j]).setSugarGBR(map.get("sugarGrowBackRate")[2].intValue());
				((SugarSimCell) myGrid[i][j]).setSugarGRI(map.get("sugarGrowBackInterval")[2].intValue());
				if (myGrid[i][j] instanceof AgentCell) {
					((AgentCell) myGrid[i][j]).setSugarMetabolism(map.get("agentMetabolism")[2].intValue());
					((AgentCell) myGrid[i][j]).setVision(map.get("agentVision")[2].intValue());
				}
			}
		}
	}
}
