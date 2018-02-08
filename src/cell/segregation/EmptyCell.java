package cell.segregation;

import java.util.List;
import cell.Cell;
import javafx.scene.paint.Color;

/**
 * @author Yashas Manjunatha
 * Creates and describes behavior of an Empty Cell in the Segregation Simulation.
 * Extends SegregationSimCell class.
 */
public class EmptyCell extends SegregationSimCell{
	private static final Color CELLCOLOR = Color.LIGHTGRAY;
	private boolean isTaken = false;

	/**
	 * Creates a new empty cell.
	 */
	public EmptyCell() {
		isTaken = false;
		setDisplayColor();
	}
	
	/* (non-Javadoc)
	 * @see cell.Cell#nextState(java.util.List)
	 */
	@Override
	public Cell nextState(List<Cell> neighbors) {
		return this;
	}

	/**
	 * @return if the empty cell is taken by another cell in the next step
	 */
	public boolean isTaken() {
		return isTaken;
	}

	/**
	 * Sets the value for isTaken
	 * @param isTaken boolean value if cell is taken by another cell in the next step
	 */
	public void setTaken(boolean isTaken) {
		this.isTaken = isTaken;
	}
	
	/* (non-Javadoc)
	 * @see cell.Cell#setDisplayColor()
	 */
	@Override
	protected void setDisplayColor() {
		this.display_color = CELLCOLOR;
	}
}
