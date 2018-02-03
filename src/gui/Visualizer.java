package gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Visualizer {
	
	public static final int XPADDING = 10; //when creating cells, the x-margin between cell
	public static final int YPADDING = 10; //when creating cells, the y-margin between cell

	static Scene setupScene (int width, int height, Paint background, Cell[][] cellArray, int cell_width, int cell_height) throws Exception {
		Group root = new Group (CreateRoot(cellArray, cell_width, cell_height));
		Scene scene = new Scene(root, width, height, background);
		return scene;	
	}
	
	private Group CreateRoot(Cell[][] cellArray, int width, int height) {//cell abstract class hasn't been created yet
		Group addition = new Group();
		for (int i = 0; i < cellArray[0].length; i++) {
			for (int j = 0; j < cellArray[1].lengt; j++) {
				addition.getChildren().add(GenerateCell(cellArray[i][j], width, height, i, j));
			}
		}
		return addition;
	}
	
	private Rectangle GenerateCell(Cell BufferCell, int width, int height, int i, int j) {
		Rectangle Image = new Rectangle((width * i + XPADDING), (height * j + YPADDING), width, height);
		Image.setFill(BufferCell.getDisplayColor);
	}
}