package grid;

import java.util.HashSet;

import cell.Cell;
import cell.segregation.EmptyCell;
import cell.segregation.OCell;
import cell.segregation.XCell;

public class SegregationSimGrid extends Grid{

	public SegregationSimGrid(int width, int height, String[][] cellArray, double[][] threshold) {
		super(width, height);
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
	
	
	private Cell[][] nextGrid;
	@Override
	public Cell[][] updateGrid() {
		nextGrid = new Cell[this.myGrid.length][this.myGrid[0].length];
		for (int i = 0; i < nextGrid.length; i++) {
			for (int j = 0; j < nextGrid[0].length; j++) {
				HashSet<Cell> neighbors = new HashSet<Cell>();
				this.addNeighbors(neighbors, myGrid, i , j);
				Cell nextState = myGrid[i][j].nextState(neighbors);
				if (!(nextState instanceof EmptyCell))
					nextGrid[i][j] = nextState;
				else if (!(myGrid[i][j] instanceof EmptyCell)) {
					placeInEmpty(myGrid[i][j]);
					nextGrid[i][j] = nextState;
				} else if (!((EmptyCell) myGrid[i][j]).isTaken())
					nextGrid[i][j] = nextState;
			}
		}
		myGrid = nextGrid;
		return nextGrid;
	}
	
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