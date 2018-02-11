package xml.SimSetups;

import xml.SimulationSetup;

public class GOLSimSetup extends SimulationSetup{
	private String[][] cellArray;
	
	/**
	 * GOL Simulation Setup
	 * 
	 * @see SimulationSetup#SimulationSetup(String n, String t, String a, String s, int xSize, int ySize, int gridX, int gridY)
	 * @param typeArray
	 */
	public GOLSimSetup(String n, String t, String a, String s, String nT, String eT, int xSize, int ySize, int gridX, int gridY,
			String[][] typeArray) {
		super(n, t, a, s, nT, eT, xSize, ySize, gridX, gridY);
		cellArray = typeArray;
	}
	
	
	/**
	 * Alternative setup
	 * @param simSetup
	 * @param typeArray
	 */
	public GOLSimSetup(SimulationSetup simSetup, String[][] typeArray) {
		super(simSetup.getName(), simSetup.getTitle(), simSetup.getAuthor(), simSetup.getShape(), simSetup.getNeighbourType(), 
				simSetup.getEdgeType(), simSetup.getCellX(), simSetup.getCellY(), simSetup.getGridX(), simSetup.getGridY());
		cellArray = typeArray;
	}
	
	
	/**
	 * returns the cell array of the simulation
	 */
	public String[][] getArray() {
		return cellArray;
	}
	
	/**
	 * @see SimulationSetup:printInfo()
	 */
	@Override
	public void printInfo() {
		super.printInfo();
		
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
