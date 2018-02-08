package cell.segregation;

import java.util.List;
import cell.Cell;
import javafx.scene.paint.Color;

/**
 * @author Yashas Manjunatha
 * Creates and describes behavior of an X Cell in the Segregation Simulation.
 * Extends SegregationSimCell class.
 */
public class XCell extends SegregationSimCell{
	private static final Color CELLCOLOR = Color.PINK;
	private double threshold;

	/**
	 * Creates a new X cell.
	 * @param t - satisfaction threshold
	 */
	public XCell(double t) {
		threshold = t;
		setDisplayColor();
	}

	/* (non-Javadoc)
	 * @see cell.Cell#nextState(java.util.List)
	 */
	@Override
	public Cell nextState(List<Cell> neighbors) {
		double xcells = 0;
		double total = 0;
		for (Cell cell:neighbors) {
			if (cell instanceof XCell) {
				xcells++;
				total++;
			}
			if (cell instanceof OCell) {
				total++;
			}

		}
		if (xcells/total < threshold) {
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
