package orihuel.vilaplana.angel.contenidors.grid_pane;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;

public class GridPaneScene extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("GridPane");
        GridPane root = new GridPane();
        root.setPadding(new Insets(20, 0, 20, 20));
        root.setHgap(7);
        root.setVgap(7);

        URL urlImage = GridPaneScene.class.getResource("SteveMinecraft.png");
        ImageView image = new ImageView(String.valueOf(urlImage));
        image.setFitWidth(120);
        image.setFitHeight(120);
        GridPane.setColumnSpan(image, 7);
        GridPane.setRowSpan(image, 10);
        root.add(image, 6, 1);

        Label lbName = new Label("Nom:");
        GridPane.setHalignment(lbName, HPos.RIGHT);
        root.add(lbName, 0, 2);
        TextField tfName = new TextField();
        root.add(tfName, 3, 2);

        Label lbLastName = new Label("Cognom:");
        GridPane.setHalignment(lbLastName, HPos.RIGHT);
        root.add(lbLastName, 0, 5);
        TextField tfLastName = new TextField();
        root.add(tfLastName, 3, 5);

        Label lbCity = new Label("Ciutat:");
        GridPane.setHalignment(lbCity, HPos.RIGHT);
        root.add(lbCity, 0, 8);
        TextField tfCity = new TextField();
        root.add(tfCity, 3, 8);

        Label lbAddress = new Label("Direcció:");
        GridPane.setHalignment(lbAddress, HPos.RIGHT);
        root.add(lbAddress, 0, 11);
        TextField tfAddressA = new TextField();
        GridPane.setColumnSpan(tfAddressA, 8);
        root.add(tfAddressA, 3, 11);
        TextField tfAddressB = new TextField();
        tfAddressB.setPrefWidth(0);
        GridPane.setColumnSpan(tfAddressB, 2);
        root.add(tfAddressB, 12, 11);

        Label lbDescription = new Label("Descripció:");
        GridPane.setHalignment(lbDescription, HPos.RIGHT);
        root.add(lbDescription, 0, 15);
        TextArea taDescription = new TextArea();
        taDescription.setWrapText(true);
        taDescription.setPrefWidth(0);
        taDescription.setPrefHeight(0);
        GridPane.setColumnSpan(taDescription, 11);
        GridPane.setRowSpan(taDescription, 10);
        root.add(taDescription, 3, 14);

        Scene theScene = new Scene(root, 450, 360);
        stage.setScene(theScene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
