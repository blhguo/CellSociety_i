package grid;

import cell.watorsim.EmptyCell;
import cell.watorsim.FishCell;
import cell.watorsim.SharkCell;

public class WatorSimGrid extends Grid{

	public WatorSimGrid(int width, int height, String[][] cellArray, int[][] threshold) {
		super(width, height);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				switch (cellArray[i][j]) {
					case "fish": 
						this.myGrid[i][j] = new FishCell(threshold[i][j]);
						break;
					case "shark": 
						this.myGrid[i][j] = new SharkCell(threshold[i][j]);
						break;
					default: 
						this.myGrid[i][j] = new EmptyCell();
						break;
				}
			}
		}
	}
}