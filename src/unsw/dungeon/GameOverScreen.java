package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameOverScreen {

    private Stage stage;
    private String title;
    private GameOverController controller;
    private Scene scene;

    public GameOverScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Dungeon Crawler";

        controller = new GameOverController(stage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOverView.fxml"));
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

    public GameOverController getController() {
        return controller;
    }
}
