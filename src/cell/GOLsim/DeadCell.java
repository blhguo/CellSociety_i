package cell.GOLsim;


import java.util.HashSet;
import cell.Cell;
import javafx.scene.paint.Color;

public class DeadCell extends GOLSimCell{
	
	public DeadCell() {
		this.DISPLAYCOLOR = Color.GRAY;
	}

	@Override
	public Cell nextState(HashSet<Cell> neighbors) {
		int neighbors_alive = 0;
		for(Cell cell:neighbors) {
			if (cell instanceof AliveCell)
				neighbors_alive++;
		}
		
		if (neighbors_alive == 3)
			return new AliveCell();
		return this;
	}
}
