package cell.watorsim;

import java.util.ArrayList;
import cell.Cell;
import javafx.scene.paint.Color;

/**
 * @author Yashas Manjunatha
 * Creates and describes behavior of a Fish Cell in the Wator Simulation.
 * Extends WatorSimCell class.
 *
 */
public class FishCell extends WatorSimCell{
	private static final Color CELLCOLOR = Color.YELLOW;
	private int reproduction_time;
	private int reproduction_threshold;
	private boolean isEaten;
	private boolean isReproducing;
	
	/**
	 * Creates a new fish cell.
	 * @param x - x location of cell in grid
	 * @param y - y location of cell in grid
	 * @param reproduction_threshold - threshold for reproduction of new fish cell
	 */
	public FishCell(int x, int y, int reproduction_threshold) {
		super(x, y);
		resetReproduction();
		this.reproduction_threshold = reproduction_threshold;
		this.setEaten(false);
		setDisplayColor();
	}

	/* (non-Javadoc)
	 * @see cell.Cell#nextState(java.util.ArrayList)
	 */
	@Override
	public Cell nextState(ArrayList<Cell> neighbors) {
		this.reproduction_time++;
		
		if (reproduction_time >= reproduction_threshold) {
			this.isReproducing = true;
			return new FishCell(this.getX(), this.getY(), reproduction_threshold);
		}
		
		return this;
	}

	/**
	 * @return boolean value if the fish is eaten by a shark in this turn
	 */
	public boolean isEaten() {
		return isEaten;
	}

	/** Sets value of isEaten.
	 * @param isEaten boolean value if the fish is eaten by a shark in this turn
	 */
	public void setEaten(boolean isEaten) {
		this.isEaten = isEaten;
	}
	
	/**
	 * @return boolean value if the fish is reproducing
	 */
	public boolean isReproducing() {
		return isReproducing;
	}
	
	/**
	 * Resets reproducing values (for use after a fish cell has reproduced)
	 */
	public void resetReproduction() {
		this.reproduction_time = 0;
		this.isReproducing = false;
	}

	/* (non-Javadoc)
	 * @see cell.Cell#setDisplayColor()
	 */
	@Override
	protected void setDisplayColor() {
		this.DISPLAYCOLOR = CELLCOLOR;
	}
}
