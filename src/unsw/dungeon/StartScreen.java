package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartScreen {

    private Stage stage;
    private String title;
    private StartController controller;
    private Scene scene;

    public StartScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Dungeon Crawler";

        controller = new StartController(stage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StartView.fxml"));
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
