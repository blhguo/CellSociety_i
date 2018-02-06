package cell.watorsim;

import java.util.ArrayList;
import cell.Cell;
import javafx.scene.paint.Color;

/**
 * @author Yashas Manjunatha
 * Creates and describes behavior of an Empty Cell in the Wator Simulation.
 * Extends WatorSimCell class.
 *
 */
public class EmptyCell extends WatorSimCell{
	private boolean isOccupied;
	
	/**
	 * Creates a new empty cell.
	 * @param x - x location of cell in grid
	 * @param y - y location of cell in grid
	 */
	public EmptyCell(int x, int y) {
		super(x, y);
		setOccupied(false);
		this.DISPLAYCOLOR = Color.LIGHTGRAY;
	}
	
	/* (non-Javadoc)
	 * @see cell.Cell#nextState(java.util.ArrayList)
	 */
	@Override
	public Cell nextState(ArrayList<Cell> neighbors) {
		return this;
	}

	/**
	 * @return if the empty cell is occupied by another cell in the next step
	 */
	public boolean isOccupied() {
		return isOccupied;
	}

	/**
	 * Sets the value for isOccupied
	 * @param isOccupied boolean value if cell is occupied by another cell in the next step
	 */
	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

}
