package travelapp.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import travelapp.*;
import java.util.*;

public class AttractionDetailsPage {

    public static Scene getScene(Stage stage, String attractionName, int id) {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(40));

        Text title = new Text(attractionName);
        title.setFont(new Font("Arial", 30));

        ImageView imageView = new ImageView();
        imageView.setFitHeight(300);
        imageView.setFitWidth(450);

        Text description = new Text();
        description.setWrappingWidth(600);
        description.setFont(new Font(16));

        Text reviews = new Text();
        reviews.setWrappingWidth(600);
        reviews.setFont(new Font(14));

        List<DBTest.Attraction> attraction = DBTest.getAttractions(id);
        for (DBTest.Attraction a : attraction) {
            
              imageView.setImage(new Image("file:" + a.getImageReference()));
                description.setText(a.getDescription());
                reviews.setText("Visits - " + a.getVisitCount());
            System.out.println("Attraction: " + a.getName() + " (" + a.getVisitCount() + " visits)");

        }

        Button backBtn = new Button("⬅ Back to Explore");
        backBtn.setStyle("-fx-background-color: #0077cc; -fx-text-fill: white;");
        backBtn.setOnAction(e -> stage.setScene(ExplorePage.getScene(stage)));

        layout.getChildren().addAll(title, imageView, description, reviews, backBtn);

        return new Scene(layout, 900, 700);
    }
}
