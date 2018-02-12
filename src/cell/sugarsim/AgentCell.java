package cell.sugarsim;

import java.util.List;

import cell.Cell;

/**
 * @author Yashas Manjunatha
 * Creates and describes behavior of an Agent Cell in the Sugar Simulation.
 * Extends SuagrSimCell class.
 */
public class AgentCell extends SugarSimCell{
	private int agent_sugar;
	private int sugarMetabolism;
	private int vision;

	/**
	 * Creates a new Agent Cell.
	 * @param patch_sugar amount of sugar in that patch the agent is on
	 * @param max_sugar max amount of sugar in that patch the agent is on
	 * @param sugarGBR sugar grow back rate
	 * @param sugarGBI sugar grow back interval
	 * @param tick time
	 * @param agent_s agent sugar amount
	 * @param sM sugar metabolism
	 * @param v vision
	 */
	public AgentCell(int patch_sugar, int max_sugar, int sugarGBR, int sugarGBI, int tick, int agent_s, int sM, int v) {
		super(patch_sugar, max_sugar, sugarGBR, sugarGBI, tick);
		this.agent_sugar = agent_s;
		this.sugarMetabolism = sM;
		this.vision = v;
	}
	
	/**
	 * @param v number of neighbors it can see
	 */
	public void setVision(int v) {
		this.vision = v;
	}
	
	/**
	 * @param sM sugar agent eats per turn
	 */
	public void setSugarMetabolism(int sM) {
		this.sugarMetabolism = sM;
	}
	
	/**
	 * @return number of neighbors it can see
	 */
	public int getVision() {
		return this.vision;
	}

	/**
	 * @return agent's amount of sugar
	 */
	public int getAgentSugar() {
		return this.agent_sugar;
	}
	
	/**
	 * @return sugar agent eats per turn
	 */
	public int getSugarMetabolism() {
		return this.sugarMetabolism;
	}
	
	/* (non-Javadoc)
	 * @see cell.Cell#nextState(java.util.List)
	 */
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
