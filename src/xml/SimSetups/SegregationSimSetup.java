package xml.SimSetups;

import xml.SimulationSetup;

public class SegregationSimSetup extends SimulationSetup{
	private String[][] cellArray;
	private double[][] threshold;
	
	/**
	 * @see SimulationSetup#SimulationSetup(String n, String t, String a, String s, int xSize, int ySize, int gridX, int gridY)
	 * @param typeArray
	 * @param thresh
	 */
	public SegregationSimSetup(String n, String t, String a, String s, String nT, String eT, int xSize, int ySize, int gridX, int gridY,
			String[][] typeArray, double[][] thresh) {
		super(n, t, a, s, nT, eT, xSize, ySize, gridX, gridY);
		cellArray = typeArray;
		threshold = thresh;
	}
	
	
	/**
	 * Alternative setup
	 * @param simSetup
	 * @param typeArray
	 * @param thresh
	 */
	public SegregationSimSetup(SimulationSetup simSetup, String[][] typeArray, double[][] thresh) {
		super(simSetup.getName(), simSetup.getTitle(), simSetup.getAuthor(), simSetup.getShape(), simSetup.getNeighbourType(), 
				simSetup.getEdgeType(), simSetup.getCellX(), simSetup.getCellY(), simSetup.getGridX(), simSetup.getGridY());
		cellArray = typeArray;
		threshold = thresh;
	}
	
	
	/**
	 * returns the cell array of the simulation
	 */
	public String[][] getArray() {
		return cellArray;
	}
	
	
	/**
	 * returns the threshold of each cell of the simulation (for initial state)
	 */
	public double[][] getThreshold() {
		return threshold;
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
		System.out.println("threshold = ");
		for(int i = 0; i<threshold[1].length; i++)
		{
		    for(int j = 0; j<threshold[0].length; j++)
		    {
		        System.out.print(threshold[j][i]);
		        System.out.print(" ");
		    }
		    System.out.println("");
		}
	}

}
