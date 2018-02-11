package xml.SimSetups;

import xml.SimulationSetup;

public class WatorSimSetup extends SimulationSetup{
	private String[][] cellArray;
	private int[][] reproductionArray;
	private int[][] gainedEnergyArray;
	private int[][] energyArray;
	private int[][] currentEnergyArray;
	private int[][] currentReproductionArray;
	
	/**
	 * @see SimulationSetup#SimulationSetup(String n, String t, String a, String s, int xSize, int ySize, int gridX, int gridY)
	 * @param typeArray
	 * @param rA
	 * @param gEA
	 * @param eA
	 */
	public WatorSimSetup(String n, String t, String a, String s, String nT, String eT, int xSize, int ySize, int gridX, int gridY,
			String[][] typeArray, int[][] rA, int[][] gEA, int[][] eA, int[][]cEA, int[][]cRA) {
		super(n, t, a, s, nT, eT, xSize, ySize, gridX, gridY);
		cellArray = typeArray;
		reproductionArray = rA;
		gainedEnergyArray = gEA;
		energyArray = eA;
		currentEnergyArray= cEA;
		currentReproductionArray = cRA;
	}
	
	
	/**
	 * Alternative setup
	 * @param simSetup
	 * @param typeArray
	 * @param rA
	 * @param gEA;
	 * @param eA;
	 */
	public WatorSimSetup(SimulationSetup simSetup, String[][] typeArray, int[][] rA, int[][] gEA, int[][] eA, int[][]cEA, int[][]cRA) {
		super(simSetup.getName(), simSetup.getTitle(), simSetup.getAuthor(), simSetup.getShape(), simSetup.getNeighbourType(), 
				simSetup.getEdgeType(), simSetup.getCellX(), simSetup.getCellY(), simSetup.getGridX(), simSetup.getGridY());
		cellArray = typeArray;
		reproductionArray = rA;
		gainedEnergyArray = gEA;
		energyArray = eA;
		currentEnergyArray= cEA;
		currentReproductionArray = cRA;
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
	public int[][] getReproduction() {
		return reproductionArray;
	}
	
	/**
	 * returns the energy gained per fish eaten of each cell of the simulation (for initial state)
	 */
	public int[][] getGainedEnergy() {
		return gainedEnergyArray;
	}
	
	/**
	 * returns the energy of each cell of the simulation (for initial state)
	 */
	public int[][] getEnergy() {
		return energyArray;
	}
	
	/**
	 * returns the current energy of each cell of the simulation (for initial state)
	 */
	public int[][] getCurrentEnergy() {
		return currentEnergyArray;
	}
	
	/**
	 * returns the energy of each cell of the simulation (for initial state)
	 */
	public int[][] getCurrentReproduction() {
		return currentReproductionArray;
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
		System.out.println("reproduction = ");
		for(int i = 0; i<reproductionArray[1].length; i++)
		{
		    for(int j = 0; j<reproductionArray[0].length; j++)
		    {
		        System.out.print(reproductionArray[j][i]);
		        System.out.print(" ");
		    }
		    System.out.println("");
		}
		System.out.println("gained energy = ");
		for(int i = 0; i<gainedEnergyArray[1].length; i++)
		{
		    for(int j = 0; j<gainedEnergyArray[0].length; j++)
		    {
		        System.out.print(gainedEnergyArray[j][i]);
		        System.out.print(" ");
		    }
		    System.out.println("");
		}
		System.out.println("energy = ");
		for(int i = 0; i<energyArray[1].length; i++)
		{
		    for(int j = 0; j<energyArray[0].length; j++)
		    {
		        System.out.print(energyArray[j][i]);
		        System.out.print(" ");
		    }
		    System.out.println("");
		}
		System.out.println("current energy = ");
		for(int i = 0; i<currentEnergyArray[1].length; i++)
		{
		    for(int j = 0; j<currentEnergyArray[0].length; j++)
		    {
		        System.out.print(currentEnergyArray[j][i]);
		        System.out.print(" ");
		    }
		    System.out.println("");
		}
		System.out.println("current reproduction = ");
		for(int i = 0; i<currentReproductionArray[1].length; i++)
		{
		    for(int j = 0; j<currentReproductionArray[0].length; j++)
		    {
		        System.out.print(currentReproductionArray[j][i]);
		        System.out.print(" ");
		    }
		    System.out.println("");
		}
	}

}
