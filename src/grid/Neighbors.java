package grid;

import java.util.List;

import cell.Cell;

public class Neighbors {
	private String shape;
	private String arrangement;
	private String edge_type;
	private int myWidth;
	private int myHeight;
	
	public Neighbors(String shape, String arrangement, String edge_type) {
		this.shape = shape;
		this.arrangement = arrangement;
		this.edge_type = edge_type;
	}
	
	/**
	 * Adds all the neighbors of a cell to a list
	 * @param neighbors - list of the cells neighbors
	 * @param grid - the grid of cells
	 * @param i - x location of cell in grid
	 * @param j - y location of cell in grid
	 */
	public void addNeighbors(List<Cell> neighbors, Cell[][] grid, int i, int j) {
		myWidth = grid.length;
		myHeight = grid[0].length;
		switch (shape) {
			case "square":
				chooseSquareNeighborOrienation(neighbors, grid, i, j);
				break;
			case "hexagon":
				break;
			case "triangle":
				chooseTriangleNeighborOrienation(neighbors, grid, i, j);
				break;
			default:
				chooseSquareNeighborOrienation(neighbors, grid, i, j);
				break;
		}
	}
	
	private void chooseSquareNeighborOrienation(List<Cell> neighbors, Cell[][] grid, int i, int j) {
		switch (arrangement) {
			case "cardinal":
				square_cardinal(neighbors, grid, i, j);
				break;
			case "diagonal":
				square_diagonal(neighbors, grid, i, j);
				break;
			case "all":
				square_all(neighbors, grid, i, j);
				break;
			default:
				square_all(neighbors, grid, i, j);
				break;
		}
	}
	
	private void chooseTriangleNeighborOrienation(List<Cell> neighbors, Cell[][] grid, int i, int j) {
		switch (arrangement) {
			case "3-next":
				triangle_three(neighbors, grid, i, j);
				break;
			default:
				triangle_all(neighbors, grid, i, j);
				break;
		}
	}
	
	private void triangle_three (List<Cell> neighbors, Cell[][] grid, int i, int j) {
		if (i%2 == j%2) {
			if (inGrid(i+1,j)) {
				neighbors.add(grid[i+1][j]);
			}
		} else {
			if (inGrid(i-1,j)) {
				neighbors.add(grid[i-1][j]);
			}
		}
		if (inGrid(i,j-1)) {
			neighbors.add(grid[i][j-1]);
		}
		if (inGrid(i,j+1)) {
			neighbors.add(grid[i][j+1]);
		}
	}
	
	private void triangle_all(List<Cell> neighbors, Cell[][] grid, int i, int j) {
		if (i%2 == j%2) {
			if (inGrid(i-1,j-1)) {
				neighbors.add(grid[i-1][j-1]);
			}
			if (inGrid(i-1,j)) {
				neighbors.add(grid[i-1][j]);
			}
			if (inGrid(i-1,j+1)) {
				neighbors.add(grid[i-1][j+1]);
			}
			if (inGrid(i,j-2)) {
				neighbors.add(grid[i][j-2]);
			}
			if (inGrid(i,j-1)) {
				neighbors.add(grid[i][j-1]);
			}
			if (inGrid(i,j+1)) {
				neighbors.add(grid[i][j+1]);
			}
			if (inGrid(i,j+2)) {
				neighbors.add(grid[i][j+2]);
			}
			if (inGrid(i+1,j-2)) {
				neighbors.add(grid[i+1][j-2]);
			}
			if (inGrid(i+1,j-1)) {
				neighbors.add(grid[i+1][j-1]);
			}
			if (inGrid(i+1,j)) {
				neighbors.add(grid[i+1][j]);
			}
			if (inGrid(i+1,j+1)) {
				neighbors.add(grid[i+1][j+1]);
			}
			if (inGrid(i+1,j+2)) {
				neighbors.add(grid[i+1][j+2]);
			}
		} else {
			if (inGrid(i-1,j-2)) {
				neighbors.add(grid[i-1][j-2]);
			}
			if (inGrid(i-1,j-1)) {
				neighbors.add(grid[i-1][j-1]);
			}
			if (inGrid(i-1,j)) {
				neighbors.add(grid[i-1][j]);
			}
			if (inGrid(i-1,j+1)) {
				neighbors.add(grid[i-1][j+1]);
			}
			if (inGrid(i-1,j+2)) {
				neighbors.add(grid[i-1][j+2]);
			}
			if (inGrid(i,j-2)) {
				neighbors.add(grid[i][j-2]);
			}
			if (inGrid(i,j-1)) {
				neighbors.add(grid[i][j-1]);
			}
			if (inGrid(i,j+1)) {
				neighbors.add(grid[i][j+1]);
			}
			if (inGrid(i,j+2)) {
				neighbors.add(grid[i][j+2]);
			}
			if (inGrid(i+1,j-1)) {
				neighbors.add(grid[i+1][j-1]);
			}
			if (inGrid(i+1,j)) {
				neighbors.add(grid[i+1][j]);
			}
			if (inGrid(i+1,j-1)) {
				neighbors.add(grid[i+1][j-1]);
			}
		}
	}
	
	private void square_cardinal(List<Cell> neighbors, Cell[][] grid, int i, int j) {
		if (inGrid(i-1,j)) {
			neighbors.add(grid[i-1][j]);
		}
		if (inGrid(i+1,j)) {
			neighbors.add(grid[i+1][j]);
		}
		if (inGrid(i,j-1)) {
			neighbors.add(grid[i][j-1]);
		}
		if (inGrid(i,j+1)) {
			neighbors.add(grid[i][j+1]);
		}
	}
	
	private void square_diagonal(List<Cell> neighbors, Cell[][] grid, int i, int j) {
		if (inGrid(i-1,j-1)) {
			neighbors.add(grid[i-1][j-1]);
		}
		if (inGrid(i+1,j+1)) {
			neighbors.add(grid[i+1][j+1]);
		}
		if (inGrid(i+1,j-1)) {
			neighbors.add(grid[i+1][j-1]);
		}
		if (inGrid(i-1,j+1)) {
			neighbors.add(grid[i-1][j+1]);
		}
	}
	
	private void square_all(List<Cell> neighbors, Cell[][] grid, int i, int j) {
		square_cardinal(neighbors, grid, i, j);
		square_diagonal(neighbors, grid, i, j);
	}
	
	/**
	 * Checks to see if cell location is in the bounds of the grid
	 * @param x - x location of cell in grid
	 * @param y - y location of cell in grid
	 * @return if location is in the bounds of the grid
	 */
	private boolean inGrid (int x, int y) {
		return (x >= 0 && x < myWidth && y >= 0 && y < myHeight);
	}
}
