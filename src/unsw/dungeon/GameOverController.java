package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * The Class GameOverController.
 */
public class GameOverController {
	
    /** The restart button. */
    @FXML
    private Button restartButton;
    
    /** The main menu button. */
    @FXML
    private Button mainMenuButton;
    
    /** The quit over button. */
    @FXML
    private Button quitOverButton;    
    
    /** The stage. */
    private Stage stage;
    
    /** The file name. */
    private String fileName;
    
    /**
     * Instantiates a new game over controller.
     *
     * @param stage the stage
     * @param fileName the file name
     */
    public GameOverController(Stage stage, String fileName) {
    	this.stage = stage;
    	this.fileName = fileName;
    }

    /**
     * Handle restart button.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    public void handleRestartButton() throws IOException {
    	(new DungeonScreen(stage, fileName)).start();
    }
    
    /**
     * Handle main menu button.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    public void handleMainMenuButton() throws IOException {
    	(new StartScreen(stage)).start();
    }
    
    /**
     * Handle quit over button.
     */
    @FXML
    public void handleQuitOverButton() {
    	System.exit(0);
    }

}