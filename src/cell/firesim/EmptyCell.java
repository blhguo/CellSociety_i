package cell.firesim;

import java.util.HashSet;

import cell.Cell;
import javafx.scene.paint.Color;

public class EmptyCell extends FireSimCell{
	private double probGrow;

	public EmptyCell() {
		this.DISPLAYCOLOR = Color.WHITE;
	}
	
	public void setProbGrow(double prob) {
		probGrow = prob;
	}
	
	@Override
	public Cell nextState(HashSet<Cell> neighbors) {
		if (Math.random() <= probGrow)
			return new TreeCell();
		return this;
	}
	
	@Override
	public boolean isBurning() {
		return false;
	}
}
