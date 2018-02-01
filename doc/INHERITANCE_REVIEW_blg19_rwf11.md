PART 1
1. The GUI is hiding the way it is going to create a new root and a new scene, and how it is going to return that. In essence, the Manager class is not going to know how the visualizer is creating it's scenes or frames; it's only going to know that it gets the next scene whenever it asks for one. 
2. Internally within the visualizer there aren't any inheritance implementations, but in terms of inheritance in general, the visualization class assumes that the methods that it calls, referring to the cells have implemented the methods required to run. This behavior is mostly oriented around taking in a 2D cell array (assuming that the grid class does it's job) and that the cells each individually have the properties that we require.
3. Within the visualizer, everything will be closed in order to ensure that the Visualizer class does exactly what it needs to. In other words, any function related to generating the next scene is handled within the visualizer class, with the manager class oblivious except to the fact that it gets what it wants. 
4. Errors will happen if the 2D Cell array is incomplete or contains "bad data", e.g. Illegal arguments or null pointers. 
5. This design is good because it helps separate the scene generation from the manager, and any changes that need to be made to the scene generation can happen in a closed, controlled environment.
PART 2
1. My area is dependent on the idea that the Grid class does output a valid 2D array of Cells, properly initializing each entry, and on the principle that each cell properly has parameters related to their positions and states/types.
2. Yes, because it's not necessarily necessary to have each cell retain it's own position, though it is necessary for the visualizer's implementation.
3. These dependencies are already minimized by encapsulating all these parameters within each individual object. Within this class, however, we are passing in multiple integers from the manager class, which is a dependency only on the manager class and not the cell or grid classes. 
4. For the most part, the individual jobs of each subclass are encapsulated within each class, and the super class (manager class) is only in charge of calling and feeding information and putting data together. One potential area for improvement might be storing cell locations within the manager class, though it would also work by keeping it in each subclass
PART 3
1. * Returns a scene given a 2D cell array
* Allows for choice of file
* Allows for type of simulation
* Creates interactive drop menus
* Creates interactive buttons to progress the simulation
2. I'm most excited to work on the encapsulation of the operations and functions of the simulations and especially visualizer. Aside from that, making scalable scenes and visuals seems to be pretty interesting and exciting. 
3. I'm most worried about properly implementing inheritance and encapsulation; while I have some conception of what's going on, it's difficult for me to understand precisely what's "good" and what's "bad", aside from what my intuition tells me. 