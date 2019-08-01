package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Class GameOverScreen.
 */
public class GameOverScreen {

    /** The stage. */
    private Stage stage;
    
    /** The title. */
    private String title;
    
    /** The controller. */
    private GameOverController controller;
    
    /** The scene. */
    private Scene scene;

    /**
     * Instantiates a new game over screen.
     *
     * @param stage the stage
     * @param fileName the file name
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public GameOverScreen(Stage stage, String fileName) throws IOException {
        this.stage = stage;
        title = "Hide In Darkness";

        controller = new GameOverController(stage, fileName);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOverView.fxml"));
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
