import GameControllers.GameEngine;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameEngine gameEngine = new GameEngine(primaryStage);

        Scene scene = new Scene(gameEngine.getDrawSpace());

        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
