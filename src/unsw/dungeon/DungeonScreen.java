package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonScreen {

    private Stage stage;
    private String title;
    private DungeonControllerLoader loader;
    private DungeonController controller;
    private Scene scene;

    public DungeonScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Dungeon Crawler";
        
        loader = new DungeonControllerLoader("advanced.json");
        controller = loader.loadController();
        controller.addStage(stage);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);

        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root);
        root.requestFocus();
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public DungeonController getController() {
        return controller;
    }
}