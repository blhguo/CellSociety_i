## Inheritance Review 2/1/18

### Group members
* Marcus Oertle
* August Ning
* Brandon Dalla Rosa

### Part 1
1. What is an implementation decision that your design is encapsulating (i.e., hiding) for other areas of the program?
	*  The actual XML is hidden from the user, they only get the data back from the object.
2. What inheritance hierarchies are you intending to build within your area and what behavior are they based around?
	* The SimulationSetup object has multiple subclasses for each simulation.  I want to do the same for the XMLreader class itself but am struggling to find a balance between inheritance/extendability and ease of use by the user.
3. What parts within your area are you trying to make closed and what parts open to take advantage of this polymorphism you are creating?
	* XMLreader is the accessible part, everything is used by the XMLreader itself. 
4. What exceptions (error cases) might occur in your area and how will you handle them (or not, by throwing)?
	* If the file is not found, it will throw a file not found exception.
	* If there is an unknown error in the XMLreader, it will return null. The user can check for a return "null" and handle it however they want to.
5. Why do you think your design is good (also define what your measure of good is)?
	* My design uses inheritance to be easily extendable to other simulation types. My measure of good is how clean and extendable code is. Right now my design is extendable, but not yet clean which I am still brainstorming on.

### Part 2
1. How is your area linked to/dependent on other areas of the project?
	* Utilized by the manager class to extract all necessary information/parameters about a simulation to then pass to other methods. It is independent from other areas (ie. does not require any other areas to run, only needs the XML files to exist in a proper format).
2. Are these dependencies based on the other class's behavior or implementation?
	* No, other areas' classes do not matter for the XMLreader. The only dependencies are on the XML files themselves.
3. How can you minimize these dependencies?
	* Not really much to say on this since there aren't a lot of dependencies. 
4. Go over one pair of super/sub classes in detail to see if there is room for improvement. 
	* Right now I have a SimulationSetup superclass and a bunch of subclasses for each simulation. There is no need for the superclass to NOT be abstract so I definitely need to refactor that into an abstract class.
5. Focus on what things they have in common (these go in the superclass) and what about them varies (these go in the subclass).
	* The specific data that needs to be extracted and stored is different for cells in each simulation.

### Part 3
1. Come up with at least five use cases for your part (most likely these will be useful for both teams).
	* Different file names being passed in and parsed
	* Accessing the stored info from other parts of the project
	* Changing simulations
	* Handling different errors
	* Handle different simulation types with different tags
2. What feature/design problem are you most excited to work on?
	* I am really antsy to start reworking the inheritance. I really want to find a way to make the XMLreader itself a superclass, but I need some serious reworking to do that. 
3. What feature/design problem are you most worried about working on?
	* The exact same thing that I'm most excited to work. It's exciting because it is really cool structure but terrifying because I am going to spend **way** too much time on it and probably act like a fish out of water half the time.