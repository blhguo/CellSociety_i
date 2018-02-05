package cell.firesim;

import cell.Cell;

public abstract class FireSimCell extends Cell{
	protected double probCatch;
	protected double probLightning;
	protected double probGrow;
	
	public FireSimCell (double pCatch, double pLight, double pGrow) {
		probCatch = pCatch;
		probLightning = pLight;
		probGrow = pGrow;
	}
	
	public abstract boolean isBurning();
}
