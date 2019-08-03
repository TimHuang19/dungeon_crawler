package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Class CompletedDungeonScreen.
 */
public class CompletedDungeonScreen {

    /** The stage. */
    private Stage stage;
    
    /** The title. */
    private String title;
    
    /** The controller. */
    private CompletedDungeonController controller;
    
    /** The scene. */
    private Scene scene;

    /**
     * Instantiates a new completed dungeon screen.
     *
     * @param stage 	The stage
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public CompletedDungeonScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Hide In Darkness";

        controller = new CompletedDungeonController(stage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CompletedDungeonView.fxml"));
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
