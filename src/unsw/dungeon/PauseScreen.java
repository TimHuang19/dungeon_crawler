package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Class PauseScreen.
 */
public class PauseScreen {

    /** The stage. */
    private Stage stage;
    
    /** The title. */
    private String title;
    
    /** The controller. */
    private PauseController controller;
    
    /** The scene. */
    private Scene scene;

    /**
     * Instantiates a new pause screen.
     *
     * @param stage the stage
     * @param fileName the file name
     * @param dungeonScreen the dungeon screen
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public PauseScreen(Stage stage, String fileName, DungeonScreen dungeonScreen) throws IOException {
        this.stage = stage;
        title = "Hide In Darkness";

        controller = new PauseController(stage, fileName, dungeonScreen);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PauseView.fxml"));
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