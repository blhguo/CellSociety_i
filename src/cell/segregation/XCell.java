package cell.segregation;

import java.util.HashSet;

import cell.Cell;
import javafx.scene.paint.Color;

public class XCell extends SegregationSimCell{
	private double threshold;
	
	public XCell(double t) {
		threshold = t;
		this.DISPLAYCOLOR = Color.PINK;
	}

	@Override
	public Cell nextState(HashSet<Cell> neighbors) {
		double xcells = 0;
		double total = 0;
		for (Cell cell:neighbors) {
			if (cell instanceof XCell) {
				xcells++;
				total++;
			}
			if (cell instanceof OCell)
				total++;
					
		}
		if (xcells/total < threshold) {
			return new EmptyCell();
		}
		
		return this;
	}
}
