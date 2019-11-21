package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        GameStage gameStage = new GameStage(primaryStage);
        gameStage.playGame();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
