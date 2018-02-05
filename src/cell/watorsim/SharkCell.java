package cell.watorsim;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import cell.Cell;
import javafx.scene.paint.Color;

public class SharkCell extends WatorSimCell{
	private int myTurns;
	private int turn_threshold;
	
	public SharkCell(int threshold) {
		myTurns = 0;
		turn_threshold = threshold;
		this.DISPLAYCOLOR = Color.BLUE;
	}

	@Override
	public Cell nextState(HashSet<Cell> neighbors) {
		List<FishCell> fish_neighbors = new ArrayList<FishCell>();
		for (Cell cell:neighbors)
			if (cell instanceof FishCell)
				fish_neighbors.add((FishCell) cell);
		
		if (fish_neighbors.size() != 0) {
			fish_neighbors.get((int) (Math.random() * fish_neighbors.size())).setEaten(this);
			return new EmptyCell();
		}
		
		List<EmptyCell> empty_neighbors = new ArrayList<EmptyCell>();
		for (Cell cell:neighbors)
			if (cell instanceof EmptyCell)
				empty_neighbors.add((EmptyCell) cell);
		
		this.myTurns++;
		if (this.myTurns >= turn_threshold && empty_neighbors.size() != 0) {
			int random_number = (int) Math.random() * empty_neighbors.size();
			empty_neighbors.get(random_number).setMoved(this);
			empty_neighbors.remove(random_number);
			return new SharkCell(turn_threshold);
		}
		
		if (empty_neighbors.size() != 0) {
			int random_number = (int) Math.random() * empty_neighbors.size();
			empty_neighbors.get(random_number).setMoved(this);
			return new EmptyCell();
		}
		
		return this;
	}

}
