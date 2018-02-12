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
	}
	
	public void setVision(int v) {
		this.vision = v;
	}
	
	public void setSugarMetabolism(int sM) {
		this.sugarMetabolism = sM;
	}
	
	public int getVision() {
		return this.vision;
	}

	public int getAgentSugar() {
		return this.agent_sugar;
	}
	
	public int getSugarMetabolism() {
		return this.sugarMetabolism;
	}
	
	@Override
	public Cell nextState(List<Cell> neighbors) {
		patchGrowBack();
		
		agent_sugar -= sugarMetabolism;
		if (agent_sugar <= 0) {
			return new EmptyCell(this.patch_sugar, this.max_sugar, this.sugarGrowBackRate, this.sugarGrowBackInterval, this.tick);
		}
		return this;
	}
}
