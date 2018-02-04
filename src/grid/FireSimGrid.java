package grid;

import cell.firesim.EmptyCell;
import cell.firesim.FireCell;
import cell.firesim.TreeCell;

public class FireSimGrid extends Grid {

	public FireSimGrid(int width, int height, String[][] cellArray, double probCatch, double probLightning) {
		super(width, height);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				switch (cellArray[i][j]) {
					case "fire": 
						this.myGrid[i][j] = new FireCell();
						break;
					case "tree": 
						TreeCell newCell = new TreeCell();
						newCell.setProbCatch(probCatch);
						newCell.setProbLightning(probLightning);
						this.myGrid[i][j] = newCell;
						break;
					default: 
						this.myGrid[i][j] = new EmptyCell();
						break;
				}
			}
		}
	}
}