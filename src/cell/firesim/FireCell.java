package cell.firesim;

import java.util.HashSet;

import cell.Cell;
import javafx.scene.paint.Color;

public class FireCell extends FireSimCell{

	public FireCell() {
		this.DISPLAYCOLOR = Color.ORANGE;
	}
	
	@Override
	public Cell nextState(HashSet<Cell> neighbors) {
		return new EmptyCell();
	}
	
	@Override
	public boolean isBurning() {
		return true;
	}
}
