package cell.segregation;

import java.util.HashSet;

import cell.Cell;
import javafx.scene.paint.Color;

public class OCell extends SegregationSimCell{
	private double threshold;
	
	public OCell(double t) {
		threshold = t;
		this.DISPLAYCOLOR = Color.ORANGE;
	}

	@Override
	public Cell nextState(HashSet<Cell> neighbors) {
		int ocells = 0;
		for (Cell cell:neighbors)
			if (cell instanceof OCell)
				ocells++;
		if (ocells/neighbors.size() < threshold) {
			return new EmptyCell();
		}
		
		return this;
	}
}
