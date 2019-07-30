package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CompletedDungeonController {
	
    @FXML
    private Button homeEndButton;
    
    @FXML
    private Button quitEndButton;
    
    private Stage stage;
        
    public CompletedDungeonController(Stage stage) {
    	this.stage = stage;
    }
    
    @FXML
    public void handleHomeEndButton() throws IOException {
    	(new StartScreen(stage)).start();
    }
    
    @FXML
    public void handleQuitEndButton() {
    	System.exit(0);
    }

}