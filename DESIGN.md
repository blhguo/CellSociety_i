# Cell Society Design Discussion

### High-Level Design Goals
* Create the ground work for a simulation program that can be easily extended to any type of simulation and then apply a variety of extra parameters and simulation logics to that ground work to test its flexibility.

### How to Add New Features/Simulations
* New Simulations:
    * A new XMLreader subclass must be created for the new simulation. It can get all of the standardized simulation parameters by calling the read method of its super class and can then parse the remaining parameters however it needs to.
    * A new SimulationSetup subclass must be created for the new simulation. It can utilize its super class to store all standardized parameters. Any simulation specific parameters it must store and create a getter method for those parameters.
    * If you want to be able to let the user generate their own XML file for the simulation, you have to make a new XMLmaker subclass. A call to the super class will print all the header information, but how to generate the cells must be done within the sub class. There are methods from the super class that are accessible here such as "printCell" that are very useful in streamlining this method's creation.
    * Additionally, to add a new simulation, a new Cell subclass and a new Grid subclass must be created. In the Grid subclass, the initialization call for the object must be defined taking in the various parameters of the simulation. Additionally, certain abstract methods need to be implemented in the Grid subclass that describe the specific parameters and types of cells in the simulation.
    * To add new types of cells to a simulation, first, an abstract Cell subclass must be created that is the abstract cell of the simulation and holds parameter information and simulation logic common to all cells in the simulation (or of the patches below cells).
    * Then, create new subclass of the previously created abstract class for that simulation cell for every type of cell in the simulation. Certain abstract methods must be implemented including providing the display color of the cell and the updating logic for the cell according to the simulation rules.

* New Shapes:
    * If other shapes are desired, the creation of a new Helper Method for "CreateRoot" inside Manager.java is recommended, with the switch case within "CreateRoot" updated as well. Note that adding a new shape requires that the shapes be regular and able to gaplessly span the entire simulation. For example, regular octagons cannot consistently span a grid without the use of squares. 
    * If a combination of shapes is used or desired, much more extensive and robust shape generation code is needed (in order to determine which shape to generate and where). In addition, the addition of a new shape requires new scaling methods for that shape specifically to ensure that it fits within the designated area. In addition, should a new shape be desired, it may become necessary to redefine what a cell's set of neighbors is.
    * Should new shapes be added, changes to the way "nextState" interacts with neighbors may also become necessary, depending on the intended performance or result of the simulation. 

* Click to dynamically adjust cells:
     * In order to implement this feature, one must, on mouse click, ascertain where that mouse has clicked. Then, additional methods must be made available to the Manager class in either the Cell or Grid abstract class in order to selectively adjust and cycle through various Cell states and set them. These methods would likely be implemented within subclass of Cell or subclasses of Grid because the visualizer is not aware of what types of cells it is printing to the screen, nor is it aware of the possible cell types there are. 

* New Neighbor Arrangements:
    * In order to implement different neighbor arrangements, the Neighbor class would need to be updated. All that would need to be done is create a new method that adds the neighbor arrangement of a cell to a list of cells, and update the proper switch case with the keyword for the neighbor arrangement.

### Major Design Choices
* XML configuation design choices
    * The XMLreader, XMLmaker, and SimulationSetup classes are all split into sub-classes so that if a new simulation is to be added to the project, new subclasses can be made instead of modifying any existing files.
    * The XMLreader, XMLmaker, and SimulationSetup classes and their sub-classes were all designed to keep dependecies to a minimum. The reader only depends on the availability of the file it is trying to read, the maker only depends on the inputs to it being valid and the grid class getter methods being accessible, and the SimulationSetup also only relies on having valid inputs. The only real dependency on methods from classes outside of the XML portion of this project are in the XMLmaker sub-classes where they call methods from the grid class to get informatoin about the current simulation for saving the state.

* Back end design choices
    * There are two seperate inheritance hierarchies defining the back end development. The first is the Grid classes. The abstract Grid class deals with the simulation as a whole and has an updateGrid() method that can be called to update the simulation. Subclasses of this define the behaviour of different simulations. The Grid class has a 2D array of Cells. Cell is another abstract class with its own inheritance hierarchy. It has an abstract subclass for each simulation type, and that has a subclass for each cell type in the simulation.
    * This design was chosen so that the front end would only have to deal with the Grid class, which encapsulated the entire simulation. In other words, the Grid class is open to the front end. Whereas, the Cell classes would be closed to the front end and would be encapsulated by the Grid class. The Grid class would use Cell objects to implement the Grid class.
    * Another design choice made after the new specifications were released was to have a Neighbors class. This was done so that a simulation could call a Neighbors object with the specifications of the Grid (shape of cells, neighbor arangements, edge types, etc.), and the addNeighbors function called on the instance of the Neighbors object would accordingly perform the operation. This keeps the implementation of all the possible neighbor arrangements separated from the Grid class.

* Visualizer design choices
    * Originally, we had decided to try to separate the visuals from the user interactions as much as possible. However, as we kept running into problems with static methods and with passing variables back and forth, it made more and more sense just to integrate all of the front end and data management into one larger class, while offloading as much of the data processing and manipulation to the backend. This decision, while it became more and more obvious as we kept working, was only decided after consulting the UTAs/Mentors, who recommended to simply integrate everything into one class. 
    * A flawed choice made, however, was to put so much into the Manager class. Some functions could likely have been exported and cleaned up or refactored, specifically button generation, scene generation, and the many instances of unique XML readers/writers. A benefit to this design is that extremely few accessibility and passing issues can occur; most objects that need to be universally accessible are made global. A heavy downside, however, is the cumbersome nature that the manager class takes on, and the loss of readability. In addition, it becomes more difficult to logically reason where new code ought to be added, as the program flow itself becomes quite convoluted. 
    * Another somewhat flawed design choice was hard-coding the buffer values for the chart. Effectively, should someone want to change the size of the chart relative to the rest of the simulation, they must update the global constants declared at the beginning of the program, specifically those referring to the buffer width and the slider length. This solution is inelegant. The benefits to this approach are limited to simply being able to easily code and adjust values on the fly; resizability of the graph was not part of the project specification. A potential fix or patch is to implement scaling with regards to this constant as well; computing a space ratio of the new Chart to the old and scaling all buffer and padding variables by that amount. 

### Assumptions to Simplify/Resolve Ambiguities
* Assumed that the user will only use valid XML files. If they do not, the program is set to ignore the file. The project will not crash, that was handled by not allowing the user to load invalid XML files.
* Assumed that every subclass properly implements the methods of it's abstract class, and that the definitions used by those subclasses are also valid, specifically neighbors. 
* Assumed that the XML parser is able to return all needed values, and that none of the values are inadvertently read to "null", something that would cause null pointer exceptions.
* In the simulation logic, it is assumed that all the parameters are valid and no error checking is done to make sure the simulation parameters make sense for the simulation. It is assumed that this happens in the XML reading and that the values passed from the XML parser is correct.