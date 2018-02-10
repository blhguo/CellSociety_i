package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

import cell.Cell;
import grid.FireSimGrid;
import grid.GOLSimGrid;
import grid.Grid;
import grid.SegregationSimGrid;
import grid.WatorSimGrid;
import javafx.animation.Animation.Status;
import javafx.animation.FadeTransition;
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
import javafx.scene.control.TextField;
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
import xml.makers.FireXMLmaker;
import xml.makers.GOLXMLmaker;
import xml.makers.SegXMLmaker;
import xml.makers.WatorXMLmaker;
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
	private static final int MAKER_SIZE = 300;
	private boolean inMenu = true;
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	public static final String DEFAULT_RESOURCE_FILE = "defaultText";
	private ResourceBundle myResources;
	private static final String FileS = "FileS";
	private static final String FileW = "FileW";
	private static final String FileF = "FileF";
	private static final String FileGOL = "FileGOL";
	private TextField probcell = null;
	private TextField probx = null;
	private TextField probo = null;
	private TextField thresh = null;
	private TextField probfish = null;
	private TextField probshark = null;
	private TextField rtshark = null;
	private TextField rtfish = null;
	private TextField eShark = null;
	private TextField geShark = null;
	private TextField probfire = null;
	private TextField problight = null;
	private TextField probnewtree = null;

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
		Button makerButton = GenerateMakerButton(stage);

		splash.getChildren().add(startButton);
		splash.getChildren().add(fileChoiceBox);
		splash.getChildren().add(guideButton);
		splash.getChildren().add(openButton);
		splash.getChildren().add(makerButton);

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

	// Generates button to start the maker scene
	public Button GenerateMakerButton(Stage s) {
		Button makerButton = new Button("Create custom XML file");

		makerButton.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent e) {	
						try {
							s.setScene(setupXMLmaker(MAKER_SIZE, MAKER_SIZE, BACKGROUND));
							System.out.println("6");
							inMenu = true;
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}

				});
		return makerButton;
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

	public Button GenButton(GridPane grid, String name, int x, int y) {
		Button temp = new Button(name);
		GridPane.setConstraints(temp, x, y);
		grid.getChildren().add(temp);
		return temp;
	}

	private Scene setupXMLmaker(int width, int height, Paint background) {
		// make gridpane
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

		// add text entry fields
		//Text title = new Text();
		//title.setText("'M' to return to main menu");
		//grid.add(title,0,0);
		final TextField file = makeTextField(grid, "name (no extension)", 0, 1);
		final TextField gridx = makeTextField(grid, "grid x dimension", 0, 2);
		final TextField gridy = makeTextField(grid, "grid y dimension", 0, 3);
		final TextField cellx = makeTextField(grid, "cell x dimension", 0, 4);
		final TextField celly = makeTextField(grid, "cell y dimension", 0, 5);
		if(fileType == 0) {
			probcell = makeTextField(grid, "cell probability (0-1)", 1, 1);
		}
		else if(fileType == 1) {
			probx = makeTextField(grid, "X type cell probability (0-1)", 1, 1);
			probo = makeTextField(grid, "O type cell probability (0-1)", 1, 2);
			thresh = makeTextField(grid, "Enter threshold (0-1)", 1, 2);
		}
		else if(fileType == 2) {
			probfish = makeTextField(grid, "fish probability (0-1)", 1, 1);
			probshark = makeTextField(grid, "shark probability (0-1)", 1, 2);
			rtshark = makeTextField(grid, "Shark rep threshold", 1, 3);
			rtfish = makeTextField(grid, "Fish rep threshold", 1, 4);
			eShark = makeTextField(grid, "Shark energy total", 1, 5);
			geShark = makeTextField(grid, "Shark energy per fish", 1, 6);
		}
		else if(fileType == 3) {
			probfire = makeTextField(grid, "fire prob (0-1)", 1, 1);
			problight = makeTextField(grid, "lightning prob (0-1)", 1, 2);
			probnewtree = makeTextField(grid, "new tree prob (0-1)", 1, 3);
		}
		Button menu = GenButton(grid, "Menu", 0, 0);
		menu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				try {
					returnMenu();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		Button create = GenButton(grid, "Create", 1, 0);
		create.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				boolean isError = false;
				int gridxval = 400;
				int gridyval = 400;
				int cellxval = 40;
				int cellyval = 40;
				double probcellval = 0.3;
				double probxval = 0.3;
				double proboval = 0.3;
				double threshval = 0.3;
				double probfishval = 0.3;
				double probsharkval = 0.3;
				int rtsharkval = 5;
				int rtfishval = 5;
				int esharkval = 5;
				int gesharkval = 3;
				double probfireval = 0.5;
				double problightval = 0.01;
				double probnewtreeval = 0.01;
				String filename = file.getText();
				if(filename.contains(".")) {
					isError = true;
				}

				try {
					gridxval = Integer.parseInt(gridx.getText());
					gridyval = Integer.parseInt(gridy.getText());
					cellxval = Integer.parseInt(cellx.getText());
					cellyval = Integer.parseInt(celly.getText());
				} catch(NumberFormatException ea) {
					displayMessage(grid, "Invalid parameters", 3, 0, 8);
					isError = true;
					//ea.printStackTrace();
				}

				try {
					if(fileType == 0) {
						try {
							probcellval = Double.parseDouble(probcell.getText());
						} catch(NumberFormatException ea) {
							displayMessage(grid, "Invalid parameters", 3, 0, 8);
							isError = true;
							//ea.printStackTrace();
						}
						if(!isError) {
							new GOLXMLmaker(filename, "square", gridxval, gridyval, cellxval, cellyval, probcellval);
							displayMessage(grid, filename + ".xml created!", 3, 1, 8);
						}		
					}
					else if(fileType == 1) {
						try {
							probxval = Double.parseDouble(probx.getText());
							proboval = Double.parseDouble(probo.getText());
							threshval = Double.parseDouble(thresh.getText());
						} catch(NumberFormatException ea) {
							displayMessage(grid, "Invalid parameters", 3, 0, 8);
							isError = true;
							//ea.printStackTrace();
						}
						if(!isError) {
							new SegXMLmaker(filename, "square", gridxval, gridyval, cellxval, cellyval, probxval, proboval, threshval);
							displayMessage(grid, filename + ".xml created!", 3, 1, 8);
						}	
					}
					else if(fileType == 2) {
						try {
							probfishval = Double.parseDouble(probfish.getText());
							probsharkval = Double.parseDouble(probshark.getText());
							rtsharkval = Integer.parseInt(rtshark.getText());
							rtfishval = Integer.parseInt(rtfish.getText());
							esharkval = Integer.parseInt(eShark.getText());
							gesharkval = Integer.parseInt(geShark.getText());
						} catch(NumberFormatException ea) {
							displayMessage(grid, "Invalid parameters", 3, 0, 8);
							isError = true;
							//ea.printStackTrace();
						}
						if(!isError) {
							new WatorXMLmaker(filename, "square", gridxval, gridyval, cellxval, cellyval, probfishval, 
									probsharkval, rtsharkval, rtfishval, esharkval, gesharkval);
							displayMessage(grid, filename + ".xml created!", 3, 1, 8);
						}	
					}
					else if(fileType == 3) {
						try {
							probfireval = Double.parseDouble(probfire.getText());
							problightval = Double.parseDouble(problight.getText());
							probnewtreeval = Double.parseDouble(probnewtree.getText());
						} catch(NumberFormatException ea) {
							displayMessage(grid, "Invalid parameters", 3, 0, 8);
							isError = true;
							//ea.printStackTrace();
						}
						if(!isError) {
							new FireXMLmaker(filename, "square", gridxval, gridyval, cellxval, cellyval, probfireval, problightval, probnewtreeval);
							displayMessage(grid, filename + ".xml created!", 3, 1, 8);
						}	
					}	
				} catch (FileNotFoundException e1) {
					displayMessage(grid, "Invalid parameters", 3, 0, 8);
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					displayMessage(grid, "Invalid parameters", 3, 0, 8);
					e1.printStackTrace();
				}
				if(isError) {
					displayMessage(grid, "Invalid parameters", 3, 0, 8);
				}
			}
		});
		// create a place to see the shapes
		Scene scene = new Scene(grid, width, height, background);
		// set what happens on key press
		scene.setOnKeyPressed(ex -> {
			// return to menu scene
			try {
				if(ex.getCode() == KeyCode.M) {
					returnMenu();
				}		
			} catch (Exception e1) {
				displayMessage(grid, "Invalid parameters", 3, 0, 7);
				e1.printStackTrace();
			}
		});
		return scene;
	}

	private void displayMessage(GridPane grid, String message, int time, int x, int y) {
		Text display = new Text();
		grid.add(display,x,y);
		display.setText(message);
		FadeTransition ft = new FadeTransition(Duration.millis(time*1000), display);
		ft.setFromValue(1.0);
		ft.setToValue(0);
		ft.setCycleCount(1);
		ft.play();
	}
	
	private TextField makeTextField(GridPane grid, String prompt, int x, int y) {
		final TextField temp = new TextField();
		temp.setPromptText(prompt);
		temp.setPrefColumnCount(10);
		temp.getText();
		GridPane.setConstraints(temp, x, y);
		grid.getChildren().add(temp);
		return temp;
	}

	// Helper function of setupScene, returns a root with everything needed
	public Group CreateRoot(Cell[][] cellArray, int width, int height) {
		Group addition = new Group();
		for (int i = 0; i < cellArray[0].length; i++) {
			for (int j = 0; j < cellArray[1].length; j++) {
				addition.getChildren().add(GenerateCell(cellArray[i][j], width, height, i, j));
			}
		}
		return addition;
	}
	//Helper function for CreateRoot, generates one cell
	private Rectangle GenerateCell(Cell BufferCell, int width, int height, int i, int j) {
		Rectangle Image = new Rectangle((width * i + XPADDING), (height * j + YPADDING), width, height);
		Image.setFill(BufferCell.getDisplayColor());
		Image.setStrokeWidth(0.3);
		Image.setStroke(Color.BLACK);
		return Image;
	}
	//Launches game
	public static void main(String[] args) {
		Application.launch(args);
	}
}
