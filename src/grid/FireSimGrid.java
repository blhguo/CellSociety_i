package grid;

import cell.firesim.EmptyCell;
import cell.firesim.FireCell;
import cell.firesim.TreeCell;

public class FireSimGrid extends Grid {
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