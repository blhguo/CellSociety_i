package cell.firesim;

import java.util.HashSet;

import cell.Cell;
import javafx.scene.paint.Color;

public class EmptyCell extends FireSimCell{

	public EmptyCell(double pCatch, double pLight, double pGrow) {
		super(pCatch, pLight, pGrow);
		this.DISPLAYCOLOR = Color.LIGHTGRAY;
	}
	
	
	@Override
	public Cell nextState(HashSet<Cell> neighbors) {
		if (Math.random() <= probGrow)
			return new TreeCell(probCatch, probLightning, probGrow);
		return this;
	}
	
	@Override
	public boolean isBurning() {
		return false;
	}
}
