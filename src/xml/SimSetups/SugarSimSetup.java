package xml.SimSetups;

import xml.SimulationSetup;

public class SugarSimSetup extends SimulationSetup{
	private int[][] patchSugarArray;
	private int[][] patchMaxSugarArray;
	private int[][] tickArray;
	private String[][] agentArray;
	private int[][] agentSugarArray;
	private int[][] agentMetabolismArray;
	private int[][] agentVisionArray;
	private int growBackInterval;
	private int growBackRate;
	
	/**
	 * @see SimulationSetup#SimulationSetup(String n, String t, String a, String s, int xSize, int ySize, int gridX, int gridY)
	 * @param psA (patchSugarArray)
	 * @param pMSA (patchMaxSugarArray)
	 * @param tA (tickArray)
	 * @param aA (agentArray)
	 * @param aSA (agentSugarArray)
	 * @param aMA (agentMetabolismArray)
	 * @param aVA (agentVisionArray)
	 */
	public SugarSimSetup(String n, String t, String a, String s, String nT, String eT, int xSize, int ySize, int gridX, int gridY,
			int gBI, int gBR, int[][]pSA, int[][] pMSA, int[][] tA, String[][] aA, int[][] aSA, int[][] aMA, int[][] aVA) {
		super(n, t, a, s, nT, eT, xSize, ySize, gridX, gridY);
		growBackInterval = gBI;
		growBackRate = gBR;
		patchSugarArray = pSA;
		patchMaxSugarArray = pMSA;
		tickArray = tA;
		agentArray = aA;
		agentSugarArray = aSA;
		agentMetabolismArray = aMA;
		agentVisionArray = aVA;
	}
	
	
	/**
	 * Alternative setup
	 * @param simSetup
	 * @param typeArray
	 * @param thresh
	 */
	public SugarSimSetup(SimulationSetup simSetup, int gBI, int gBR, int[][]pSA, int[][] pMSA, int[][] tA, String[][] aA, int[][] aSA, int[][] aMA, int[][] aVA) {
		super(simSetup.getName(), simSetup.getTitle(), simSetup.getAuthor(), simSetup.getShape(), simSetup.getNeighbourType(), 
				simSetup.getEdgeType(), simSetup.getCellX(), simSetup.getCellY(), simSetup.getGridX(), simSetup.getGridY());
		growBackInterval = gBI;
		growBackRate = gBR;
		patchSugarArray = pSA;
		patchMaxSugarArray = pMSA;
		tickArray = tA;
		agentArray = aA;
		agentSugarArray = aSA;
		agentMetabolismArray = aMA;
		agentVisionArray = aVA;
	}
	
	/**
	 * returns the grow back interval
	 */
	public int getGrowBackInterval() {
		return growBackInterval;
	}
	
	/**
	 * returns the grow back rate
	 */
	public int getGrowBackRate() {
		return growBackRate;
	}
	
	
	/**
	 * returns the sugar of the patches
	 */
	public int[][] getPatchSugarArray() {
		return patchSugarArray;
	}
	
	
	/**
	 * returns the max sugar of the patches
	 */
	public int[][] getPatchMaxSugarArray() {
		return patchMaxSugarArray;
	}
	
	/**
	 * returns the max sugar of the patches
	 */
	public int[][] getPatchTickArray() {
		return tickArray;
	}
	
	/**
	 * returns the array of agent locations
	 */
	public String[][] getAgentArray() {
		return agentArray;
	}
	
	/**
	 * returns the sugar of the agents
	 */
	public int[][] getAgentSugarArray() {
		return agentSugarArray;
	}
	
	/**
	 * returns the metabolism of the agents
	 */
	public int[][] getAgentMetabolismArray() {
		return agentMetabolismArray;
	}
	
	/**
	 * returns the vision of the agents
	 */
	public int[][] getAgentVisionArray() {
		return agentVisionArray;
	}
	
	/**
	 * @see SimulationSetup:printInfo()
	 */
	@Override
	public void printInfo() {
		super.printInfo();
		System.out.println("patchSugarArray = ");
		for(int i = 0; i<patchSugarArray[0].length; i++)
		{
		    for(int j = 0; j<patchSugarArray.length; j++)
		    {
		        System.out.print(patchSugarArray[j][i]);
		        System.out.print(" ");
		    }
		    System.out.println("");
		}
		System.out.println("agentArray = ");
		for(int i = 0; i<agentArray[0].length; i++)
		{
		    for(int j = 0; j<agentArray.length; j++)
		    {
		        System.out.print(agentArray[j][i]);
		        System.out.print(" ");
		    }
		    System.out.println("");
		}
		System.out.println("Grow back interval = " + growBackInterval);
		System.out.println("Grow back rate = " + growBackRate);
	}
}
