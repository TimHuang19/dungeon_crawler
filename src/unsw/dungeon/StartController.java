package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * The Class StartController.
 */
public class StartController {

    /** The start button. */
    @FXML
    private Button startButton;
    
    /** The level button. */
    @FXML
    private Button levelButton;
    
    /** The quit button. */
    @FXML
    private Button quitButton;
    
    /** The instruction button. */
    @FXML
    private Button instructionButton;
    
    /** The stage. */
    private Stage stage;
    
    
    /**
     * Instantiates a new start controller.
     *
     * @param stage 	The stage
     */
    public StartController(Stage stage) {
    	this.stage = stage;
    }
    
    /**
     * Handle start button.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    public void handleStartButton() throws IOException {
        (new DungeonScreen(stage, "level_1.json")).start();
    }
    
    /**
     * Handle level button.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    public void handleLevelButton() throws IOException {
    	(new LevelSelectScreen(stage)).start();
    }
    
    /**
     * Handle level button.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    public void handleInstructionButton() throws IOException {
    	(new InstructionScreen(stage)).start();
    }
    
    /**
     * Handle quit button.
     */
    @FXML
    public void handleQuitButton() {
    	System.exit(0);
    }
    
}