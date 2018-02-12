package grid;

import java.util.HashMap;
import java.util.Map;

import cell.sugarsim.AgentCell;
import cell.sugarsim.EmptyCell;

public class SugarSimGrid extends Grid{
	private String[][] cellArray;

	public SugarSimGrid(int width, int height, String shape, String arrangement, String edge_type, String[][] cellArray, int[][] patch_sugar, int[][] max_sugar, int sugarGBR, int sugarGBI, int[][] tick, int[][] agent_sugar, int[][] sugarMetabolism, int[][] vision) {
		super(width, height, shape, arrangement, edge_type);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				switch (cellArray[i][j]) {
				case "agent": 
					this.myGrid[i][j] = new AgentCell(patch_sugar[i][j], max_sugar[i][j], sugarGBR, sugarGBI, tick[i][j], agent_sugar[i][j], sugarMetabolism[i][j], vision[i][j]);
					break;
				default: 
					this.myGrid[i][j] = new EmptyCell(patch_sugar[i][j], max_sugar[i][j], sugarGBR, sugarGBI, tick[i][j]);
					break;
				}
			}
		}
	}

	@Override
	public Map<String, Number> getNumberOfCells() {
		HashMap<String, Number> map = new HashMap<>();
		int empty = 0;
		int agent = 0;
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				if (myGrid[i][j] instanceof EmptyCell) {
					empty++;
				} else if (myGrid[i][j] instanceof AgentCell) {
					agent++;
				}
			}
		}
		//map.put("Empty Cells", empty);
		map.put("Agent Cells", agent);
		return map;
	}

	@Override
	public String[][] getArray() {
		getCurrentParameters();
		return this.cellArray;
	}
	
	public int[][] getPatchSugarArray() {
		return null;
	}
	
	public int[][] getPatchMaxSugarArray() {
		return null;
	}
	
	public int[][] getPatchTickArray() {
		return null;
	}
	
	public int[][] getAgentSugarArray() {
		return null;
	}
	
	public int[][] getAgentMetabolismArray() {
		return null;
	}
	
	public int[][] getAgentVisionArray() {
		return null;
	}
	
	public int getGrowRate() {
		return 0;
	}
	
	public int getGrowInterval() {
		return 0;
	}

	@Override
	public Map<String, Double[]> getCurrentParameters() {
		HashMap<String, Double[]> map = new HashMap<>();
		return map;
	}

	@Override
	public void setCurrentParameters(Map<String, Double[]> map) {
		// TODO Auto-generated method stub
		
	}
}
