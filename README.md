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
### Menu
* *Start Selected Simulation* - begins the simulation specified by the drop down menu
* *Drop down menu* - select a default simulation
* *Guide* - loads the user guide
* *Open a file...* - choose custom file, **will be treated as whatever type XML file is selected in drop down menu**

### In simulations
* *P* - pauses/plays the simulation
* *Arrow Up* - double animation speed (maximum speed: 2x)
* *Arrow Down* - halve animation speed (minimum speed: 0.5x)
* *S* - Load simulation's next State
* *M* - return to the menu

## Assumptions
* Input XML file will exactly follow the requested format, otherwise they will not load.
* Simulation scene is a 2D square array
* Only simulations available are "Game of Life", "Fire", "Wator World", and "Segregation", and "Sugar Scape"

## Known bugs
* Occasionally, closing the simulation window results in a second window popping up, with the simulation continuing as if it had not been closed.
* 

## Extra features
* Cells can be squares, triangles, or hexagons.
* Neighbour formation can be 

## Impressions of the project
* Separation and manipulation of visuals from the application class itself was more difficult than anticipated, with variable accessibility becoming convoluted