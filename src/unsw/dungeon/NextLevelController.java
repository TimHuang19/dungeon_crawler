package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NextLevelController {
	
    @FXML
    private Button startNextButton;
    
    @FXML
    private Button restartPreviousButton;
    
    @FXML
    private Button mainMenuLvlButton;
    
    @FXML
    private Button quitLvlButton;
    
    private Stage stage;
    
    private String fileName;
    
    public NextLevelController(Stage stage, String fileName) {
    	this.stage = stage;
    	this.fileName = fileName;
    }
    
    @FXML
    public void handleStartNextButton(ActionEvent event) throws IOException {
    	switch (fileName) {
    		case "maze.json":
    	    	(new DungeonScreen(stage, "advanced.json")).start();
    	    	break;
    	}
    		
    }

    @FXML
    public void handleRestartPreviousButton(ActionEvent event) throws IOException {
    	System.out.println(fileName);
    	(new DungeonScreen(stage, fileName)).start();
    }
    
    @FXML
    public void handleMainMenuLvlButton(ActionEvent event) throws IOException {
    	(new StartScreen(stage)).start();
    }
    
    @FXML
    public void handleQuitLvlButton(ActionEvent event) {
    	System.exit(0);
    }

}