package gui;

import java.io.File;

import cell.Cell;
import grid.FireSimGrid;
import grid.GOLSimGrid;
import grid.Grid;
import grid.SegregationSimGrid;
import grid.WatorSimGrid;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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

	int width = 500; //TODO
	int height = 500; //TODO
	Grid myGrid;
	int cell_Width; //TODO
	int cell_Height; //TODO
	public static final Paint BACKGROUND = Color.WHITE;
	private Stage TheStage;
	private static final String TITLE = "CA SIMULATION";
	private String DEFAULT_FILENAME = "data/game_of_life.xml";
	private String fileName = DEFAULT_FILENAME;
	public double SECOND_DELAY = 1000.0;
	private KeyFrame frame;
	private Timeline animation;	
	private static final int XPADDING = 10; //when creating cells, the x-margin between cell
	private static final int YPADDING = 27; //when creating cells, the y-margin between cell
	private static final int MENU_PAD = 10;
	private static final int GUIDE_SIZE = 300;
	private boolean inMenu = true;

	//Visualizer visualizer = new Visualizer();
	
	@Override
	public void start(Stage stage) throws Exception {
		
		//add code to parse and create grid
		
		TheStage = stage;
		Scene menuScene = setupMenu(width, height, BACKGROUND, TheStage);
		menuScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        /*FileChooser fileChooser = new FileChooser();
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
        	});*/
        
		TheStage.setScene(menuScene);
		TheStage.setTitle(TITLE);
		TheStage.show();
		
        frame = new KeyFrame(Duration.millis(SECOND_DELAY), e1 -> step());
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
	}
	
	private void returnMenu() throws Exception {
		fileName = DEFAULT_FILENAME;
		Scene menuScene = setupMenu(width, height, BACKGROUND, TheStage);
		menuScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		TheStage.setScene(menuScene);
		TheStage.setTitle(TITLE);
		TheStage.show();
	}

	
	private void handleKeyInput(KeyCode code) {
		if (code == KeyCode.RIGHT) {
			step();
		}
		else if (code == KeyCode.UP) {
			//speed up
			//SECOND_DELAY = SECOND_DELAY * 0.5;
			animation.setRate(2);
		}
		else if (code == KeyCode.DOWN) {
			//slow down
			//SECOND_DELAY = SECOND_DELAY * 2;
			animation.setRate(0.5);
		}
		else if (code == KeyCode.P) {
			//play or pause
			if (animation.getStatus() == Status.PAUSED) {
				animation.play();
			}
			else if (animation.getStatus() == Status.RUNNING) {
				animation.pause();
			}
		}
		else if (code == KeyCode.M) {
			// return menu
			try {
				inMenu = true;
				returnMenu();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	
	private void step () {
		//TODO: call grid, call start
		if (!inMenu) {
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
	}
	
	public void callXMLreader(String file){
		if(file.equals("data/segregation.xml")) {
		    SegregationXMLreader xml_reader = new SegregationXMLreader();
		    SegregationSimSetup simInfo = xml_reader.read(file);
		    //simInfo.printInfo();
			width = simInfo.getGridX() + 20;
			height = simInfo.getGridY() + 20;
		    int simWidth = simInfo.getGridX()/simInfo.getCellX();
		    int simHeight = simInfo.getGridY()/simInfo.getCellY();
		    myGrid = new SegregationSimGrid(simWidth, simHeight, simInfo.getArray(), simInfo.getThreshold());
		    cell_Width = simInfo.getCellX();
		    cell_Height = simInfo.getCellY();
		}
		else if(file.equals("data/wator.xml")) {
		    WatorXMLreader xml_reader = new WatorXMLreader();
		    WatorSimSetup simInfo = xml_reader.read(file);
		    //simInfo.printInfo();
			width = simInfo.getGridX() + 20;
			height = simInfo.getGridY() + 20;
		    int simWidth = simInfo.getGridX()/simInfo.getCellX();
		    int simHeight = simInfo.getGridY()/simInfo.getCellY();
		    myGrid = new WatorSimGrid(simWidth, simHeight, simInfo.getArray(), simInfo.getReproduction());
		    cell_Width = simInfo.getCellX();
		    cell_Height = simInfo.getCellY();
		}
		else if(file.equals("data/fire.xml")) {
		    FireXMLreader xml_reader = new FireXMLreader();
		    FireSimSetup simInfo = xml_reader.read(file);
		    //simInfo.printInfo();
			width = simInfo.getGridX() + 20;
			height = simInfo.getGridY() + 20;
		    int simWidth = simInfo.getGridX()/simInfo.getCellX();
		    int simHeight = simInfo.getGridY()/simInfo.getCellY();
		    myGrid = new FireSimGrid(simWidth, simHeight, simInfo.getArray(), simInfo.getFireProb(), simInfo.getLightningProb(), 1);//simInfo.getProbGrow());
		    cell_Width = simInfo.getCellX();
		    cell_Height = simInfo.getCellY();
		}
		else {
		    GOLXMLreader xml_reader = new GOLXMLreader();
		    GOLSimSetup simInfo = xml_reader.read(file);
		    //simInfo.printInfo();
			width = simInfo.getGridX() + 20;
			height = simInfo.getGridY() + 20;
			int simWidth = simInfo.getGridX()/simInfo.getCellX();
		    int simHeight = simInfo.getGridY()/simInfo.getCellY();
		    myGrid = new GOLSimGrid(simWidth, simHeight, simInfo.getArray());
		    cell_Width = simInfo.getCellX();
		    cell_Height = simInfo.getCellY();
		}
	}
	
	private Scene setupGuide(int width, int height, Paint background) {
		//String label = myResources.getString(property); ---> property itself is a string
		// set all text for guide
		Text g1 = new Text();
		g1.setText("Welcome to Group 18 Simulation Project!");
		Text g2 = new Text();
		g2.setText("Press any key to return to menu.");
		Text line = new Text();
		line.setText("----------------------------------------------------");
		Text line2 = new Text();
		line2.setText("----------------------------------------------------");
		Text line3 = new Text();
		line3.setText("----------------------------------------------------");
		Text g3 = new Text();
		g3.setText("Keys:");
		Text g4 = new Text();
		g4.setText("P - pauses the simulation");
		Text g5 = new Text();
		g5.setText("Arrow up - increases animation rate");
		Text g6 = new Text();
		g6.setText("Arrow down - decreases animation rate");
		Text g7 = new Text();
		g7.setText("Arrow right - step forward through simulation");
		Text g8 = new Text();
		g8.setText("M - take you to menu");
		Text g15 = new Text();
		g15.setText("About:");
		Text g155 = new Text();
		g155.setText("Game of Life Simulation by John Horton Conway");
		Text g16 = new Text();
		g16.setText("Segregation Simulation by Thomas Schelling");
		Text g17 = new Text();
		g17.setText("Wator World Simulation by A.K. Dewdney");
		Text g18 = new Text();
		g18.setText("Fire Simulation by Angela B. Shiflet");

		// initialize GridPane and settings
		GridPane gridPane = new GridPane();
		gridPane.setMinSize(width, height);
		gridPane.setPadding(new Insets(10,10,10,10));
		gridPane.setVgap(5);
		gridPane.setHgap(5);
		gridPane.setStyle("-fx-background-color: THISTLE;");
		//gridPane.setAlignment(Pos.CENTER);

		// add text to GridPane
		gridPane.add(g1, 0, 0);
		gridPane.add(g2, 0, 1);
		gridPane.add(line, 0, 2);
		gridPane.add(g3, 0, 3);
		gridPane.add(g4, 0, 4);
		gridPane.add(g5, 0, 5);
		gridPane.add(g6, 0, 6);
		gridPane.add(g7, 0, 7);
		gridPane.add(g8, 0, 8);
		gridPane.add(line2, 0, 9);
		gridPane.add(g15, 0, 10);
		gridPane.add(g155, 0, 11);
		gridPane.add(g16, 0, 12);
		gridPane.add(g17, 0, 13);
		gridPane.add(g18, 0, 14);	

		// create a place to see the shapes
		Scene scene = new Scene(gridPane, width, height, background);

		// set what happens on key press
		scene.setOnKeyPressed(e -> {
			// return to menu scene
			try {
				returnMenu();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		return scene;
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
        
        FileChooser fileChooser = new FileChooser();
        Button openButton = new Button("Open a File...");
        
		Button startButton = GenerateStartButton(stage);
		ChoiceBox<String> fileChoiceBox = GenerateChoiceBox();
		Button guideButton = GenerateGuideButton(stage);

		splash.getChildren().add(startButton);
		splash.getChildren().add(fileChoiceBox);
		splash.getChildren().add(guideButton);

        
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
        						inMenu = false;
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
	
	public Button GenerateStartButton(Stage s) {
		Button startButton = new Button("Start Selected Simulation");

		startButton.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent e) {	
						try {
							callXMLreader(fileName);
							s.setScene(setupScene(width, height, BACKGROUND, myGrid.getCellArray(), cell_Width, cell_Height));
							inMenu = false;
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}

				});
		return startButton;
	}
	
	public ChoiceBox GenerateChoiceBox() {
		ChoiceBox<String> fileChoiceBox = new ChoiceBox<String>();
		fileChoiceBox.getItems().add("Game of Life");
		fileChoiceBox.getItems().add("Segregation");
		fileChoiceBox.getItems().add("Wator World");
		fileChoiceBox.getItems().add("Fire");
		fileChoiceBox.getSelectionModel().selectFirst();
		fileChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value) {	
				if((int) new_value == 0){
					fileName = "data/game_of_life.xml";
				}
				if((int) new_value == 1){
					fileName = "data/segregation.xml";
				}
				if((int) new_value == 2){
					fileName = "data/wator.xml";
				}
				if((int) new_value == 3){
					fileName = "data/fire.xml";
				}
			}
		});
		return fileChoiceBox;

	}

	public Button GenerateGuideButton(Stage s) {
		Button guideButton = new Button("Guide");

		guideButton.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent e) {	
						try {
							s.setScene(setupGuide(GUIDE_SIZE, GUIDE_SIZE + 40, BACKGROUND));
							inMenu = true;
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}

				});
		return guideButton;
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
