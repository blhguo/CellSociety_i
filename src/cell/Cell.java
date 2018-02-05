package cell;

import java.util.HashSet;
import javafx.scene.paint.Color;

public abstract class Cell {
	protected Color DISPLAYCOLOR;
	
	public abstract Cell nextState (HashSet<Cell> neighbors);
	
	public Color getDisplayColor() {
		return DISPLAYCOLOR;
	}
}