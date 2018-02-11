package cell.watorsim;

import java.util.List;
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

	/**
	 * Returns an instance of FishCell by duplicating another instance of FishCell
	 * @param fish_cell - fish cell to be duplicated
	 */
	public FishCell(FishCell fish_cell) {
		super(fish_cell.getX(), fish_cell.getY());
		this.reproduction_time = fish_cell.reproduction_time;
		this.reproduction_threshold = fish_cell.reproduction_threshold;
		this.setEaten(fish_cell.isEaten);
		this.isReproducing = fish_cell.isReproducing;
		setDisplayColor();
	}
	
	public int getReproductionTime() {
		return this.reproduction_time;
	}
	
	public int getReproductionThreshold() {
		return this.reproduction_threshold;
	}
	
	public void setReproductionThreshold(double t) {
		this.reproduction_threshold = (int) t;
	}

	/* (non-Javadoc)
	 * @see cell.Cell#nextState(java.util.List)
	 */
	@Override
	public Cell nextState(List<Cell> neighbors) {
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

	/* (non-Javadoc)
	 * @see cell.watorsim.WatorSimCell#isReproducing()
	 */
	@Override
	public boolean isReproducing() {
		return isReproducing;
	}

	/* (non-Javadoc)
	 * @see cell.watorsim.WatorSimCell#resetReproduction()
	 */
	@Override
	public void resetReproduction() {
		this.reproduction_time = 0;
		this.isReproducing = false;
	}

	/* (non-Javadoc)
	 * @see cell.Cell#setDisplayColor()
	 */
	@Override
	protected void setDisplayColor() {
		this.display_color = CELLCOLOR;
	}
}
