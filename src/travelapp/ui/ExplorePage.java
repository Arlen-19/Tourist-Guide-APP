package travelapp.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import java.util.*;
import travelapp.*;

public class ExplorePage {

    public static Scene getScene(Stage stage) {
        VBox layout = new VBox(25);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(30));

        Text title = new Text("🌍 Explore Beautiful Attractions of India");
        title.setFont(new Font("Arial", 28));
        title.setFill(Color.DARKBLUE);

        TilePane tilePane = new TilePane();
        tilePane.setHgap(40);
        tilePane.setVgap(40);
        tilePane.setAlignment(Pos.CENTER);
        tilePane.setPadding(new Insets(30));

        // Get list of tourists
        List<DBTest.Tourist> tourists = DBTest.getTourists();

        // // Access each tourist's data
        // for (DBTest.Tourist t : tourists) {
        //     System.out.println("Name: " + t.getName());
        //     System.out.println("Email: " + t.getEmail());
        //     System.out.println("Role: " + t.getRole());
        //     System.out.println("--------------------");
        // }

        // Get list of attractions
        List<DBTest.Attraction> attractions = DBTest.getAttractions();
        for (DBTest.Attraction a : attractions) {
             tilePane.getChildren().add(createAttractionCard(stage, a.getName()+"-"+a.getVisitCount(), a.getImageReference(), a.getId()));
            System.out.println("Attraction: " + a.getName() + " (" + a.getVisitCount() + " visits)");
        }

        // // Get visits
        // List<DBTest.Visit> visits = DBTest.getVisits();
        // for (DBTest.Visit v : visits) {
        //     System.out.println(v.getTouristName() + " visited " + v.getAttractionName() + " on " + v.getVisitDate());
        // }

        // Wrap TilePane in ScrollPane
    ScrollPane scrollPane = new ScrollPane(tilePane);
    scrollPane.setFitToWidth(true);  // Make TilePane width adapt to ScrollPane
    scrollPane.setPannable(true);    // Allow dragging with mouse
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        Button homeBtn = new Button("🏠 Home");
        homeBtn.setStyle("-fx-background-color: #0077cc; -fx-text-fill: white; -fx-font-size: 14px;");
        homeBtn.setOnAction(e -> stage.setScene(LandingPage.getScene(stage)));

        layout.getChildren().addAll(title, scrollPane, homeBtn);

        return new Scene(layout, 1000, 700);
    }

    private static VBox createAttractionCard(Stage stage, String name, String imagePath, int id) {
        ImageView img = new ImageView(new Image("file:" + imagePath));
        img.setFitWidth(220);
        img.setFitHeight(160);
        img.setPreserveRatio(true);

        Text nameText = new Text(name);
        nameText.setFont(new Font("Arial", 18));
        nameText.setFill(Color.DARKBLUE);

        VBox card = new VBox(10, img, nameText);
        card.setAlignment(Pos.CENTER);
        card.setStyle("-fx-background-color: #f9f9f9; -fx-border-color: #cccccc; -fx-border-radius: 10; -fx-background-radius: 10; -fx-padding: 10;");
        card.setOnMouseEntered(e -> card.setStyle("-fx-background-color: #e8f4ff; -fx-border-color: #0077cc; -fx-border-radius: 10; -fx-background-radius: 10; -fx-padding: 10;"));
        card.setOnMouseExited(e -> card.setStyle("-fx-background-color: #f9f9f9; -fx-border-color: #cccccc; -fx-border-radius: 10; -fx-background-radius: 10; -fx-padding: 10;"));

        card.setOnMouseClicked(e -> stage.setScene(AttractionDetailsPage.getScene(stage, name, id)));

        return card;
    }
}
