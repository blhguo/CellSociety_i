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
						this.myGrid[i][j] = new FireCell();
						break;
					case "tree": 
						TreeCell newTreeCell = new TreeCell();
						newTreeCell.setProbCatch(probCatch);
						newTreeCell.setProbLightning(probLightning);
						this.myGrid[i][j] = newTreeCell;
						break;
					default: 
						EmptyCell newEmptyCell = new EmptyCell();
						newEmptyCell.setProbGrow(probGrow);
						this.myGrid[i][j] = newEmptyCell;
						break;
				}
			}
		}
	}
}