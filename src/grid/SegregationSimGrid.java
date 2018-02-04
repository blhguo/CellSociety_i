package grid;

import java.util.HashSet;

import cell.Cell;
import cell.segregation.EmptyCell;
import cell.segregation.OCell;
import cell.segregation.XCell;

public class SegregationSimGrid extends Grid{

	public SegregationSimGrid(int width, int height, String[][] cellArray, double x_threshold, double o_threshold) {
		super(width, height);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				switch (cellArray[i][j]) {
					case "x": 
						this.myGrid[i][j] = new XCell(x_threshold);
						break;
					case "o":
						this.myGrid[i][j] = new OCell(o_threshold);
						break;
					default: 
						this.myGrid[i][j] = new EmptyCell();
						break;
				}
			}
		}
	}
	
	@Override
	public Cell[][] updateGrid() {
		Cell[][] nextGrid = new Cell[this.myGrid.length][this.myGrid[0].length];
		for (int i = 0; i < nextGrid.length; i++) {
			for (int j = 0; j < nextGrid[0].length; j++) {
				HashSet<Cell> neighbors = new HashSet<Cell>();
				this.addNeighbors(neighbors, myGrid, i , j);
				nextGrid[i][j] = myGrid[i][j].nextState(neighbors);
			}
		}
		myGrid = nextGrid;
		return nextGrid;
	}

}