package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NextLevelScreen {

    private Stage stage;
    private String title;
    private NextLevelController controller;
    private Scene scene;

    public NextLevelScreen(Stage stage, String fileName) throws IOException {
        this.stage = stage;
        title = "Dungeon Crawler";

        controller = new NextLevelController(stage, fileName);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NextLevelView.fxml"));
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

    public NextLevelController getController() {
        return controller;
    }
}
