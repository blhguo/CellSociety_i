package cell.sugarsim;

import java.util.List;

import cell.Cell;

/**
 * @author Yashas Manjunatha
 * Creates and describes behavior of an Empty Cell in the Sugar Simulation.
 * Extends SuagrSimCell class.
 */
public class EmptyCell extends SugarSimCell{
	private boolean isOccupied;

	/**
	 * Creates a new empty cell.
	 * @param patch_sugar amount of sugar in that patch the agent is on
	 * @param max_sugar max amount of sugar in that patch the agent is on
	 * @param sugarGBR sugar grow back rate
	 * @param sugarGBI sugar grow back interval
	 * @param tick time
	 */
	public EmptyCell(int i, int j, int patch_sugar, int max_sugar, int sugarGBR, int sugarGBI, int tick) {
		super(i, j, patch_sugar, max_sugar, sugarGBR, sugarGBI, tick);
		isOccupied = false;
	}

	/* (non-Javadoc)
	 * @see cell.Cell#nextState(java.util.List)
	 */
	@Override
	public Cell nextState(List<Cell> neighbors) {
		patchGrowBack();
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