package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class NextLevelController {
	
    @FXML
    private Label completionName;
    
    @FXML
    private Button nextButton;
    
    @FXML
    private Button homeNextButton;
    
    @FXML
    private Button quitNextButton;
    
    private Stage stage;
    
    private String fileName;
    
    public NextLevelController(Stage stage, String fileName) {
    	this.stage = stage;
    	this.fileName = fileName;
    }
    
    @FXML
    public void initialize() {
    	switch (fileName) {
		case "maze.json":
	    	completionName.setText("Level 1 of 6 Clear");
	    	break;
    	}
    }
    
    @FXML
    public void handleContinueButton() throws IOException {
    	switch (fileName) {
		case "maze.json":
	    	(new DungeonScreen(stage, "advanced.json")).start();
	    	break;
    	}
    }
    
    @FXML
    public void handleHomeNextButton() throws IOException {
    	(new StartScreen(stage)).start();
    }
    
    @FXML
    public void handleQuitNextButton() {
    	System.exit(0);
    }
    
}