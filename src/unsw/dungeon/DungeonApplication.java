package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The Class DungeonApplication.
 */
public class DungeonApplication extends Application {

    /**
     * Start.
     *
     * @param primaryStage 	The primary stage
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
    	StartScreen startScreen = new StartScreen(primaryStage);
    	    	    	
    	startScreen.start();
    }

    /**
     * The main method.
     *
     * @param args 	The arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}