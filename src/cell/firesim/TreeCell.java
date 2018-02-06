package cell.firesim;

import java.util.ArrayList;
import cell.Cell;
import javafx.scene.paint.Color;

/**
 * @author Yashas Manjunatha
 * Creates and describes behavior of a Tree Cell in the Fire Simulation.
 * Extends FireSimCell class.
 */
public class TreeCell extends FireSimCell{
	
	/**
	 * Creates a new tree cell with the following parameters.
	 * @param pCatch - probability of tree cell catching on fire
	 * @param pLight - probability of tree cell getting struck by lightning
	 * @param pGrow - probability of tree cell growing in empty cell
	 */
	public TreeCell(double pCatch, double pLight, double pGrow) {
		super(pCatch, pLight, pGrow);
		this.DISPLAYCOLOR = Color.FORESTGREEN;
	}

	/* (non-Javadoc)
	 * @see cell.Cell#nextState(java.util.ArrayList)
	 */
	@Override
	public Cell nextState(ArrayList<Cell> neighbors) {
		boolean neighborBurning = false;
		for(Cell cell:neighbors) {
			if(cell instanceof FireSimCell)
				if(((FireSimCell) cell).isBurning())
					neighborBurning = true;
		}
		double prob = probCatch;
		if (Math.random() <= probLightning)
			prob *= probLightning;
		if (neighborBurning && Math.random() <= prob)
			return new FireCell(probCatch, probLightning, probGrow);
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
