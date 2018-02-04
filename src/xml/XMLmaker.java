package xml;

public class XMLmaker {
	private final static String gol = "gol";
	private final static String seg = "seg";
	private final static String wator = "wator";
	private final static String fire =  "fire";
	
	private final static String sim = fire;
	private final static int gridx = 400;
	private final static int gridy = 400;
	private final static int cellx = 10;
	private final static int celly = 10;
	
	
	
	public static void main (String[] args) {
		int numCellsX = (int) gridx / cellx;
		int numCellsY = (int) gridy / celly;
		
		if(sim.equals(gol)){
			
		}
		else if(sim.equals(seg)){
			
		}
		else if(sim.equals(wator)){
			
		}
		else if(sim.equals(fire)){			
			for(int i = 0; i < numCellsX; i++){
				System.out.println("\t" + "<cell>");
				System.out.println("\t" + "\t" + "<type>empty</type>");
				System.out.println("\t" + "\t" + "<x>" + i + "</x>");
				System.out.println("\t" + "\t" + "<y>" + 0 + "</y>");
				System.out.println("\t" + "</cell>");
				System.out.println("");
				System.out.println("\t" + "<cell>");
				System.out.println("\t" + "\t" + "<type>empty</type>");
				System.out.println("\t" + "\t" + "<x>" + i + "</x>");
				System.out.println("\t" + "\t" + "<y>" + (numCellsY-1) + "</y>");
				System.out.println("\t" + "</cell>");
				System.out.println("");
			}
			
			for(int i = 0; i < numCellsY; i++){
				System.out.println("\t" + "<cell>");
				System.out.println("\t" + "\t" + "<type>empty</type>");
				System.out.println("\t" + "\t" + "<x>" + 0 + "</x>");
				System.out.println("\t" + "\t" + "<y>" + i + "</y>");
				System.out.println("\t" + "</cell>");
				System.out.println("");
				System.out.println("\t" + "<cell>");
				System.out.println("\t" + "\t" + "<type>empty</type>");
				System.out.println("\t" + "\t" + "<x>" + (numCellsX-1) + "</x>");
				System.out.println("\t" + "\t" + "<y>" + i+ "</y>");
				System.out.println("\t" + "</cell>");
				System.out.println("");
			}
			
			System.out.println("\t" + "<cell>");
			System.out.println("\t" + "\t" + "<type>fire</type>");
			int centerX = (int) (numCellsX-1)/2;
			int centerY = (int) (numCellsY-1)/2;
			System.out.println("\t" + "\t" + "<x>" + centerX + "</x>");
			System.out.println("\t" + "\t" + "<y>" + centerY + "</y>");
			System.out.println("\t" + "</cell>");
			
			
		}
		else{
			System.out.println("Error, invalid sim type");
		}
	}
}
