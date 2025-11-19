package travelapp.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Scene landingScene = LandingPage.getScene(primaryStage);

        primaryStage.setTitle("Tourist Guide");
        primaryStage.setScene(landingScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
