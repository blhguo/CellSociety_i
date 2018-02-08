package cell.GOLsim;


import java.util.List;
import cell.Cell;
import javafx.scene.paint.Color;

/**
 * @author Yashas Manjunatha
 * Creates and describes behavior of a Dead Cell in the GOL Simulation.
 * Extends GOLSimCell class.
 */
public class DeadCell extends GOLSimCell{
	private static final Color CELLCOLOR = Color.GRAY;

	/**
	 * Creates a new dead cell.
	 */
	public DeadCell() {
		setDisplayColor();
	}

	/* (non-Javadoc)
	 * @see cell.Cell#nextState(java.util.List)
	 */
	@Override
	public Cell nextState(List<Cell> neighbors) {
		int neighbors_alive = 0;
		for(Cell cell:neighbors) {
			if (cell instanceof AliveCell) {
				neighbors_alive++;
			}
		}

		if (neighbors_alive == 3) {
			return new AliveCell();
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see cell.Cell#setDisplayColor()
	 */
	@Override
	protected void setDisplayColor() {
		this.display_color = CELLCOLOR;
	}
}
