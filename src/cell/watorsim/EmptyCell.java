package cell.watorsim;

import java.util.HashSet;
import cell.Cell;
import javafx.scene.paint.Color;

public class EmptyCell extends WatorSimCell{
	WatorSimCell nextcell;
	boolean moved = false;
	
	public EmptyCell() {
		moved = false;
		this.DISPLAYCOLOR = Color.WHITE;
	}
	
	@Override
	public Cell nextState(HashSet<Cell> neighbors) {
		if (moved)
			return nextcell;
		return this;
	}
	
	public void setMoved (WatorSimCell cell) {
		nextcell = cell;
		moved = true;
	}

}
