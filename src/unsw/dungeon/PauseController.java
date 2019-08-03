package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The Class PauseController.
 */
public class PauseController {
	
	/** The resume button. */
	@FXML
	private Button resumeButton;
	
	/** The restart button. */
	@FXML
	private Button restartButton;
	
	/** The home button. */
	@FXML
	private Button homeButton;
	
	/** The quit pause button. */
	@FXML
	private Button quitPauseButton;
	
    /** The level name. */
    @FXML
    private Label levelName;
    
    /** The stage. */
    private Stage stage;
    
    /** The file name. */
    private String fileName;
    
    /** The dungeon screen. */
    private DungeonScreen dungeonScreen;
    
    /**
     * Instantiates a new pause controller.
     *
     * @param stage the stage
     * @param fileName the file name
     * @param dungeonScreen the dungeon screen
     */
    public PauseController(Stage stage, String fileName, DungeonScreen dungeonScreen) {
    	this.stage = stage;
    	this.fileName = fileName;
    	this.dungeonScreen = dungeonScreen;
    }
    
    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
    	switch (fileName) {
			case "level_1.json":
		    	levelName.setText("Level 1 of 8");
		    	break;
			case "level_2.json":
		    	levelName.setText("Level 2 of 8");
		    	break;
			case "level_3.json":
		    	levelName.setText("Level 3 of 8");
		    	break;
			case "level_4.json":
		    	levelName.setText("Level 4 of 8");
		    	break;
			case "level_5.json":
		    	levelName.setText("Level 5 of 8");
		    	break;
			case "level_6.json":
		    	levelName.setText("Level 6 of 8");
		    	break;
			case "level_7.json":
		    	levelName.setText("Level 7 of 8");
		    	break;
			case "level_8.json":
		    	levelName.setText("Level 8 of 8");
		    	break;
    	}
    }
    
    /**
     * Handle resume button.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    public void handleResumeButton() throws IOException {
    	dungeonScreen.unPause();
    	dungeonScreen.start();
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
     * Handle home button.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    public void handleHomeButton() throws IOException {
    	(new StartScreen(stage)).start();
    }
    
    /**
     * Handle quit pause button.
     */
    @FXML
    public void handleQuitPauseButton() {
    	System.exit(0);
    }

}