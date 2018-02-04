package grid;

import java.util.HashSet;

import cell.Cell;
import cell.GOLsim.AliveCell;
import cell.GOLsim.DeadCell;

public class GOLSimGrid extends Grid{

	public GOLSimGrid(int width, int height, String[][] cellArray) {
		super(width, height);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				switch (cellArray[i][j]) {
					case "alive": 
						this.myGrid[i][j] = new AliveCell();
						break;
					default: 
						this.myGrid[i][j] = new DeadCell();
						break;
				}
			}
		}
	}
	
	@Override
	protected void addNeighbors(HashSet<Cell> neighbors, Cell[][] grid, int i, int j) {
		if (inGrid(i-1,j))
			neighbors.add(grid[i-1][j]);
		if (inGrid(i+1,j))
			neighbors.add(grid[i+1][j]);
		if (inGrid(i,j-1))
			neighbors.add(grid[i][j-1]);
		if (inGrid(i,j+1))
			neighbors.add(grid[i][j+1]);
		if (inGrid(i-1,j-1))
			neighbors.add(grid[i-1][j-1]);
		if (inGrid(i+1,j+1))
			neighbors.add(grid[i+1][j+1]);
		if (inGrid(i+1,j-1))
			neighbors.add(grid[i+1][j-1]);
		if (inGrid(i-1,j+1))
			neighbors.add(grid[i-1][j+1]);
	}
}
