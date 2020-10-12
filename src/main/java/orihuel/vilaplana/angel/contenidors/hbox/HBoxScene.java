package orihuel.vilaplana.angel.contenidors.hbox;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HBoxScene extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("HBox");
        HBox root = new HBox();
        root.setPadding(new Insets(20));
        root.setSpacing(10);

        Label label = new Label("Etiqueta HBOX");
        root.getChildren().add(label);

        Button bt1 = new Button("Button1 HBOX");
        bt1.setPrefHeight(125);
        bt1.setPrefWidth(125);
        root.getChildren().add(bt1);

        Button bt2 = new Button("Button2 HBOX");
        root.getChildren().add(bt2);

        TextField textF = new TextField("Text Field del HBOX");
        root.getChildren().add(textF);

        RadioButton radioB = new RadioButton("Radio Button HBOX");
        root.getChildren().add(radioB);

        CheckBox checkB = new CheckBox("Check Box HBOX");
        root.getChildren().add(checkB);

        Scene theScene = new Scene(root, 870, 200);
        stage.setScene(theScene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
