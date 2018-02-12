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
* *D* - double animation speed (No longer has maximum speed, but too fast and you will lose frames because they can't be generated that fast)
* *A* - halve animation speed (No longer has minimum speed)
* *S* - Load simulation's next State
* *M* - return to the menu

## Assumptions
* Input XML file will exactly follow the requested format, otherwise they will not load.
* Simulation scene should only be a triangle, square, or hexagon, with no combinations of them.
* Only simulations available are "Game of Life", "Fire", "Wator World", and "Segregation", and "Sugar Scape"

## Known bugs
* Occasionally, closing the simulation window results in a second window popping up, with the simulation continuing as if it had not been closed.
* Slider's often cannot be slid quickly enough; constant minute changes to slider value can cause problems, suggested to click a position on the slider bar rather an slide to said position.
* Moving the slider all the way to one side in some simulations causes errors (but will not crash the program).

## Extra features
* Cells can be squares, triangles, or hexagons.
* Neighbour formation can be cardinal, diagonal, 3-next, or all formations.
* Able to save current state of a simulation and load that state from file
* Custom file maker option on menu
* Plot of populations with legend and autoscaling added
* Sliders to vary parameter values, with labels are added. The quantity of which is dynamic according to the type of simulation

## Impressions of the project
* Separation and manipulation of visuals from the application class itself was more difficult than anticipated, with variable accessibility becoming convoluted
* Much refactoring can be done to Manager.java to clean it up a bit, though not as much as the original target of Visualizer.

## Visuals Decisions
* Usage of VBox to prevent overlap
* Declared integers in beginning to shift the simulation itself to accomodate VBox, rather than have simulation in it's own VBox because at the time of simulation implementation, VBoxes weren't necessary
* Unimplemented Features:
> - *Allow users to interact with the simulation dynamically to create or change a state at a grid location* : Unimplemented due to time. However, this feature I anticipate to be relatively simple to add. Based of a mouse click's location in the Scene, we can iterate across the Group representing the cells and use boundary checking. A method within Cell would need to be added to cycle to the next cell type, and the Manager would have to edit the grid (represented in code as gridArray) so as to ensure that grid update is working off of the new grid
> - *Allow users to run multiple simulations at the same time so they can compare the results side by side (i.e., do not use tabs like a browser)* : Unimplemented at this time. Were I to implement it, however, I would add a button to the simulation scene (returned by setupScene) that, when pressed, would open a new window and call setupScene within that new window, thus duplicating the Grid object and all the parameters. That simulation, however, would take it's own, random course as defined by the parameters. As to whether the button interactions would be universal, chances are pause would pause both, etc. due to the implementation of the hardware buttons. However, this depends most on Java's specific interpretation. 

## Possible Improvements
* Color, size of each individual cell, etc, could all be added easily to the XML setup in a similar way that grid type, neighbour type, and shape werer added.
* Initial configurations could be configured by total number of cells instead of percentages by having a counter instead of a Java Random object.
* More extensive error checking for inputs files. As of now, there is very extensive error checking for file creation to ensure that the file will actually be usable. For loading files, the program will ignore files that it can't load (it will not crash though).