package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * The Class LevelSelectController.
 */
public class LevelSelectController {
	
    /** The first level button. */
    @FXML
    private Button oneButton;
    
    /** The second level button. */
    @FXML
    private Button twoButton;
    
    /** The third level button. */
    @FXML
    private Button threeButton;
    
    /** The fourth level button. */
    @FXML
    private Button fourButton;
    
    /** The fifth level button. */
    @FXML
    private Button fiveButton;
    
    /** The sixth level button. */
    @FXML
    private Button sixButton;
    
    /** The seventh level button. */
    @FXML
    private Button sevenButton;
    
    /** The eighth level button. */
    @FXML
    private Button eightButton;
    
    /** The back button. */
    @FXML
    private Button backButton;
    
    /** The stage. */
    private Stage stage;
    
    
    /**
     * Instantiates a new LevelSelect controller.
     *
     * @param stage 	The stage
     */
    public LevelSelectController(Stage stage) {
    	this.stage = stage;
    }
    
    /**
     * Handle one button.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    public void handleOneButton() throws IOException {
        (new DungeonScreen(stage, "level_1.json")).start();
    }
    
    /**
     * Handle two button.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    public void handleTwoButton() throws IOException {
        (new DungeonScreen(stage, "level_2.json")).start();
    }
    
    /**
     * Handle three button.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    public void handleThreeButton() throws IOException {
        (new DungeonScreen(stage, "level_3.json")).start();
    }
    
    /**
     * Handle four button.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    public void handleFourButton() throws IOException {
        (new DungeonScreen(stage, "level_4.json")).start();
    }
    
    /**
     * Handle five button.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    public void handleFiveButton() throws IOException {
        (new DungeonScreen(stage, "level_5.json")).start();
    }
    
    /**
     * Handle six button.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    public void handleSixButton() throws IOException {
        (new DungeonScreen(stage, "level_6.json")).start();
    }
    
    /**
     * Handle seven button.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    public void handleSevenButton() throws IOException {
        (new DungeonScreen(stage, "level_7.json")).start();
    }
    
    /**
     * Handle eight button.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    public void handleEightButton() throws IOException {
        (new DungeonScreen(stage, "level_8.json")).start();
    }
    
    /**
     * Handle back button.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    public void handleBackButton() throws IOException {
        (new StartScreen(stage)).start();
    }
}
