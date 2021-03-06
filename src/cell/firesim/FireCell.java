package cell.firesim;

import java.util.List;
import cell.Cell;
import javafx.scene.paint.Color;

/**
 * @author Yashas Manjunatha
 * Creates and describes behavior of a Fire Cell in the Fire Simulation.
 * Extends FireSimCell class.
 */
public class FireCell extends FireSimCell{
	private static final Color CELLCOLOR = Color.DARKORANGE;

	/**
	 * Creates a new fire cell with the following parameters.
	 * @param pCatch - probability of tree cell catching on fire
	 * @param pLight - probability of tree cell getting struck by lightning
	 * @param pGrow - probability of tree cell growing in empty cell
	 */
	public FireCell(double pCatch, double pLight, double pGrow) {
		super(pCatch, pLight, pGrow);
		setDisplayColor();
	}

	/* (non-Javadoc)
	 * @see cell.Cell#nextState(java.util.List)
	 */
	@Override
	public Cell nextState(List<Cell> neighbors) {
		return new EmptyCell(probCatch, probLightning, probGrow);
	}

	/* (non-Javadoc)
	 * @see cell.Cell#setDisplayColor()
	 */
	@Override
	protected void setDisplayColor() {
		this.display_color = CELLCOLOR;
	}
}
