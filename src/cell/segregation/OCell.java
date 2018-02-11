package cell.segregation;

import java.util.List;
import cell.Cell;
import javafx.scene.paint.Color;

/**
 * @author Yashas Manjunatha
 * Creates and describes behavior of an O Cell in the Segregation Simulation.
 * Extends SegregationSimCell class.
 *
 */
public class OCell extends SegregationSimCell{
	private static final Color CELLCOLOR = Color.ORANGE;
	private double threshold;

	/**
	 * Creates a new O cell.
	 * @param t - satisfaction threshold
	 */
	public OCell(double t) {
		threshold = t;
		setDisplayColor();
	}
	
	public double getThreshold() {
		return this.threshold;
	}

	/* (non-Javadoc)
	 * @see cell.Cell#nextState(java.util.List)
	 */
	@Override
	public Cell nextState(List<Cell> neighbors) {
		double ocells = 0;
		double total = 0;
		for (Cell cell:neighbors) {
			if (cell instanceof OCell) {
				ocells++;
				total++;
			}
			if (cell instanceof XCell) {
				total++;
			}

		}
		if (ocells/total < threshold) {
			return new EmptyCell();
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
