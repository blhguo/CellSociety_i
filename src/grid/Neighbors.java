package grid;

import java.util.List;

import cell.Cell;

/**
 * @author Yashas Manjunatha
 * Implements all the methods for different neighbor orientations for all shapes and edge cases.
 *
 */
public class Neighbors {
	private String shape;
	private String arrangement;
	private String edge_type;
	private int myWidth;
	private int myHeight;
	//private Grid myGrid;
	
	/**
	 * Creates Neighbor object based on the parameters.
	 * @param shape - shape of cell
	 * @param arrangement - neighbor arrangement
	 * @param edge_type - edge type
	 * @param grid - the grid
	 */
	public Neighbors(String shape, String arrangement, String edge_type, Grid grid) {
		this.shape = shape;
		this.arrangement = arrangement;
		this.edge_type = edge_type;
		//this.myGrid = grid;
	}
	
	/**
	 * Adds the neighbors of a cell to a list
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
				hexagon_all(neighbors, grid, i, j);
				break;
			case "triangle":
				chooseTriangleNeighborOrienation(neighbors, grid, i, j);
				break;
		}
	}
	
	/**
	 * Chooser for square cells
	 * @param neighbors - list of the cells neighbors
	 * @param grid - the grid of cells
	 * @param i - x location of cell in grid
	 * @param j - y location of cell in grid
	 */
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
		}
	}
	
	/**
	 * Chooser for triangle cells
	 * @param neighbors - list of the cells neighbors
	 * @param grid - the grid of cells
	 * @param i - x location of cell in grid
	 * @param j - y location of cell in grid
	 */
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
	
	/**
	 * Adds all sides of hexagon
	 * @param neighbors - list of the cells neighbors
	 * @param grid - the grid of cells
	 * @param i - x location of cell in grid
	 * @param j - y location of cell in grid
	 */
	private void hexagon_all (List<Cell> neighbors, Cell[][] grid, int i, int j) {
		checkEdgeTypeAndAdd(neighbors, grid, i-2,j);
		checkEdgeTypeAndAdd(neighbors, grid, i+2,j);
		if (i%2 == 0) {
			checkEdgeTypeAndAdd(neighbors, grid, i-1,j-1);
			checkEdgeTypeAndAdd(neighbors, grid, i+1,j-1);
		} else {
			checkEdgeTypeAndAdd(neighbors, grid, i-1,j+1);
			checkEdgeTypeAndAdd(neighbors, grid, i+1,j+1);
		}
		checkEdgeTypeAndAdd(neighbors, grid, i-1,j);
		checkEdgeTypeAndAdd(neighbors, grid, i+1,j);
	}
	
	/**
	 * Adds three most prominent neighbors of triangle
	 * @param neighbors - list of the cells neighbors
	 * @param grid - the grid of cells
	 * @param i - x location of cell in grid
	 * @param j - y location of cell in grid
	 */
	private void triangle_three (List<Cell> neighbors, Cell[][] grid, int i, int j) {
		if (i%2 == j%2) {
			checkEdgeTypeAndAdd(neighbors, grid, i+1,j);
		} else {
			checkEdgeTypeAndAdd(neighbors, grid, i-1,j);
		}
		checkEdgeTypeAndAdd(neighbors, grid, i,j-1);
		checkEdgeTypeAndAdd(neighbors, grid, i,j+1);
	}
	
	/**
	 * Adds all 12 neighbors of triangle
	 * @param neighbors - list of the cells neighbors
	 * @param grid - the grid of cells
	 * @param i - x location of cell in grid
	 * @param j - y location of cell in grid
	 */
	private void triangle_all(List<Cell> neighbors, Cell[][] grid, int i, int j) {
		checkEdgeTypeAndAdd(neighbors, grid, i,j-2);
		checkEdgeTypeAndAdd(neighbors, grid, i,j-1);
		checkEdgeTypeAndAdd(neighbors, grid, i,j+1);
		checkEdgeTypeAndAdd(neighbors, grid, i,j+2);
		
		if (i%2 == j%2) {
			checkEdgeTypeAndAdd(neighbors, grid, i-1,j-1);
			checkEdgeTypeAndAdd(neighbors, grid, i-1,j);
			checkEdgeTypeAndAdd(neighbors, grid, i-1,j+1);
			checkEdgeTypeAndAdd(neighbors, grid, i+1,j-2);
			checkEdgeTypeAndAdd(neighbors, grid, i+1,j-1);
			checkEdgeTypeAndAdd(neighbors, grid, i+1,j);
			checkEdgeTypeAndAdd(neighbors, grid, i+1,j+1);
			checkEdgeTypeAndAdd(neighbors, grid, i+1,j+2);
		} else {
			checkEdgeTypeAndAdd(neighbors, grid, i-1,j-2);
			checkEdgeTypeAndAdd(neighbors, grid, i-1,j-1);
			checkEdgeTypeAndAdd(neighbors, grid, i-1,j);
			checkEdgeTypeAndAdd(neighbors, grid, i-1,j+1);
			checkEdgeTypeAndAdd(neighbors, grid, i-1,j+2);
			checkEdgeTypeAndAdd(neighbors, grid, i+1,j-1);
			checkEdgeTypeAndAdd(neighbors, grid, i+1,j);
			checkEdgeTypeAndAdd(neighbors, grid, i+1,j-1);
		}
	}
	
	/**
	 * Adds cardinal orientation neighbors of square
	 * @param neighbors - list of the cells neighbors
	 * @param grid - the grid of cells
	 * @param i - x location of cell in grid
	 * @param j - y location of cell in grid@param neighbors
	 */
	private void square_cardinal(List<Cell> neighbors, Cell[][] grid, int i, int j) {
		checkEdgeTypeAndAdd(neighbors, grid, i-1,j);
		checkEdgeTypeAndAdd(neighbors, grid, i+1,j);
		checkEdgeTypeAndAdd(neighbors, grid, i,j-1);
		checkEdgeTypeAndAdd(neighbors, grid, i,j+1);
	}
	
	/**
	 * Adds diagonal orientation neighbors of square
	 * @param neighbors - list of the cells neighbors
	 * @param grid - the grid of cells
	 * @param i - x location of cell in grid
	 * @param j - y location of cell in grid
	 */
	private void square_diagonal(List<Cell> neighbors, Cell[][] grid, int i, int j) {
		checkEdgeTypeAndAdd(neighbors, grid, i-1,j-1);
		checkEdgeTypeAndAdd(neighbors, grid, i+1,j+1);
		checkEdgeTypeAndAdd(neighbors, grid, i+1,j-1);
		checkEdgeTypeAndAdd(neighbors, grid, i-1,j+1);
	}
	
	/**
	 * Adds all neighbors of square
	 * @param neighbors - list of the cells neighbors
	 * @param grid - the grid of cells
	 * @param i - x location of cell in grid
	 * @param j - y location of cell in grid
	 */
	private void square_all(List<Cell> neighbors, Cell[][] grid, int i, int j) {
		square_cardinal(neighbors, grid, i, j);
		square_diagonal(neighbors, grid, i, j);
	}
	
	/**
	 * Deals with edge types of grid
	 * @param neighbors - list of the cells neighbors
	 * @param grid - the grid of cells
	 * @param i - x location of cell in grid
	 * @param j - y location of cell in grid
	 */
	private void checkEdgeTypeAndAdd (List<Cell> neighbors, Cell[][] grid, int x, int y) {
		switch(edge_type) {
		case "finite":
			if (inGrid(x,y)) {
				neighbors.add(grid[x][y]);
			}
			break;
		case "infinite":
			//if (!inGrid(x,y))
				//myGrid.extendGrid();
		case "toroidal":
			if (x < 0) {
				x += grid.length;
			}
			if (y < 0) {
				y += grid[0].length;
			}
			if (x >= grid.length) {
				x -= grid.length;
			}
			if (y >= grid[0].length) {
				y -= grid[0].length;
			}
			neighbors.add(grid[x][y]);
			break;
		}
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
