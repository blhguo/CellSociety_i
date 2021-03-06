package cell.watorsim;

import java.util.List;
import cell.Cell;
import javafx.scene.paint.Color;

/**
 * @author Yashas Manjunatha
 * Creates and describes behavior of a Shark Cell in the Wator Simulation.
 * Extends WatorSimCell class.
 *
 */
public class SharkCell extends WatorSimCell{
	private static final Color CELLCOLOR = Color.BLUE;
	private int reproduction_time;
	private int reproduction_threshold;
	private int current_energy;
	private int gained_energy;
	private boolean isReproducing;

	/**
	 * Creates a new shark cell.
	 * @param x - x location of cell in grid
	 * @param y - y location of cell in grid
	 * @param reproduction_threshold - threshold for reproduction of new shark cell
	 * @param current_energy - current energy of shark
	 * @param gained_energy - energy shark will gain if it eats a fish
	 */
	public SharkCell(int x, int y, int reproduction_threshold, int current_energy, int gained_energy) {
		super(x, y);
		resetReproduction();
		this.reproduction_threshold = reproduction_threshold;
		this.current_energy = current_energy;
		this.gained_energy = gained_energy;
		setDisplayColor();
	}

	/**
	 * Returns an instance of SharkCell by duplicating another instance of SharkCell
	 * @param shark_cell - SharkCell to be duplicated
	 */
	public SharkCell(SharkCell shark_cell) {
		super(shark_cell.getX(), shark_cell.getY());
		this.reproduction_time = shark_cell.reproduction_time;
		this.reproduction_threshold = shark_cell.reproduction_threshold;
		this.current_energy = shark_cell.current_energy;
		this.gained_energy = shark_cell.gained_energy;
		this.isReproducing = shark_cell.isReproducing;
		setDisplayColor();
	}
	
	/**
	 * Gets reproduction time
	 * @return reproduction time
	 */
	public int getReproductionTime() {
		return this.reproduction_time;
	}
	
	/**
	 * Gets reproduction threshold
	 * @return reproduction threshold
	 */
	public int getReproductionThreshold() {
		return this.reproduction_threshold;
	}
	
	/**
	 * Sets reproduction threshold
	 * @param t reproduction threshold
	 */
	public void setReproductionThreshold(double t) {
		this.reproduction_threshold = (int) t;
	}
	
	/**
	 * Gets current energy
	 * @return current energy
	 */
	public int getEnergy() {
		return this.current_energy;
	}
	
	/**
	 * Gets gained energy
	 * @return gained energy
	 */
	public int getGainedEnergy() {
		return this.gained_energy;
	}
	
	/**
	 * Sets gained energy
	 * @param e gained energy
	 */
	public void setGainedEnergy(double e) {
		this.gained_energy = (int) e;
	}

	/* (non-Javadoc)
	 * @see cell.Cell#nextState(java.util.List)
	 */
	@Override
	public Cell nextState(List<Cell> neighbors) {
		reproduction_time++;
		current_energy--;

		if (current_energy <= 0) {
			return new EmptyCell(this.getX(), this.getY());
		}

		if (reproduction_time >= reproduction_threshold) {
			this.isReproducing = true;
			return new SharkCell(this.getX(), this.getY(), reproduction_threshold, current_energy, gained_energy);
		}

		return this;
	}

	/**
	 * Updates energy value after shark eats a fish
	 */
	public void gainEnergy() {
		this.current_energy += this.gained_energy;
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
