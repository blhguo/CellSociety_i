package cell.segregation;

import java.util.HashSet;

import cell.Cell;
import javafx.scene.paint.Color;

public class EmptyCell extends SegregationSimCell{

	public EmptyCell() {
		this.DISPLAYCOLOR = Color.GREY;
	}
	
	@Override
	public Cell nextState(HashSet<Cell> neighbors) {
		return this;
	}

}
