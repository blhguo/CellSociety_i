package cell.sugarsim;

import java.util.List;

import cell.Cell;

public class EmptyCell extends SugarSimCell{

	public EmptyCell(int patch_sugar, int max_sugar, int sugarGBR, int sugarGBI, int tick) {
		super(patch_sugar, max_sugar, sugarGBR, sugarGBI, tick);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cell nextState(List<Cell> neighbors) {
		patchGrowBack();
		return this;
	}
}