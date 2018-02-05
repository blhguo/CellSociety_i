package cell.firesim;

import java.util.HashSet;

import cell.Cell;
import javafx.scene.paint.Color;

public class TreeCell extends FireSimCell{
	
	public TreeCell(double pCatch, double pLight, double pGrow) {
		super(pCatch, pLight, pGrow);
		this.DISPLAYCOLOR = Color.BURLYWOOD;
	}

	@Override
	public Cell nextState(HashSet<Cell> neighbors) {
		boolean neighborBurning = false;
		for(Cell cell:neighbors) {
			if(cell instanceof FireSimCell)
				if(((FireSimCell) cell).isBurning())
					neighborBurning = true;
		}
		double prob = probCatch;
		if (Math.random() <= probLightning)
			prob *= probLightning;
		if (neighborBurning && Math.random() <= prob)
			return new FireCell(probCatch, probLightning, probGrow);
		return this;
	}
	
	@Override
	public boolean isBurning() {
		return false;
	}
}
