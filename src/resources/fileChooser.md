### File Chooser code for Brandon

```java
FileChooser fileChooser = new FileChooser();
Button openButton = new Button("Open a File...");

openButton.setOnAction(
	new EventHandler<ActionEvent>() {
		@Override
		public void handle(final ActionEvent e) {
			File file = fileChooser.showOpenDialog(mStage);
			if (file != null) {
				fileName = file.getName();
				fileName = "data/" + fileName;			
			}
		}
	});
```

* must then add button to scene
* "fileName" must be instance variable of class that is using this code