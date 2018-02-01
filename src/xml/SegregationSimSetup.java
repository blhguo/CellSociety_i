package xml;

public class SegregationSimSetup extends SimulationSetup{
	private double[][] threshold;
	
	/**
	 * @see SimulationSetup#SimulationSetup(String n, String t, String a, String s, int xSize, int ySize, int gridX, int gridY,
	 *		String[][] typeArray)
	 */
	public SegregationSimSetup(String n, String t, String a, String s, int xSize, int ySize, int gridX, int gridY,
			String[][] typeArray, double[][] thresh) {
		super(n, t, a, s, xSize, ySize, gridX, gridY, typeArray);
		threshold = thresh;
	}
	
	/**
	 * returns the threshold of each cell of the simulation (for initial state)
	 */
	@Override
	public double[][] getThreshold() {
		return threshold;
	}
	
	@Override
	public void printInfo() {
		super.printInfo();
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
