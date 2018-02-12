package cell.firesim;

import cell.Cell;

/**
 * @author Yashas Manjunatha
 *	Abstract class that creates and describes behavior of a cell in the Fire Simulation.
 *	Create a subclass to introduce a new type of cell in this simulation.
 *	Extends the Cell class.
 */
public abstract class FireSimCell extends Cell{
	protected double probCatch;
	protected double probLightning;
	protected double probGrow;

	/**
	 * Creates a new cell in the fire simulation with the following parameters.
	 * @param pCatch - probability of tree cell catching on fire
	 * @param pLight - probability of tree cell getting struck by lightning
	 * @param pGrow - probability of tree cell growing in empty cell
	 */
	public FireSimCell (double pCatch, double pLight, double pGrow) {
		probCatch = pCatch;
		probLightning = pLight;
		probGrow = pGrow;
	}
	
	/**
	 * Gets probability of cell catching on fire
	 * @return probability of cell catching on fire
	 */
	public double getFireProb() {
		return this.probCatch;
	}
	
	/**
	 * Sets probability of cell catching on fire
	 * @param p probability of cell catching on fire
	 */
	public void setFireProb(double p) {
		this.probCatch = p;
	}
	
	/**
	 * Gets probability of cell struck by lightning
	 * @return probability of cell struck by lightning
	 */
	public double getLightningProb() {
		return this.probLightning;
	}
	
	/**
	 * Sets probability of cell struck by lightning
	 * @param p probability of cell struck by lightning
	 */
	public void setLightningProb(double p) {
		this.probLightning = p;
	}
	
	/**
	 * Gets probability of tree growing
	 * @return probability of tree growing
	 */
	public double getProbGrow() {
		return this.probGrow;
	}
	
	/**
	 * Sets probability of tree growing
	 * @param p probability of tree growing
	 */
	public void setProbGrow(double p) {
		this.probGrow = p;
	}
}
