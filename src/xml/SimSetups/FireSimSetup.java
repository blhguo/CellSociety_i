package xml.SimSetups;

import xml.SimulationSetup;

public class FireSimSetup extends SimulationSetup{
	private String[][] cellArray;
	private double fireProb;
	private double lightningProb;
	private double emptyTreeProb;
	
	/**
	 * @see SimulationSetup#SimulationSetup(String n, String t, String a, String s, int xSize, int ySize, int gridX, int gridY)
	 * @param typeArray
	 * @param fP
	 * @param lP
	 * @param eTP
	 */
	public FireSimSetup(String n, String t, String a, String s, String nT, String eT, int xSize, int ySize, int gridX, int gridY,
			String[][] typeArray, double fP, double lP, double eTP) {
		super(n, t, a, s, nT, eT, xSize, ySize, gridX, gridY);
		cellArray = typeArray;
		fireProb = fP;
		lightningProb = lP;
		emptyTreeProb = eTP;
	}
	
	
	/**
	 * Alternative setup
	 * @param simSetup
	 * @param typeArray
	 * @param fP
	 * @param lP
	 * @param eTP
	 */
	public FireSimSetup(SimulationSetup simSetup, String[][] typeArray, double fP, double lP, double eTP) {
		super(simSetup.getName(), simSetup.getTitle(), simSetup.getAuthor(), simSetup.getShape(), simSetup.getNeighbourType(), 
				simSetup.getEdgeType(), simSetup.getCellX(), simSetup.getCellY(), simSetup.getGridX(), simSetup.getGridY());
		cellArray = typeArray;
		fireProb = fP;
		lightningProb = lP;
		emptyTreeProb = eTP;
	}
	
	
	/**
	 * returns the cell array of the simulation
	 */
	public String[][] getArray() {
		return cellArray;
	}
	
	
	/**
	 * returns the reproduction of each cell of the simulation (for initial state)
	 */
	public double getFireProb() {
		return fireProb;
	}
	
	/**
	 * returns the energy gained per fish eaten of each cell of the simulation (for initial state)
	 */
	public double getLightningProb() {
		return lightningProb;
	}
	
	/**
	 * returns the energy of each cell of the simulation (for initial state)
	 */
	public double getProbGrow() {
		return emptyTreeProb;
	}
	
	
	/**
	 * @see SimulationSetup:printInfo()
	 */
	@Override
	public void printInfo() {
		super.printInfo();
		System.out.println("cellArray = ");
		for(int i = 0; i<cellArray[0].length; i++)
		{
		    for(int j = 0; j<cellArray.length; j++)
		    {
		        System.out.print(cellArray[j][i]);
		        System.out.print(" ");
		    }
		    System.out.println("");
		}
		System.out.print("probabilty of fire = ");
		System.out.println(fireProb);
		System.out.print("probabilty of lightning = ");
		System.out.println(lightningProb);
		System.out.print("probabilty of new tree = ");
		System.out.println(emptyTreeProb);
	}

}
