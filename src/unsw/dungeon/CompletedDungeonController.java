package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * The Class CompletedDungeonController.
 */
public class CompletedDungeonController {
	
    /** The home end button. */
    @FXML
    private Button homeEndButton;
    
    /** The quit end button. */
    @FXML
    private Button quitEndButton;
    
    /** The stage. */
    private Stage stage;
        
    /**
     * Instantiates a new completed dungeon controller.
     *
     * @param stage 	The stage
     */
    public CompletedDungeonController(Stage stage) {
    	this.stage = stage;
    }
    
    /**
     * Handle home end button.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    public void handleHomeEndButton() throws IOException {
    	(new StartScreen(stage)).start();
    }
    
    /**
     * Handle quit end button.
     */
    @FXML
    public void handleQuitEndButton() {
    	System.exit(0);
    }

}