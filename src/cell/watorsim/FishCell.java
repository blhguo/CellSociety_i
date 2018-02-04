package cell.watorsim;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import cell.Cell;
import javafx.scene.paint.Color;

public class FishCell extends WatorSimCell{
	private boolean isEaten;
	private int myTurns;
	private int turn_threshold;
	
	public FishCell(int threshold) {
		isEaten = false;
		myTurns = 0;
		turn_threshold = threshold;
		this.DISPLAYCOLOR = Color.YELLOW;
	}

	@Override
	public Cell nextState(HashSet<Cell> neighbors) {
		if(this.isEaten)
			return new EmptyCell();
		
		List<EmptyCell> empty_neighbors = new ArrayList<EmptyCell>();
		for (Cell cell:neighbors)
			if (cell instanceof EmptyCell)
				empty_neighbors.add((EmptyCell) cell);
		
		this.myTurns++;
		if (this.myTurns >= turn_threshold && empty_neighbors.size() != 0) {
			int random_number = (int) Math.random() * empty_neighbors.size();
			empty_neighbors.get(random_number).setMoved(new FishCell(turn_threshold));
			empty_neighbors.remove(random_number);
		}
		
		if (empty_neighbors.size() != 0) {
			empty_neighbors.get((int) (Math.random() * empty_neighbors.size())).setMoved(this);
			return new EmptyCell();
		}
		
		return this;
	}

	public boolean isEaten() {
		return isEaten;
	}

	public void setEaten(boolean isEaten) {
		this.isEaten = isEaten;
	}

}
