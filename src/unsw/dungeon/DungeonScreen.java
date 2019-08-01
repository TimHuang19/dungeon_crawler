package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Class DungeonScreen.
 */
public class DungeonScreen {

    /** The stage. */
    private Stage stage;
    
    /** The title. */
    private String title;
    
    /** The loader. */
    private DungeonControllerLoader loader;
    
    /** The controller. */
    private DungeonController controller;
    
    /** The scene. */
    private Scene scene;

    /**
     * Instantiates a new dungeon screen.
     *
     * @param stage the stage
     * @param fileName the file name
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public DungeonScreen(Stage stage, String fileName) throws IOException {
        this.stage = stage;
        title = "Hide In Darkness";
        
        loader = new DungeonControllerLoader(fileName);
        controller = loader.loadController();
        controller.addStage(stage);
        controller.addName(fileName);
        controller.addPauseScreen(this);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);

        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root);
        root.requestFocus();
    }

    /**
     * Start.
     */
    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Un pause.
     */
    public void unPause() {
    	controller.unPause();
    }

}