package gui;

import grid.Grid;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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

	int width = 10;
	int height = 10;
	Grid CellArray; //TODO
	int cell_Width; //TODO
	int cell_Height; //TODO
    public static final Paint BACKGROUND = Color.WHITE;
    private Stage TheStage;
	private static final String TITLE = "CA SIMULATION";
	private String fileName = "";
	public double SECOND_DELAY = 1000.0;
	private KeyFrame frame;
	private Timeline animation;
	Visualizer visualizer = new Visualizer();
	
	private Grid[] myPossibleSims = { 
	        //new FireSimGrid(sim_width, sim_height, cellArray, probCatch, probLightning),
	        //new GOLSimGrid(sim_width, sim_height, cellArray),
	        //new SegregationSimGrid(sim_width, sim_height, cellArray, x_threshold, o_threshold),
	        //new WatorSimGrid(sim_width, sim_height, cellArray, fish_threshold, shark_threshold)
	};
	
	
	@Override
	public void start(Stage stage) throws Exception {
		
		//add code to parse and create grid
		
		
		CellArray = myPossibleSims[0];
		//cell_Width; //TODO
		//cell_Height; //TODO
		
		TheStage = stage;
		Scene myScene = visualizer.setupMenu(width, height, BACKGROUND, CellArray.getCellArray(), cell_Width, cell_Height, TheStage);
		myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
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
			myScene_Buffer = visualizer.setupScene(width, height, BACKGROUND, CellArray.getCellArray(), cell_Width, cell_Height);
			myScene_Buffer.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
			TheStage.setScene(myScene_Buffer);
			TheStage.show();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void callXMLreader(String file){
		if(file.equals("data/segregation.xml")) {
		    SegregationXMLreader xml_reader = new SegregationXMLreader();
		    SegregationSimSetup simInfo = xml_reader.read(file);
		    simInfo.printInfo();
		}
		else if(file.equals("data/wator.xml")) {
		    WatorXMLreader xml_reader = new WatorXMLreader();
		    WatorSimSetup simInfo = xml_reader.read(file);
		    simInfo.printInfo();
		}
		else if(file.equals("data/fire.xml")) {
		    FireXMLreader xml_reader = new FireXMLreader();
		    FireSimSetup simInfo = xml_reader.read(file);
		    simInfo.printInfo();
		}
		else {
		    GOLXMLreader xml_reader = new GOLXMLreader();
		    GOLSimSetup simInfo = xml_reader.read(file);
		    simInfo.printInfo();
		}
	}
	

}
