package cell.firesim;

import java.util.HashSet;

import cell.Cell;
import javafx.scene.paint.Color;

public class TreeCell extends FireSimCell{
	private double probCatch = 0.5;
	private double probLightning = 0;
	
	public TreeCell() {
		this.DISPLAYCOLOR = Color.BURLYWOOD;
	}
	
	public void setProbCatch (double prob) {
		probCatch = prob;
	}
	
	public void setProbLightning (double prob) {
		probLightning = prob;
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
			return new FireCell();
		return this;
	}
	
	@Override
	public boolean isBurning() {
		return false;
	}
}
