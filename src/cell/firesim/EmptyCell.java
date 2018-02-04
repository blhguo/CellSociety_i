package cell.firesim;

import java.util.HashSet;

import cell.Cell;
import javafx.scene.paint.Color;

public class EmptyCell extends FireSimCell{

	public EmptyCell() {
		this.DISPLAYCOLOR = Color.WHITE;
	}
	
	@Override
	public Cell nextState(HashSet<Cell> neighbors) {
		return this;
	}
	
	@Override
	public boolean isBurning() {
		return false;
	}
}
