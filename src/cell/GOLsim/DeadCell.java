package cell.GOLsim;


import java.util.ArrayList;
import cell.Cell;
import javafx.scene.paint.Color;

/**
 * @author Yashas Manjunatha
 * Creates and describes behavior of a Dead Cell in the GOL Simulation.
 * Extends GOLSimCell class.
 */
public class DeadCell extends GOLSimCell{
	
	/**
	 * Creates a new dead cell.
	 */
	public DeadCell() {
		this.DISPLAYCOLOR = Color.GRAY;
	}

	/* (non-Javadoc)
	 * @see cell.Cell#nextState(java.util.ArrayList)
	 */
	@Override
	public Cell nextState(ArrayList<Cell> neighbors) {
		int neighbors_alive = 0;
		for(Cell cell:neighbors) {
			if (cell instanceof AliveCell)
				neighbors_alive++;
		}
		
		if (neighbors_alive == 3)
			return new AliveCell();
		return this;
	}
}
