package cell.sugarsim;

import java.util.List;

import cell.Cell;

/**
 * @author Yashas Manjunatha
 * Creates and describes behavior of an Empty Cell in the Sugar Simulation.
 * Extends SuagrSimCell class.
 */
public class EmptyCell extends SugarSimCell{

	/**
	 * Creates a new empty cell.
	 * @param patch_sugar amount of sugar in that patch the agent is on
	 * @param max_sugar max amount of sugar in that patch the agent is on
	 * @param sugarGBR sugar grow back rate
	 * @param sugarGBI sugar grow back interval
	 * @param tick time
	 */
	public EmptyCell(int patch_sugar, int max_sugar, int sugarGBR, int sugarGBI, int tick) {
		super(patch_sugar, max_sugar, sugarGBR, sugarGBI, tick);
	}

	/* (non-Javadoc)
	 * @see cell.Cell#nextState(java.util.List)
	 */
	@Override
	public Cell nextState(List<Cell> neighbors) {
		patchGrowBack();
		return this;
	}
}