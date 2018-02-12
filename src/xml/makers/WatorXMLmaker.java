package xml.makers;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import grid.WatorSimGrid;
import xml.XMLmaker;

public class WatorXMLmaker extends XMLmaker{
	private final static String FILE = "wator_saved";
	private final static String TYPE = "wator";
	private final static String WATOR_TITLE =  "Wa-Tor World";
	private final static String WATOR_AUTHOR =  "A.K. Dewdney";


	// wator world specific
	private double prob_fish = 0.55;
	private double prob_shark = 0.4;
	private int rt_shark = 10;
	private int rt_fish = 5;
	private int e_shark = 5;
	private int e_fish = 0;
	private int ge_shark = 5;
	private int ge_fish = 0;
	private int ce_fish = 0;
	private int ce_shark = 0;
	private int cr_fish = 0;
	private int cr_shark = 0;

	public WatorXMLmaker(String file, String shape, String nT, String eT, int gx, int gy, int cx, int cy, double probFish, double probShark, int rtShark, int rtFish, int eShark, int geShark) throws FileNotFoundException, UnsupportedEncodingException{
		super(file, TYPE, WATOR_TITLE, WATOR_AUTHOR, shape, nT, eT, gx, gy, cx, cy);
		prob_fish = probFish;
		prob_shark = probShark;
		rt_shark = rtShark;
		rt_fish = rtFish;
		ge_shark = geShark;
		e_shark = eShark;
		double fishNum = PROB * prob_fish;
		double sharkNum = PROB * prob_shark + fishNum;
		for(int i = 0; i < numCellsX; i++){
			for(int j = 0; j < numCellsY; j++){
				Random rand = new Random();
				int  n = rand.nextInt(PROB+1);
				String wType = "";
				int rT = 0;
				int gE = 0;
				int e = 0;
				int ce;
				int cr;

				if(n < fishNum){
					wType = "fish";
					rT = rt_fish;
					gE = ge_fish;
					e = e_fish;
					ce = ce_fish;
					cr = cr_fish;

				}
				else if(n > fishNum && n < sharkNum){
					wType = "shark";
					rT = rt_shark;
					gE = ge_shark;
					e = e_shark;
					ce = ce_shark;
					cr = cr_shark;
				}
				else{
					continue;
				}

				printWatorCell(wType, i, j, rT, gE, e, ce, cr);
			}
		}
		closeWriter();
	}
	
	public WatorXMLmaker(WatorSimGrid grid, int gx, int gy, int cx, int cy) throws FileNotFoundException, UnsupportedEncodingException{
		super(FILE, TYPE, WATOR_TITLE, WATOR_AUTHOR);
		gridx = gx;
		gridy = gy;
		cellx = cx;
		celly = cy;
		shape = grid.getShape();
		neighbourType = grid.getNeighborArrangement();
		edgeType = grid.getEdgeType();
		int[][] repThreshArray = grid.getReproductionThreshold(); // both shark and fish
		int[][] currentEnergyArray = grid.getCurrentEnergy();
		int[][] gainedEnergyArray = grid.getGainedEnergy();
		int[][] defaultEnergyArray = grid.getDefaultEnergy();
		int[][] currentReproductionArray = grid.getReproductionTime();
		numCellsX = (int) gridx / cellx;
		numCellsY = (int) gridy / celly;
		printFileHeader2();
		String[][] cellArray = grid.getArray();
		String type;
		for(int i = 0; i < cellArray[0].length; i++){
			for(int j = 0; j < cellArray[1].length; j++){
				type = cellArray[i][j];
				if(!type.equals("empty")){
					printWatorCell(type, i, j, repThreshArray[i][j], gainedEnergyArray[i][j],
							defaultEnergyArray[i][j], currentEnergyArray[i][j], currentReproductionArray[i][j]);
				}
			}
		}
		closeWriter();
	}

	private void printWatorCell(String type, int i, int j, int rT, int gE, int e, int ce, int cr){
		printCellHeader(type, i, j);
		writer.println("\t" + "\t" + "<reproductionThreshold>" + rT + "</reproductionThreshold>");
		writer.println("\t" + "\t" + "<gainedEnergy>" + gE + "</gainedEnergy>");
		writer.println("\t" + "\t" + "<energy>" + e + "</energy>");
		writer.println("\t" + "\t" + "<currentEnergy>" + ce + "</currentEnergy>");
		writer.println("\t" + "\t" + "<currentReproduction>" + cr + "</currentReproduction>");
		printCellFooter();
	}
}
