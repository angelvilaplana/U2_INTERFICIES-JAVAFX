package orihuel.vilaplana.angel.contenidors.vbox;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VBoxScene extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("HBox");
        VBox root = new VBox();
        root.setPadding(new Insets(20));
        root.setSpacing(20);

        Label label = new Label("Etiqueta VBOX");
        root.getChildren().add(label);

        Button bt1 = new Button("Button1 VBOX");
        bt1.setPrefHeight(125);
        bt1.setPrefWidth(125);
        root.getChildren().add(bt1);

        Button bt2 = new Button("Button2 VBOX");
        root.getChildren().add(bt2);

        TextField textF = new TextField("Text Field del VBOX");
        root.getChildren().add(textF);

        RadioButton radioB = new RadioButton("Radio Button VBOX");
        root.getChildren().add(radioB);

        CheckBox checkB = new CheckBox("Check Box VBOX");
        root.getChildren().add(checkB);

        Scene theScene = new Scene(root, 400, 400);
        stage.setScene(theScene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
