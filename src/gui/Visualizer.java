package gui;

import java.io.File;

import cell.Cell;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import xml.readers.*;
import xml.SimSetups.*;

public class Visualizer {
	
	private static final int XPADDING = 10; //when creating cells, the x-margin between cell
	private static final int YPADDING = 10; //when creating cells, the y-margin between cell
	private static final int MENU_PAD = 10;
	private static String fileName;

	public Scene setupScene (int width, int height, Paint background, Cell[][] cellArray, int cell_width, int cell_height) throws Exception {
		Group root = new Group (CreateRoot(cellArray, cell_width, cell_height));
		Scene scene = new Scene(root, width, height, background);
		return scene;	
	}
	
	public Scene setupMenu (int width, int height, Paint background, Cell[][] cellArray, int cell_width, int cell_height, Stage stage) throws Exception {
    	VBox splash = new VBox ();
        splash.setPadding(new Insets(MENU_PAD, MENU_PAD, MENU_PAD, MENU_PAD));
        splash.setSpacing(MENU_PAD);
        Label lbl = new Label("Cell Society Simulation");
        lbl.setFont(Font.font("Amble CN", FontWeight.BOLD, 24));
        splash.getChildren().add(lbl);
        FileChooser fileChooser = new FileChooser();
        Button openButton = new Button("Open a File...");
        
        openButton.setOnAction(
        	new EventHandler<ActionEvent>() {
        		@Override
        		public void handle(final ActionEvent e) {
        			File file = fileChooser.showOpenDialog(stage);
        			if (file != null) {
        				fileName = file.getName();
        				fileName = "data/" + fileName;		
        				try {
        					Manager.callXMLreader(fileName);
							stage.setScene(setupScene (width, height, background, cellArray, cell_width, cell_height));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
        			}
        		}
        	});
        splash.getChildren().add(openButton);
        Scene scene = new Scene(splash);
        return scene;
	}
	
	public Group CreateRoot(Cell[][] cellArray, int width, int height) {//cell abstract class hasn't been created yet
		Group addition = new Group();
		for (int i = 0; i < cellArray[0].length; i++) {
			for (int j = 0; j < cellArray[1].length; j++) {
				addition.getChildren().add(GenerateCell(cellArray[i][j], width, height, i, j));
			}
		}
		return addition;
	}
	
	private Rectangle GenerateCell(Cell BufferCell, int width, int height, int i, int j) {
		Rectangle Image = new Rectangle((width * i + XPADDING), (height * j + YPADDING), width, height);
		Image.setFill(BufferCell.getDisplayColor());
		return Image;
	}
}