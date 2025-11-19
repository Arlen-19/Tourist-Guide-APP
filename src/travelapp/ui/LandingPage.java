package travelapp.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LandingPage {

    public static Scene getScene(Stage stage) {
        // Background image
        Image bgImg = new Image("file:assets/home_bg.jpg", 1200, 800, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(
                bgImg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );

        StackPane root = new StackPane();
        root.setBackground(new Background(backgroundImage));

        // Add fallback gradient if no image
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #2193b0, #6dd5ed);");

        // Title + Tagline
        Label title = new Label("🌍 Tourist Guide");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 48));
        title.setTextFill(Color.WHITE);
        title.setEffect(new DropShadow(10, Color.DARKBLUE));

        Label tagline = new Label("Discover India’s Hidden Gems ✨");
        tagline.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 22));
        tagline.setTextFill(Color.LIGHTYELLOW);

        VBox titleBox = new VBox(10, title, tagline);
        titleBox.setAlignment(Pos.CENTER);

        // Center image
        ImageView indiaMap = new ImageView(new Image("file:assets/india_map.png"));
        indiaMap.setFitWidth(300);
        indiaMap.setPreserveRatio(true);
        indiaMap.setSmooth(true);

        // Buttons
        Button addBtn = new Button("Add Attractions");
        Button exploreBtn = new Button("Explore Attractions");
        Button exitBtn = new Button("Exit");

        String btnBase = "-fx-font-size: 18px; -fx-font-weight: bold; "
                + "-fx-background-radius: 25; -fx-padding: 10 30;";

        addBtn.setStyle(btnBase + "-fx-background-color: #6c0ae4ff; -fx-text-fill: white;");
        exploreBtn.setStyle(btnBase + "-fx-background-color: #0078D7; -fx-text-fill: white;");
        exitBtn.setStyle(btnBase + "-fx-background-color: #ff3b30; -fx-text-fill: white;");

        // Hover effects
        addBtn.setOnMouseEntered(e -> addBtn.setStyle(btnBase + "-fx-background-color: rgba(36, 101, 167, 1)ff; -fx-text-fill: white;"));
        addBtn.setOnMouseExited(e -> addBtn.setStyle(btnBase + "-fx-background-color: #6c0ae4ff; -fx-text-fill: white;"));
        exploreBtn.setOnMouseEntered(e -> exploreBtn.setStyle(btnBase + "-fx-background-color: #005bb5; -fx-text-fill: white;"));
        exploreBtn.setOnMouseExited(e -> exploreBtn.setStyle(btnBase + "-fx-background-color: #0078D7; -fx-text-fill: white;"));
        exitBtn.setOnMouseEntered(e -> exitBtn.setStyle(btnBase + "-fx-background-color: #cc2b1d; -fx-text-fill: white;"));
        exitBtn.setOnMouseExited(e -> exitBtn.setStyle(btnBase + "-fx-background-color: #ff3b30; -fx-text-fill: white;"));

        addBtn.setOnAction(e -> stage.setScene(AddAttractionPage.getScene(stage)));
        exploreBtn.setOnAction(e -> stage.setScene(ExplorePage.getScene(stage)));
        exitBtn.setOnAction(e -> stage.close());


        VBox buttonsBox = new VBox(15, addBtn, exploreBtn, exitBtn);
        buttonsBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox(40, titleBox, indiaMap, buttonsBox);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(50));

        root.getChildren().add(layout);

        return new Scene(root, 1000, 700);
    }
}
