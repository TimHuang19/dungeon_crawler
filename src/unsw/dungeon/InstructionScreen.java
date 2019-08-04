package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InstructionScreen {
    /** The stage. */
    private Stage stage;
    
    /** The title. */
    private String title;
    
    /** The controller. */
    private InstructionController controller;
    
    /** The scene. */
    private Scene scene;

    /**
     * Instantiates a new start screen.
     *
     * @param stage 	The stage
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public InstructionScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Hide In Darkness";

        controller = new InstructionController(stage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InstructionView.fxml"));
        loader.setController(controller);

        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root);
    }

    /**
     * Start.
     */
    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
