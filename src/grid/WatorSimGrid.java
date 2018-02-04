package grid;

import cell.watorsim.EmptyCell;
import cell.watorsim.FishCell;
import cell.watorsim.SharkCell;

public class WatorSimGrid extends Grid{

	public WatorSimGrid(int width, int height, String[][] cellArray, int fish_threshold, int shark_threshold) {
		super(width, height);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				switch (cellArray[i][j]) {
					case "fish": 
						this.myGrid[i][j] = new FishCell(fish_threshold);
						break;
					case "shark": 
						this.myGrid[i][j] = new SharkCell(shark_threshold);
						break;
					default: 
						this.myGrid[i][j] = new EmptyCell();
						break;
				}
			}
		}
	}
}