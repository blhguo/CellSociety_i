package cell.sugarsim;

import java.util.List;

import cell.Cell;

public class AgentCell extends SugarSimCell{
	private int agent_sugar;
	private int sugarMetabolism;
	private int vision;

	public AgentCell(int patch_sugar, int max_sugar, int sugarGBR, int sugarGBI, int tick, int agent_s, int sM, int v) {
		super(patch_sugar, max_sugar, sugarGBR, sugarGBI, tick);
		this.agent_sugar = agent_s;
		this.sugarMetabolism = sM;
		this.vision = v;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cell nextState(List<Cell> neighbors) {
		agent_sugar -= sugarMetabolism;
		if (agent_sugar <= 0) {
			return new EmptyCell(this.patch_sugar, this.max_sugar, this.sugarGrowBackRate, this.sugarGrowBackInterval, this.tick);
		}
		
		// TODO Auto-generated method stub
		return null;
	}
}
