package cell.segregation;

import java.util.HashSet;

import cell.Cell;
import javafx.scene.paint.Color;

public class EmptyCell extends SegregationSimCell{
	private boolean isTaken = false;

	public EmptyCell() {
		isTaken = false;
		this.DISPLAYCOLOR = Color.LIGHTGRAY;
	}
	
	@Override
	public Cell nextState(HashSet<Cell> neighbors) {
		return this;
	}

	public boolean isTaken() {
		return isTaken;
	}

	public void setTaken(boolean isTaken) {
		this.isTaken = isTaken;
	}

}
