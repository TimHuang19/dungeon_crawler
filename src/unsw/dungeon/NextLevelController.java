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
		case "level_1.json":
	    	completionName.setText("Level 1 of 8 Clear");
	    	break;
		case "level_2.json":
	    	completionName.setText("Level 2 of 8 Clear");
	    	break;
		case "level_3.json":
	    	completionName.setText("Level 3 of 8 Clear");
	    	break;
		case "level_4.json":
	    	completionName.setText("Level 4 of 8 Clear");
	    	break;
		case "level_5.json":
	    	completionName.setText("Level 5 of 8 Clear");
	    	break;
		case "level_6.json":
	    	completionName.setText("Level 6 of 8 Clear");
	    	break;
		case "level_7.json":
	    	completionName.setText("Level 7 of 8 Clear");
	    	break;
		case "level_8.json":
	    	completionName.setText("Level 8 of 8 Clear");
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
		case "level_1.json":
	    	(new DungeonScreen(stage, "level_2.json")).start();
	    	break;
		case "level_2.json":
	    	(new DungeonScreen(stage, "level_3.json")).start();
	    	break;
		case "level_3.json":
	    	(new DungeonScreen(stage, "level_4.json")).start();
	    	break;
		case "level_4.json":
	    	(new DungeonScreen(stage, "level_5.json")).start();
	    	break;
		case "level_5.json":
	    	(new DungeonScreen(stage, "level_6.json")).start();
	    	break;
		case "level_6.json":
	    	(new DungeonScreen(stage, "level_7.json")).start();
	    	break;
		case "level_7.json":
	    	(new DungeonScreen(stage, "level_8.json")).start();
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