package cell.firesim;

import java.util.ArrayList;
import cell.Cell;
import javafx.scene.paint.Color;

/**
 * @author Yashas Manjunatha
 * Creates and describes behavior of an Empty Cell in the Fire Simulation.
 * Extends FireSimCell class.
 *
 */
public class EmptyCell extends FireSimCell{

	/**
	 * Creates a new empty cell with the following parameters.
	 * @param pCatch - probability of tree cell catching on fire
	 * @param pLight - probability of tree cell getting struck by lightning
	 * @param pGrow - probability of tree cell growing in empty cell
	 */
	public EmptyCell(double pCatch, double pLight, double pGrow) {
		super(pCatch, pLight, pGrow);
		this.DISPLAYCOLOR = Color.LIGHTGRAY;
	}
	
	
	/* (non-Javadoc)
	 * @see cell.Cell#nextState(java.util.ArrayList)
	 */
	@Override
	public Cell nextState(ArrayList<Cell> neighbors) {
		if (Math.random() <= probGrow)
			return new TreeCell(probCatch, probLightning, probGrow);
		return this;
	}
	
	/* (non-Javadoc)
	 * @see cell.firesim.FireSimCell#isBurning()
	 */
	@Override
	public boolean isBurning() {
		return false;
	}
}
