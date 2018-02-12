package cell.sugarsim;

import cell.Cell;
import javafx.scene.paint.Color;

public abstract class SugarSimCell extends Cell{
	protected int patch_sugar;
	protected int max_sugar;
	protected int sugarGrowBackRate;
	protected int sugarGrowBackInterval;
	protected int tick;
	
	public SugarSimCell(int patch_sugar, int max_sugar, int sugarGBR, int sugarGBI, int tick) {
		this.patch_sugar = patch_sugar;
		this.max_sugar = max_sugar;
		this.sugarGrowBackRate = sugarGBR;
		this.sugarGrowBackInterval = sugarGBI;
		this.tick = tick;
		setDisplayColor();
	}
	
	public void setSugarGBR(int gbr) {
		this.sugarGrowBackRate = gbr;
	}
	
	public void setSugarGRI(int gbi) {
		this.sugarGrowBackInterval = gbi;
	}
	
	public int getPatchSugar() {
		return this.patch_sugar;
	}
	
	public int getMaxSugar() {
		return this.max_sugar;
	}
	
	public int getTick() {
		return this.tick;
	}
	
	public int getSugarGBR() {
		return this.sugarGrowBackRate;
	}
	
	public int getSugarGBI() {
		return this.sugarGrowBackInterval;
	}
	
	protected void patchGrowBack() {
		if (tick%sugarGrowBackInterval == 0) {
			patch_sugar += sugarGrowBackRate;
			if (patch_sugar > max_sugar) {
				patch_sugar = max_sugar;
			}
		}
		setDisplayColor();
	}
	
	/* (non-Javadoc)
	 * @see cell.Cell#setDisplayColor()
	 */
	@Override
	protected void setDisplayColor() {
		this.display_color = new Color(1.0,0.0,0.0,((double)patch_sugar)/4);
	}
}
