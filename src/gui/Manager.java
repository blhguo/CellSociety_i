package gui;

import java.io.File;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
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

/**
 * Simulation visualization and management
 * @author Brandon Guo, Marcus Olerte, Yashas Manjunatha
 * 
 * input:
 * @param No input parameters, all inputs are given by user
 * @return No return object, presents simulation
 * 
 * Use: 
 * Graphically presents simulation
 * Allows for User selected files
 * Double speed simulation, half speed simulation
 * Call next cell layout
 */

public class Manager extends Application {

	int width;
	int height;
	Grid myGrid;
	int cell_Width;
	int cell_Height;
	public static final Paint BACKGROUND = Color.WHITE;
	private Stage TheStage;
	private static final String TITLE = "CA SIMULATION";
	private String DEFAULT_FILENAME = "data/game_of_life.xml";
	private String fileName = DEFAULT_FILENAME;
	private int fileType = 0;
	public double SECOND_DELAY = 1000.0;
	private KeyFrame frame;
	private Timeline animation;	
	private static final int XPADDING = 10;
	private static final int YPADDING = 10;
	private static final int MENU_PAD = 10;
	private static final int GUIDE_SIZE = 310;
	private boolean inMenu = true;
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	public static final String DEFAULT_RESOURCE_FILE = "defaultText";
	private ResourceBundle myResources;
	private static final String FileS = "FileS";
	private static final String FileW = "FileW";
	private static final String FileF = "FileF";
	private static final String FileGOL = "FileGOL";

	@Override
	public void start(Stage stage) throws Exception {

		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_RESOURCE_FILE);

		TheStage = stage;
		Scene menuScene = setupMenu(width, height, BACKGROUND, TheStage);
		menuScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		TheStage.setScene(menuScene);
		TheStage.setTitle(TITLE);
		TheStage.show();

		frame = new KeyFrame(Duration.millis(SECOND_DELAY), e1 -> step());
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	// Takes in no parameters, but when called returns user to the menu
	// Has no return value
	private void returnMenu() throws Exception {
		inMenu = true;
		fileType = 0;
		animation.setRate(1);
		fileName = DEFAULT_FILENAME;
		Scene menuScene = setupMenu(width, height, BACKGROUND, TheStage);
		menuScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		TheStage.setScene(menuScene);
		TheStage.setTitle(TITLE);
		TheStage.show();
	}

	// Handles key input, takes in a keycode and matches it to one of the following, then acts on the simulation. No return value
	private void handleKeyInput(KeyCode code) {
		if (code == KeyCode.S) {
			step();
		}
		if (code == KeyCode.UP) {
			animation.setRate(2);
		}
		else if (code == KeyCode.DOWN) {
			animation.setRate(0.5);
		}
		else if (code == KeyCode.P) {
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
				returnMenu();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	// Computes next "scene", takes in no parameters and returns nothing
	private void step () {
		if (!inMenu) {
			Scene myScene_Buffer;
			try {
				myGrid.updateGrid();
				myScene_Buffer = setupScene(width, height, BACKGROUND, myGrid.getCellArray(), cell_Width, cell_Height);
				myScene_Buffer.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
				TheStage.setScene(myScene_Buffer);
				TheStage.show();

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	// Calls XML parser and stores the values from the parser
	public void callXMLreader(String file){
		if(file.equals(myResources.getString(FileS))) {
			callSegXMLreader(file);
		}
		else if(file.equals(myResources.getString(FileW))) {
			callWatorXMLreader(file);
		}
		else if(file.equals(myResources.getString(FileF))) {
			callFireXMLreader(file);
		}
		else {
			if(fileType == 0) {
				callGOLXMLreader(file);
			}
			else if(fileType == 1) {
				callSegXMLreader(file);
			}
			else if(fileType == 2) {
				callWatorXMLreader(file);
			}
			else if(fileType == 3) {
				callFireXMLreader(file);
			}			
		}
	}

	public void callGOLXMLreader(String file){
		GOLXMLreader xml_reader = new GOLXMLreader();
		GOLSimSetup simInfo = xml_reader.read(file);
		width = simInfo.getGridX() + 20;
		height = simInfo.getGridY() + 20;
		int simWidth = simInfo.getGridX()/simInfo.getCellX();
		int simHeight = simInfo.getGridY()/simInfo.getCellY();
		myGrid = new GOLSimGrid(simWidth, simHeight, simInfo.getArray());
		cell_Width = simInfo.getCellX();
		cell_Height = simInfo.getCellY();
	}

	public void callSegXMLreader(String file){
		SegregationXMLreader xml_reader = new SegregationXMLreader();
		SegregationSimSetup simInfo = xml_reader.read(file);
		width = simInfo.getGridX() + 20;
		height = simInfo.getGridY() + 20;
		int simWidth = simInfo.getGridX()/simInfo.getCellX();
		int simHeight = simInfo.getGridY()/simInfo.getCellY();
		myGrid = new SegregationSimGrid(simWidth, simHeight, simInfo.getArray(), simInfo.getThreshold());
		cell_Width = simInfo.getCellX();
		cell_Height = simInfo.getCellY();
	}

	public void callWatorXMLreader(String file){
		WatorXMLreader xml_reader = new WatorXMLreader();
		WatorSimSetup simInfo = xml_reader.read(file);
		width = simInfo.getGridX() + 20;
		height = simInfo.getGridY() + 20;
		int simWidth = simInfo.getGridX()/simInfo.getCellX();
		int simHeight = simInfo.getGridY()/simInfo.getCellY();
		myGrid = new WatorSimGrid(simWidth, simHeight, simInfo.getArray(), simInfo.getReproduction(), simInfo.getEnergy(), simInfo.getGainedEnergy());
		cell_Width = simInfo.getCellX();
		cell_Height = simInfo.getCellY();
	}

	public void callFireXMLreader(String file){
		FireXMLreader xml_reader = new FireXMLreader();
		FireSimSetup simInfo = xml_reader.read(file);
		width = simInfo.getGridX() + 20;
		height = simInfo.getGridY() + 20;
		int simWidth = simInfo.getGridX()/simInfo.getCellX();
		int simHeight = simInfo.getGridY()/simInfo.getCellY();
		myGrid = new FireSimGrid(simWidth, simHeight, simInfo.getArray(), simInfo.getFireProb(), simInfo.getLightningProb(), simInfo.getProbGrow());
		cell_Width = simInfo.getCellX();
		cell_Height = simInfo.getCellY();
	}

	// Sets up scene for tutorial
	private Scene setupGuide(int width, int height, Paint background) {
		// set all text for guide
		Text g1 = new Text();
		g1.setText(myResources.getString("Guide1"));
		Text g2 = new Text();
		g2.setText(myResources.getString("Guide2"));
		Text line = new Text();
		line.setText(myResources.getString("Line"));
		Text line2 = new Text();
		line2.setText(myResources.getString("Line"));
		Text g3 = new Text();
		g3.setText(myResources.getString("Guide3"));
		Text g4 = new Text();
		g4.setText(myResources.getString("Guide4"));
		Text g5 = new Text();
		g5.setText(myResources.getString("Guide5"));
		Text g6 = new Text();
		g6.setText(myResources.getString("Guide6"));
		Text g7 = new Text();
		g7.setText(myResources.getString("Guide7"));
		Text g8 = new Text();
		g8.setText(myResources.getString("Guide8"));
		Text g15 = new Text();
		g15.setText(myResources.getString("Guide9"));
		Text g155 = new Text();
		g155.setText(myResources.getString("Guide10"));
		Text g16 = new Text();
		g16.setText(myResources.getString("Guide11"));
		Text g17 = new Text();
		g17.setText(myResources.getString("Guide12"));
		Text g18 = new Text();
		g18.setText(myResources.getString("Guide13"));

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
				e1.printStackTrace();
			}
		});
		return scene;
	}

	// Sets up scene for the actual simulation
	public Scene setupScene (int width, int height, Paint background, Cell[][] cellArray, int cell_width, int cell_height) throws Exception {
		Group root = new Group (CreateRoot(cellArray, cell_width, cell_height));
		Scene scene = new Scene(root, width, height, background);
		return scene;	
	}

	// Sets up Splash screen/menu
	public Scene setupMenu (int width, int height, Paint background, Stage stage) throws Exception {
		VBox splash = new VBox ();
		splash.setPadding(new Insets(MENU_PAD, MENU_PAD, MENU_PAD, MENU_PAD));
		splash.setSpacing(MENU_PAD);
		Label lbl = new Label(myResources.getString("Title"));
		lbl.setFont(Font.font("Amble CN", FontWeight.BOLD, 24));
		splash.getChildren().add(lbl);

		Button openButton = GenerateFileButton(stage);
		Button startButton = GenerateStartButton(stage);
		ChoiceBox<String> fileChoiceBox = GenerateChoiceBox();
		Button guideButton = GenerateGuideButton(stage);

		splash.getChildren().add(startButton);
		splash.getChildren().add(fileChoiceBox);
		splash.getChildren().add(guideButton);
		splash.getChildren().add(openButton);

		Scene scene = new Scene(splash);
		return scene;
	}

	// Generates button to click to choose a file
	public Button GenerateFileButton(Stage s) {
		Button openButton = new Button(myResources.getString("OpenFile"));
		FileChooser fileChooser = new FileChooser();
		openButton.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent e) {
						File file = fileChooser.showOpenDialog(s);
						if (file != null) {
							fileName = file.getName();
							fileName = "data/" + fileName;		
							try {
								animation.play();
								callXMLreader(fileName);
								s.setScene(setupScene(width, height, BACKGROUND, myGrid.getCellArray(), cell_Width, cell_Height));
								inMenu = false;
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					}
				});
		return openButton;
	}
	// Generates button to start the selected simulation
	public Button GenerateStartButton(Stage s) {
		Button startButton = new Button(myResources.getString("StartCommand"));

		startButton.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent e) {	
						try {
							animation.play();
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
	// Drop down menu to select type of simulation
	public ChoiceBox GenerateChoiceBox() {
		ChoiceBox<String> fileChoiceBox = new ChoiceBox<String>();
		fileChoiceBox.getItems().add(myResources.getString("DropDown1"));
		fileChoiceBox.getItems().add(myResources.getString("DropDown2"));
		fileChoiceBox.getItems().add(myResources.getString("DropDown3"));
		fileChoiceBox.getItems().add(myResources.getString("DropDown4"));
		fileChoiceBox.getSelectionModel().selectFirst();
		fileChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value) {	
				if((int) new_value == 0){
					fileName = myResources.getString(FileGOL);
					fileType = 0;
				}
				if((int) new_value == 1){
					fileName = myResources.getString(FileS);
					fileType = 1;
				}
				if((int) new_value == 2){
					fileName = myResources.getString(FileW);
					fileType = 2;
				}
				if((int) new_value == 3){
					fileName = myResources.getString(FileF);
					fileType = 3;
				}
			}
		});
		return fileChoiceBox;
	}
	// Generates button that opens the user manual
	public Button GenerateGuideButton(Stage s) {
		Button guideButton = new Button(myResources.getString("Guide"));

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

	// Helper function of setupScene, returns a root with everything needed
	public Group CreateRoot(Cell[][] cellArray, int width, int height) {
		Group addition = new Group();
		for (int i = 0; i < cellArray[0].length; i++) {
			for (int j = 0; j < cellArray[1].length; j++) {
				/* TODO: Add switch case here to tell which type of cell to generate */	
				addition.getChildren().add(GenerateRectangularCell(cellArray[i][j], width, height, i, j));
			}
		}
		return addition;
	}
	//Helper function for CreateRoot, generates one cell
	private Rectangle GenerateRectangularCell(Cell BufferCell, int width, int height, int i, int j) {
		Rectangle Image = new Rectangle((width * i + XPADDING), (height * j + YPADDING), width, height);
		Image.setFill(BufferCell.getDisplayColor());
		Image.setStrokeWidth(0.3);
		Image.setStroke(Color.BLACK);
		return Image;
	}
	
	private Polygon GenerateHexagonCell(Cell BufferCell, int width, int height, int i, int j) {
		Polygon Image = new Polygon();
		Double[] points;
		if ((i % 2 )== 0) {
			points = new Double[] {
					40.0 * j + 10, 10.0 * i, 
					40.0 * j + 20, 10.0 * i, 
					40.0 * j + 30, 10.0 * i + 10,
					40.0 * j + 20, 10.0 * i + 20,
					40.0 * j + 10, 10.0 * i + 20,
					40.0 * j, 10.0 * i + 10
					};
		}
		else {
			points = new Double[] {
					40.0 * j + 30, 10.0 * i, 
					40.0 * j + 40, 10.0 * i, 
					40.0 * j + 50, 10.0 * i + 10,
					40.0 * j + 40, 10.0 * i + 20,
					40.0 * j + 30, 10.0 * i + 20,
					40.0 * j + 20, 10.0 * i + 10
					};
			}
		Image.getPoints().addAll(points);
		Image.setFill(BufferCell.getDisplayColor());
		Image.setStrokeWidth(0.3);
		Image.setStroke(Color.BLACK);
		return Image;
	}
	
	private Polygon GenerateTriangleCell(Cell BufferCell, int width, int height, int i, int j) {
		Polygon Image = new Polygon();
		Double[] points;
		if ((i % 2 )== 0) {
			points = new Double[] {
					10.0 * j + 10, 15.0 * (j % 2) + 15 * i, 
					10.0 * j + 20, 15.0 * ((j + 1) % 2) + 15 * i, 
					10.0 * j, 15.0 * (j + 1% 2) + 15 * i
					};
		}
		else {
			points = new Double[] {
					10.0 * j + 10, 15.0 * ((j + 1) % 2) + 15 * i, 
					10.0 * j + 20, 15.0 * (j % 2) + 15 * i, 
					10.0 * j, 15.0 * (j % 2) + 15 * i
					};
			}
		Image.getPoints().addAll(points);
		Image.setFill(BufferCell.getDisplayColor());
		Image.setStrokeWidth(0.3);
		Image.setStroke(Color.BLACK);
		return Image;
	}
	
	//more efficient way is to make it so that each time you simply add the point to the XYCHart instead of creating an entirely new xy chart, trying to make flexible
	
	private LineChart<Number, Number> GenerateLineChart() {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Population counts");
        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        lineChart.setTitle("Population Plots");
        return lineChart;
	}
	
	private XYChart.Series<Integer, Integer> GenerateSeries() {
        XYChart.Series series = new XYChart.Series();
        return series;
     }
	
	private XYChart.Series<Integer, Integer>[] GenerateSeriesArray(int arraysize) {
		XYChart.Series<Integer, Integer>[] array = new XYChart.Series<Integer, Integer>[arraysize];
		
	}
	
	
	//Launches game
	public static void main(String[] args) {
		Application.launch(args);
	}
}
