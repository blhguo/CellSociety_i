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
}
