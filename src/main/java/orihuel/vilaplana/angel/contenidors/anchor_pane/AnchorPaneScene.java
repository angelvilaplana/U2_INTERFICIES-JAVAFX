package orihuel.vilaplana.angel.contenidors.anchor_pane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AnchorPaneScene extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("AnchorPane");
        AnchorPane root = new AnchorPane();
        root.setPadding(new Insets(15, 20, 10, 10));

        Button bt1 = new Button("Botó 1: anclat a: Top + Left");
        bt1.setPadding(new Insets(10));
        AnchorPane.setTopAnchor(bt1, (double) 40);
        AnchorPane.setLeftAnchor(bt1, (double) 50);
        root.getChildren().add(bt1);

        Button bt2 = new Button("Botó 2: anclat a: Top + Left + Right");
        bt2.setPadding(new Insets(10));
        AnchorPane.setTopAnchor(bt2, (double) 90);
        AnchorPane.setLeftAnchor(bt2, (double) 10);
        AnchorPane.setRightAnchor(bt2, (double) 320);
        root.getChildren().add(bt2);

        Button bt3 = new Button("Botó 3: anclat a: Top + Right");
        bt3.setPadding(new Insets(10));
        AnchorPane.setTopAnchor(bt3, (double) 100);
        AnchorPane.setRightAnchor(bt3, (double) 20);
        root.getChildren().add(bt3);

        Button bt4 = new Button("Botó 4: anclat a: Top + Left + Right + Bottom");
        bt4.setPadding(new Insets(10));
        AnchorPane.setTopAnchor(bt4, (double) 150);
        AnchorPane.setLeftAnchor(bt4, (double) 40);
        AnchorPane.setRightAnchor(bt4, (double) 50);
        AnchorPane.setBottomAnchor(bt4, (double) 45);
        root.getChildren().add(bt4);

        Scene theScene = new Scene(root, 800, 300);
        stage.setScene(theScene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
