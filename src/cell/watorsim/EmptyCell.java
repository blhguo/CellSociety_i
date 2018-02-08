package cell.watorsim;

import java.util.List;
import cell.Cell;
import javafx.scene.paint.Color;

/**
 * @author Yashas Manjunatha
 * Creates and describes behavior of an Empty Cell in the Wator Simulation.
 * Extends WatorSimCell class.
 *
 */
public class EmptyCell extends WatorSimCell{
	private static final Color CELLCOLOR = Color.LIGHTGRAY;
	private boolean isOccupied;

	/**
	 * Creates a new empty cell.
	 * @param x - x location of cell in grid
	 * @param y - y location of cell in grid
	 */
	public EmptyCell(int x, int y) {
		super(x, y);
		setOccupied(false);
		setDisplayColor();
	}

	/**
	 * Returns an instance of EmptyCell by duplicating another instance of EmptyCell
	 * @param empty_cell
	 */
	public EmptyCell(EmptyCell empty_cell) {
		super(empty_cell.getX(), empty_cell.getY());
		setOccupied(empty_cell.isOccupied);
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

	/* (non-Javadoc)
	 * @see cell.Cell#setDisplayColor()
	 */
	@Override
	protected void setDisplayColor() {
		this.display_color = CELLCOLOR;
	}

	/* (non-Javadoc)
	 * @see cell.watorsim.WatorSimCell#isReproducing()
	 */
	@Override
	public boolean isReproducing() {
		return false;
	}

	/* (non-Javadoc)
	 * @see cell.watorsim.WatorSimCell#resetReproduction()
	 */
	@Override
	public void resetReproduction() throws UnsupportedOperationException{
		//this cell never reproduces
	}
}
