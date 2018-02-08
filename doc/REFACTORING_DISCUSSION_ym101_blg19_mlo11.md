# Static Code Review and Refactoring
By: Brandon Guo (blg19), Marcus Oertle (mlo11), and Yashas Manjunatha (ym101)

### Duplication Refactoring
* We made duplicate files, objects, or strings into global constants. That way, they only appear once instead of repeatedly as the same hardcoded string. 
* We also tried to refactor some of our Grid code. In the Wator simulation code we had duplications in checking and moving to empty neighbors and checking and reproducing cells. We created methods for the duplicated code and used the method calls instead.
* XMLmaker:
    * Removed "if" tree entirely, replaced with separate classes for each type of XML file to generate so there is less repeated code.
    * Moved all "writer.println" lines into separate methods that can be called multiple times instead of having the same "writer.println" line used multiple times.

### Checklist Refactoring
* Added curly braces everywhere
* Made all "final" instance variables all CAPS instead of lower-case.
* Fixed indentations
* Updated some code logic to use calls like .isEmpty on collections instead of checking if .size == 0.
* Abstracted from initializing List variables with ArrayList<Cell> to ArrayList

### General Refactoring
* With regard to how we deal with neighbors, we temporarily updated the override implementations of addNeighbors to call super.addNeighbors instead of duplicating this code. However, moving forward, we talked about creating a Neighbors class or a CellShape class that had different implementations of addNeighbors and that the Grid class would instantiate one of these objects and use the code from the object to addNeighbors according to the specifications.