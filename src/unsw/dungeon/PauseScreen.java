package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PauseScreen {

    private Stage stage;
    private String title;
    private PauseController controller;
    private Scene scene;

    public PauseScreen(Stage stage, String fileName, DungeonScreen dungeonScreen) throws IOException {
        this.stage = stage;
        title = "Dungeon Crawler";

        controller = new PauseController(stage, fileName, dungeonScreen);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PauseView.fxml"));
        loader.setController(controller);

        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root);
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

}