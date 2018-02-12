# Cell Society Team 18

CompSci 308 Cell Society Project

*Date Started*: January 25, 2018

*Date Finished*: February 11, 2018

## Project information
### Team members:
* Marcus Oertle (mlo11) - XML processor
* Brandon Guo (blg19) - Front end graphics
* Yashas Manjunatha (ym101) - Back-end processes

### Resources used
* [TutorialsPoint XML tutorial](https://www.tutorialspoint.com/java_xml/java_dom_query_document.htm)
* [StackOverflow] (https://stackoverflow.com/questions/28290814/how-to-slow-down-javafx-animation)
* [Timeline and Animation] (https://docs.oracle.com/javafx/2/animations/basics.htm)
* [Game of Life Simulation Rules] (https://en.wikipedia.org/wiki/Conway's_Game_of_Life)
* [Segregation Simulation Rules] (http://nifty.stanford.edu/2014/mccown-schelling-model-segregation/)
* [Wator World Simulation Rules] (https://en.wikipedia.org/wiki/Wa-Tor)
* [Fire Simulation Rules] (http://nifty.stanford.edu/2007/shiflet-fire/)

### Important files
* [Manager.java](https://coursework.cs.duke.edu/CompSci308_2018Spring/cellsociety_team18/blob/master/src/gui/Manager.java) in package "gui" is used to start the application.
* Testing:
	* [XMLtester.java](https://coursework.cs.duke.edu/CompSci308_2018Spring/cellsociety_team18/blob/master/src/xml/XMLtester.java) used to test the XML reader and XML maker.
* Resource files:
	* ["resources"](https://coursework.cs.duke.edu/CompSci308_2018Spring/cellsociety_team18/tree/master/src/resources) folder in "src" directory required for all text in "defaultText.properties"
	* ["data"](https://coursework.cs.duke.edu/CompSci308_2018Spring/cellsociety_team18/tree/master/data) folder in main directory along with associated XML required for running the default simulations

## Controls
### Menu - **!!!NOTE!!! The selection from the drop down menu determines how files will be read and what type of custom file will be created.**
* *Start Selected Simulation* - begins the simulation specified by the drop down menu
* *Drop down menu* - select a default simulation
* *Guide* - loads the user guide
* *Open a file...* - choose custom file, **will be treated as whatever type XML file is selected in drop down menu**
* *Create a custom file* - allows the user to build their own simulation from within the application

### In simulations
* *P* - pauses/plays the simulation
* *Arrow Up* - double animation speed (maximum speed: 2x)
* *Arrow Down* - halve animation speed (minimum speed: 0.5x)
* *S* - Load simulation's next State
* *M* - return to the menu

## Assumptions
* Input XML file will exactly follow the requested format, otherwise they will not load.
* Simulation scene should only be a triangle, square, or hexagon, with no combinations of them.
* Only simulations available are "Game of Life", "Fire", "Wator World", and "Segregation", and "Sugar Scape"

## Known bugs
* Occasionally, closing the simulation window results in a second window popping up, with the simulation continuing as if it had not been closed.
* Slider's often cannot be slid quickly enough; constant minute changes to slider value can cause problems, suggested to click a position on the slider bar rather an slide to said position.

## Extra features
* Cells can be squares, triangles, or hexagons.
* Neighbour formation can be cardinal, diagonal, 3-next, or all formations.
* Able to save current state of a simulation and load that state from file
* Plot of populations with legend and autoscaling added
* Sliders to vary parameter values, with labels are added. The quantity of which is dynamic according to the type of simulation

## Impressions of the project
* Separation and manipulation of visuals from the application class itself was more difficult than anticipated, with variable accessibility becoming convoluted
* Much refactoring can be done to Manager.java to clean it up a bit, though not as much as the original target of Visualizer.