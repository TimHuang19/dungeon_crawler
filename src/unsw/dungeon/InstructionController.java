package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InstructionController {

    /** The back button. */
    @FXML
    private Button backInstruction;
    
    /** The stage. */
    private Stage stage;
    
    
    /**
     * Instantiates a new start controller.
     *
     * @param stage 	The stage
     */
    public InstructionController(Stage stage) {
    	this.stage = stage;
    }
    
    /**
     * Handle back instruction button.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    public void handleBackInstructionButton() throws IOException {
    	(new StartScreen(stage)).start();
	}
}
