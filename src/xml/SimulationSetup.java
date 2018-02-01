package xml;

import java.util.Arrays;

public class SimulationSetup {
	private String name;
	private String title;
	private String author;
	private String cell_shape;
	private int cell_xsize;
	private int cell_ysize;
	private int grid_x;
	private int grid_y;
	private String[][] cellArray;
	
	public SimulationSetup(String n, String t, String a, String s, int xSize, int ySize, 
			int gridX, int gridY, String[][] typeArray) {
		name =  n;
		title = t;
		author = a;
		cell_shape = s;
		cell_xsize = xSize;
		cell_ysize = ySize;
		grid_x = gridX;
		grid_y = gridY;
		cellArray = typeArray;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getShape() {
		return cell_shape;
	}
	
	public int getCellX() {
		return cell_xsize;
	}
	
	public int getCellY() {
		return cell_ysize;
	}
	
	public int getGridX() {
		return grid_x;
	}
	
	public int getGridY() {
		return grid_y;
	}
	
	public String[][] getArray() {
		return cellArray;
	}
	
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
		
		System.out.println("cellArray = ");
		for(int i = 0; i<cellArray[1].length; i++)
		{
		    for(int j = 0; j<cellArray[0].length; j++)
		    {
		        System.out.print(cellArray[j][i]);
		        System.out.print(" ");
		    }
		    System.out.println("");
		}
	}
}
