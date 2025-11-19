package travelapp.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import travelapp.DBTest;

public class AddAttractionPage {

    public static Scene getScene(Stage stage) {
        VBox root = new VBox(20);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(30));

        Label title = new Label("➕ Add New Attraction");
        title.setFont(new Font("Arial", 26));

        // GridPane for form fields
        GridPane grid = new GridPane();
        grid.setVgap(15);
        grid.setHgap(20);
        grid.setAlignment(Pos.CENTER);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label categoryLabel = new Label("Category:");
        TextField categoryField = new TextField();

        Label locationLabel = new Label("Location:");
        TextField locationField = new TextField();

        Label descriptionLabel = new Label("Description:");
        TextArea descriptionArea = new TextArea();
        descriptionArea.setPrefRowCount(4);
        descriptionArea.setWrapText(true);

        Label imageLabel = new Label("Image Path:");
        TextField imageField = new TextField();

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(categoryLabel, 0, 1);
        grid.add(categoryField, 1, 1);
        grid.add(locationLabel, 0, 2);
        grid.add(locationField, 1, 2);
        grid.add(descriptionLabel, 0, 3);
        grid.add(descriptionArea, 1, 3);
        grid.add(imageLabel, 0, 4);
        grid.add(imageField, 1, 4);

        Button addBtn = new Button("Add Attraction");
        addBtn.setStyle("-fx-background-color: #0077cc; -fx-text-fill: white; -fx-font-size: 14px;");

        Label statusLabel = new Label();
        statusLabel.setFont(new Font(14));

        addBtn.setOnAction(e -> {
            String name = nameField.getText().trim();
            String category = categoryField.getText().trim();
            String location = locationField.getText().trim();
            String description = descriptionArea.getText().trim();
            String image = imageField.getText().trim();

            if (name.isEmpty() || category.isEmpty() || location.isEmpty() || description.isEmpty() || image.isEmpty()) {
                statusLabel.setText("⚠ All fields are required!");
            } else {
                boolean success = DBTest.addAttraction(name, category, location, description, image);
                if (success) {
                    statusLabel.setText("✅ Attraction added successfully!");
                    nameField.clear();
                    categoryField.clear();
                    locationField.clear();
                    descriptionArea.clear();
                    imageField.clear();
                } else {
                    statusLabel.setText("❌ Failed to add attraction.");
                }
            }
        });

        Button backBtn = new Button("⬅ Back to Explore");
        backBtn.setStyle("-fx-background-color: #cccccc; -fx-text-fill: black; -fx-font-size: 14px;");
        backBtn.setOnAction(e -> stage.setScene(LandingPage.getScene(stage)));

        root.getChildren().addAll(title, grid, addBtn, statusLabel, backBtn);

        return new Scene(root, 900, 700);
    }
}
