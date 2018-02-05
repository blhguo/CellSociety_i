package cell.firesim;

import java.util.HashSet;

import cell.Cell;
import javafx.scene.paint.Color;

public class FireCell extends FireSimCell{

	public FireCell(double pCatch, double pLight, double pGrow) {
		super(pCatch, pLight, pGrow);
		this.DISPLAYCOLOR = Color.DARKORANGE;
	}
	
	@Override
	public Cell nextState(HashSet<Cell> neighbors) {
		return new EmptyCell(probCatch, probLightning, probGrow);
	}
	
	@Override
	public boolean isBurning() {
		return true;
	}
}
