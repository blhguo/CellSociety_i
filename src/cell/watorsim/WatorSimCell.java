package cell.watorsim;

import cell.Cell;

/**
 * @author Yashas Manjunatha
 * Abstract class that creates and describes behavior of a cell in the Wator Simulation.
 * Create a subclass to introduce a new type of cell in this simulation.
 * Extends the Cell class.
 *
 */
public abstract class WatorSimCell extends Cell{
	private int myX;
	private int myY;
	
	/**
	 * Creates a new Wator simulation cell.
	 * @param x - x location of cell in grid
	 * @param y - y location of cell in grid
	 */
	public WatorSimCell (int x, int y) {
		this.myX = x;
		this.myY = y;
	}
	
	/**
	 * @return x location of cell in grid
	 */
	public int getX() {
		return myX;
	}
	
	/**
	 * Sets x location of cell in grid
	 * @param x - x location of cell in grid
	 */
	public void setX(int x) {
		this.myX = x;
	}
	
	/**
	 * @return y location of cell in grid
	 */
	public int getY() {
		return myY;
	}
	
	/**
	 * Sets y location of cell in grid
	 * @param y - y location of cell in grid
	 */
	public void setY(int y) {
		this.myY = y;
	}

}
