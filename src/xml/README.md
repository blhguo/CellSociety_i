# XML README
## Packages
Don't forget to import the packages:
```java
import xml.*;
```
## Syntax
Use the following format or something along the line of it when using this:
```java
if(fileName.equals(segregation)) {
	SegregationXMLreader xml_reader = new SegregationXMLreader();
	SegregationSimSetup simInfo = xml_reader.read(fileName);
	simInfo.printInfo();
}
else if(fileName.equals(wator)) {
	WatorXMLreader xml_reader = new WatorXMLreader();
	WatorSimSetup simInfo = xml_reader.read(fileName);
	simInfo.printInfo();
}
else if(fileName.equals(fire)) {
	FireXMLreader xml_reader = new FireXMLreader();
	FireSimSetup simInfo = xml_reader.read(fileName);
	simInfo.printInfo();
}
else {
	GOLXMLreader xml_reader = new GOLXMLreader();
	GOLSimSetup simInfo = xml_reader.read(fileName);
	simInfo.printInfo();
}
```