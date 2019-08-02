package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The Class NextLevelController.
 */
public class NextLevelController {
	
    /** The completion name. */
    @FXML
    private Label completionName;
    
    /** The next button. */
    @FXML
    private Button nextButton;
    
    /** The home next button. */
    @FXML
    private Button homeNextButton;
    
    /** The quit next button. */
    @FXML
    private Button quitNextButton;
    
    /** The stage. */
    private Stage stage;
    
    /** The file name. */
    private String fileName;
    
    /**
     * Instantiates a new next level controller.
     *
     * @param stage the stage
     * @param fileName the file name
     */
    public NextLevelController(Stage stage, String fileName) {
    	this.stage = stage;
    	this.fileName = fileName;
    }
    
    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
    	switch (fileName) {
		case "maze.json":
	    	completionName.setText("Level 1 of 6 Clear");
	    	break;
    	}
    }
    
    /**
     * Handle continue button.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    public void handleContinueButton() throws IOException {
    	switch (fileName) {
		case "maze.json":
	    	(new DungeonScreen(stage, "last.json")).start();
	    	break;
    	}
    }
    
    /**
     * Handle home next button.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    public void handleHomeNextButton() throws IOException {
    	(new StartScreen(stage)).start();
    }
    
    /**
     * Handle quit next button.
     */
    @FXML
    public void handleQuitNextButton() {
    	System.exit(0);
    }
    
}