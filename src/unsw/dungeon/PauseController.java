package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PauseController {
	
	@FXML
	private Button resumeButton;
	
	@FXML
	private Button restartButton;
	
	@FXML
	private Button homeButton;
	
	@FXML
	private Button quitPauseButton;
	
    @FXML
    private Label levelName;
    
    private Stage stage;
    
    private String fileName;
    
    private DungeonScreen dungeonScreen;
    
    public PauseController(Stage stage, String fileName, DungeonScreen dungeonScreen) {
    	this.stage = stage;
    	this.fileName = fileName;
    	this.dungeonScreen = dungeonScreen;
    }
    
    @FXML
    public void initialize() {
    	switch (fileName) {
			case "maze.json":
		    	levelName.setText("Level 1 of 6");
		    	break;
			case "advanced.json":
				levelName.setText("Level 6 of 6");
				break;
    	}
    }
    
    @FXML
    public void handleResumeButton() throws IOException {
    	dungeonScreen.unPause();
    	dungeonScreen.start();
    }
    
    @FXML
    public void handleRestartButton() throws IOException {
    	(new DungeonScreen(stage, fileName)).start();
    }
    
    @FXML
    public void handleHomeButton() throws IOException {
    	(new StartScreen(stage)).start();
    }
    
    @FXML
    public void handleQuitPauseButton() {
    	System.exit(0);
    }

}