package orihuel.vilaplana.angel.contenidors.border_pane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BorderPaneScene extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("BorderPane");
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15, 20, 10, 10));

        Button btnTop = new Button("Element situat en la part superior (Top)");
        btnTop.setPadding(new Insets(10));
        BorderPane.setMargin(btnTop, new Insets(10));
        root.setTop(btnTop);

        Button btnLeft = new Button("Element situat en la part esquerra (Left)");
        btnLeft.setPadding(new Insets(10));
        BorderPane.setMargin(btnLeft, new Insets(10));
        root.setLeft(btnLeft);

        Button btnRight = new Button("Element situat en la dreta (Right)");
        btnRight.setPadding(new Insets(10));
        BorderPane.setMargin(btnRight, new Insets(10));
        root.setRight(btnRight);

        Button btnCenter = new Button("Element situat en el centre (Center)");
        btnCenter.setPadding(new Insets(10));
        BorderPane.setMargin(btnCenter, new Insets(10));
        root.setCenter(btnCenter);

        Button btnBottom = new Button("Element situat en la part inferior (Bottom)");
        btnBottom.setPadding(new Insets(10));
        BorderPane.setMargin(btnBottom, new Insets(10));
        root.setBottom(btnBottom);

        Scene theScene = new Scene(root, 800, 500);
        stage.setScene(theScene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
