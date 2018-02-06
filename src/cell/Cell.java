package cell;

import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 * @author Yashas Manjunatha
 * This is an abstract class for cells. To create a new cell type, create a subclass of this.
 * Be sure to define the DISPLAYCOLOR parameter in each subclass.
 * 
 */
public abstract class Cell {
	protected Color DISPLAYCOLOR;
	
	/**
	 * Used to get the next state information of a cell based on rules applied
	 * to the current state and the states of its neighbors.
	 * @param neighbors - List of all neighboring cells.
	 * @return - A cell with the next state information.
	 */
	public abstract Cell nextState (ArrayList<Cell> neighbors);
	
	/**
	 * Used to get the display color of the cell.
	 * @return - The display color of the cell.
	 */
	public Color getDisplayColor() {
		return DISPLAYCOLOR;
	}
}