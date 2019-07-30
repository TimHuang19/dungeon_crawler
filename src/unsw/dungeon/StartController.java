package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartController {

    @FXML
    private Button startButton;
    
    @FXML
    private Button levelButton;
    
    @FXML
    private Button quitButton;
    
    private Stage stage;
    
    public StartController(Stage stage) {
    	this.stage = stage;
    }
    
    @FXML
    public void handleStartButton() throws IOException {
        (new DungeonScreen(stage, "maze.json")).start();
    }
    
    @FXML
    public void handleLevelButton() {
    	
    }
    
    @FXML
    public void handleQuitButton() {
    	System.exit(0);
    }

}