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
		double ocells = 0;
		double total = 0;
		for (Cell cell:neighbors) {
			if (cell instanceof OCell) {
				ocells++;
				total++;
			}
			if (cell instanceof XCell)
				total++;
				
		}
		if (ocells/total < threshold) {
			return new EmptyCell();
		}
		
		return this;
	}
}
