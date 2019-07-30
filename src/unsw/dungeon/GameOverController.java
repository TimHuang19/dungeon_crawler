package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GameOverController {
	
    @FXML
    private Button restartButton;
    
    @FXML
    private Button mainMenuButton;
    
    @FXML
    private Button quitOverButton;    
    
    private Stage stage;
    
    private String fileName;
    
    public GameOverController(Stage stage, String fileName) {
    	this.stage = stage;
    	this.fileName = fileName;
    }

    @FXML
    public void handleRestartButton(ActionEvent event) throws IOException {
    	(new DungeonScreen(stage, fileName)).start();
    }
    
    @FXML
    public void handleMainMenuButton(ActionEvent event) throws IOException {
    	(new StartScreen(stage)).start();
    }
    
    @FXML
    public void handleQuitOverButton(ActionEvent event) {
    	System.exit(0);
    }

}