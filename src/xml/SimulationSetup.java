package xml;

import java.util.Arrays;

/**
 * Simulation Setup - stores all information about a simulation for use by other classes
 * @author Marcus Oertle
 * 
 * inputs:
 * @param n (name)
 * @param t (title)
 * @param a (author)
 * @param s (cell shape)
 * @param xSize (cell x size)
 * @param ySize (cell y size)
 * @param gridX (grid x size)
 * @param gridY (grid y size)
 * @param typeArray (double array of cell types)
 * 
 * returns:
 * none
 */

public class SimulationSetup {
	private String name;
	private String title;
	private String author;
	private String cell_shape;
	private int cell_xsize;
	private int cell_ysize;
	private int grid_x;
	private int grid_y;
	
	public SimulationSetup(String n, String t, String a, String s, int xSize, int ySize, 
			int gridX, int gridY) {
		name =  n;
		title = t;
		author = a;
		cell_shape = s;
		cell_xsize = xSize;
		cell_ysize = ySize;
		grid_x = gridX;
		grid_y = gridY;
	}
	
	/**
	 * returns the name of the simulation
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * returns the title of the simulation
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * returns the author of the simulation
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * returns the cell shape of the simulation
	 */
	public String getShape() {
		return cell_shape;
	}
	
	/**
	 * returns the cell x size of the simulation
	 */
	public int getCellX() {
		return cell_xsize;
	}
	
	/**
	 * returns the cell y size of the simulation
	 */
	public int getCellY() {
		return cell_ysize;
	}
	
	/**
	 * returns the grid x size of the simulation
	 */
	public int getGridX() {
		return grid_x;
	}
	
	/**
	 * returns the grid y size of the simulation
	 */
	public int getGridY() {
		return grid_y;
	}
		
	/**
	 * prints all stored info about the simulation
	 */
	public void printInfo() {
		System.out.print("name = ");
		System.out.println(name);
		
		System.out.print("title = ");
		System.out.println(title);
		
		System.out.print("author = ");
		System.out.println(author);
		
		System.out.print("cell_shape = ");
		System.out.println(cell_shape);
		
		System.out.print("cell_xsize = ");
		System.out.println(cell_xsize);
		
		System.out.print("cell_ysize = ");
		System.out.println(cell_ysize);
		
		System.out.print("grid_x = ");
		System.out.println(grid_x);
		
		System.out.print("grid_y = ");
		System.out.println(grid_y);
	}
}
