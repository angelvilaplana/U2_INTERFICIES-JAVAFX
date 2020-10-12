package orihuel.vilaplana.angel.contenidors.flow_pane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FlowPaneScene extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Flow Pane");
        FlowPane root = new FlowPane();

        for (int i = 0; i < 7; i++) {
            VBox box = box();
            box.setStyle("-fx-border-color: black");
            root.getChildren().add(box);
        }

        Scene theScene = new Scene(root, 900, 800);
        stage.setScene(theScene);
        stage.setResizable(false);
        stage.show();
    }

    private static VBox box() {
        VBox box = new VBox();
        box.setPadding(new Insets(20));
        box.setSpacing(20);

        Label label = new Label("Etiqueta VBOX");
        box.getChildren().add(label);

        Button bt1 = new Button("Button1 VBOX");
        bt1.setPrefHeight(125);
        bt1.setPrefWidth(125);
        box.getChildren().add(bt1);

        Button bt2 = new Button("Button2 VBOX");
        box.getChildren().add(bt2);

        TextField textF = new TextField("Text Field del VBOX");
        box.getChildren().add(textF);

        RadioButton radioB = new RadioButton("Radio Button VBOX");
        box.getChildren().add(radioB);

        CheckBox checkB = new CheckBox("Check Box VBOX");
        box.getChildren().add(checkB);

        return box;
    }

    public static void main(String[] args) {
        launch();
    }

}
