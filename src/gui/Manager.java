package gui;

import java.io.File;

import cell.Cell;
import grid.FireSimGrid;
import grid.GOLSimGrid;
import grid.Grid;
import grid.SegregationSimGrid;
import grid.WatorSimGrid;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import xml.SimSetups.FireSimSetup;
import xml.SimSetups.GOLSimSetup;
import xml.SimSetups.SegregationSimSetup;
import xml.SimSetups.WatorSimSetup;
import xml.readers.FireXMLreader;
import xml.readers.GOLXMLreader;
import xml.readers.SegregationXMLreader;
import xml.readers.WatorXMLreader;

public class Manager extends Application {

	int width = 100; //TODO
	int height = 100; //TODO
	Grid myGrid;
	//Grid CellArray; //TODO
	int cell_Width; //TODO
	int cell_Height; //TODO
    public static final Paint BACKGROUND = Color.WHITE;
    private Stage TheStage;
	private static final String TITLE = "CA SIMULATION";
	private String fileName = "";
	public double SECOND_DELAY = 1000.0;
	private KeyFrame frame;
	private Timeline animation;	
	private static final int XPADDING = 10; //when creating cells, the x-margin between cell
	private static final int YPADDING = 10; //when creating cells, the y-margin between cell
	private static final int MENU_PAD = 10;
	//Visualizer visualizer = new Visualizer();
	
	/*private Grid[] myPossibleSims = { 
	        new FireSimGrid(sim_width, sim_height, cellArray, probCatch, probLightning),
	        new GOLSimGrid(sim_width, sim_height, cellArray),
	        new SegregationSimGrid(sim_width, sim_height, cellArray, x_threshold, o_threshold),
	        new WatorSimGrid(sim_width, sim_height, cellArray, fish_threshold, shark_threshold)
	};
	*/
	
	@Override
	public void start(Stage stage) throws Exception {
		
		//add code to parse and create grid
		
		//CellArray = myPossibleSims[0];
		//cell_Width; //TODO
		//cell_Height; //TODO
		
		TheStage = stage;
		Scene myScene = setupMenu(width, height, BACKGROUND, TheStage);
		myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
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
        					callXMLreader(fileName);
							stage.setScene(setupScene(width, height, BACKGROUND, myGrid.getCellArray(), cell_Width, cell_Height));
						} catch (Exception e1) {
							e1.printStackTrace();
						}	
        			}
        		}
        	});
        
		TheStage.setScene(myScene);
		TheStage.setTitle(TITLE);
		TheStage.show();
		
        frame = new KeyFrame(Duration.millis(SECOND_DELAY), e1 -> step());
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
	}
	
	private void handleKeyInput(KeyCode code) {
		if (code == KeyCode.RIGHT) {
			step();
		}
		else if (code == KeyCode.UP) {
			//speed up
			SECOND_DELAY = SECOND_DELAY * 0.5;
		}
		else if (code == KeyCode.DOWN) {
			//slow down
			SECOND_DELAY = SECOND_DELAY * 2;
		}
		else if (code == KeyCode.P) {
			//play or pause
			if (animation.getStatus().equals("PAUSED")) {
				animation.play();
			}
			else if (animation.getStatus().equals("RUNNING")) {
				animation.pause();
			}
		}
	}
	
	private void step () {
		//TODO: call grid, call start
		Scene myScene_Buffer;
		try {
			myGrid.updateGrid();
			myScene_Buffer = setupScene(width, height, BACKGROUND, myGrid.getCellArray(), cell_Width, cell_Height);
			myScene_Buffer.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
			TheStage.setScene(myScene_Buffer);
			TheStage.show();
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void callXMLreader(String file){
		if(file.equals("data/segregation.xml")) {
		    SegregationXMLreader xml_reader = new SegregationXMLreader();
		    SegregationSimSetup simInfo = xml_reader.read(file);
		    myGrid = new SegregationSimGrid(simInfo.getGridX(), simInfo.getGridY(), simInfo.getArray(), simInfo.getThreshold());
		    simInfo.printInfo();
		    cell_Width = simInfo.getCellX();
		    cell_Height = simInfo.getCellY();
		}
		else if(file.equals("data/wator.xml")) {
		    WatorXMLreader xml_reader = new WatorXMLreader();
		    WatorSimSetup simInfo = xml_reader.read(file);
		    myGrid = new WatorSimGrid(simInfo.getGridX(), simInfo.getGridY(), simInfo.getArray(), simInfo.getReproduction());
		    simInfo.printInfo();
		    cell_Width = simInfo.getCellX();
		    cell_Height = simInfo.getCellY();
		}
		else if(file.equals("data/fire.xml")) {
		    FireXMLreader xml_reader = new FireXMLreader();
		    FireSimSetup simInfo = xml_reader.read(file);
		    myGrid = new FireSimGrid(simInfo.getGridX(), simInfo.getGridY(), simInfo.getArray(), simInfo.getFireProb(), simInfo.getLightningProb(), simInfo.getProbGrow());
		    simInfo.printInfo();
		    cell_Width = simInfo.getCellX();
		    cell_Height = simInfo.getCellY();
		}
		else {
		    GOLXMLreader xml_reader = new GOLXMLreader();
		    GOLSimSetup simInfo = xml_reader.read(file);
		    myGrid = new GOLSimGrid(simInfo.getGridX(), simInfo.getGridY(), simInfo.getArray());
		    simInfo.printInfo();
		    cell_Width = simInfo.getCellX();
		    cell_Height = simInfo.getCellY();
		}
	}

	public Scene setupScene (int width, int height, Paint background, Cell[][] cellArray, int cell_width, int cell_height) throws Exception {
		Group root = new Group (CreateRoot(cellArray, cell_width, cell_height));
		Scene scene = new Scene(root, width, height, background);
		return scene;	
	}
	
	public Scene setupMenu (int width, int height, Paint background, Stage stage) throws Exception {
    	VBox splash = new VBox ();
        splash.setPadding(new Insets(MENU_PAD, MENU_PAD, MENU_PAD, MENU_PAD));
        splash.setSpacing(MENU_PAD);
        Label lbl = new Label("Cell Society Simulation");
        lbl.setFont(Font.font("Amble CN", FontWeight.BOLD, 24));
        splash.getChildren().add(lbl);

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
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
