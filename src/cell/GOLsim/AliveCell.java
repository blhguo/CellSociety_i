package cell.GOLsim;

import java.util.List;
import cell.Cell;
import javafx.scene.paint.Color;

/**
 * @author Yashas Manjunatha
 * Creates and describes behavior of an Alive Cell in the GOL Simulation.
 * Extends GOLSimCell class.
 */
public class AliveCell extends GOLSimCell{
	private static final Color CELLCOLOR = Color.GREENYELLOW;
	
	/**
	 * Creates a new alive cell.
	 */
	public AliveCell() {
		setDisplayColor();
	}

	/* (non-Javadoc)
	 * @see cell.Cell#nextState(java.util.List)
	 */
	@Override
	public Cell nextState(List<Cell> neighbors) {
		int neighbors_alive = 0;
		for(Cell cell:neighbors) {
			if (cell instanceof AliveCell)
				neighbors_alive++;
		}
		
		if (neighbors_alive == 2 || neighbors_alive == 3)
			return this;
		else //if (neighbors_alive < 2 || neighbors_alive > 3)
			return new DeadCell();
	}
	
	/* (non-Javadoc)
	 * @see cell.Cell#setDisplayColor()
	 */
	@Override
	protected void setDisplayColor() {
		this.display_color = CELLCOLOR;
	}
}
