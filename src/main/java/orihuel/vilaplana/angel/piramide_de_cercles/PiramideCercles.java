package orihuel.vilaplana.angel.piramide_de_cercles;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class PiramideCercles extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Piramide Cercles");
        Group root = new Group();

        int widthScreen = 550;
        int heightScreen = 500;

        for (int i = 0; i < 8; i++) {
            for (int j = i; j < 8; j++ ) {
                int actualX = ((widthScreen / 2) - (j * 30)) + (i * 60);
                int actualY = (100 + (j * 40));
                Circle c = new Circle(actualX, actualY, 20);
                c.setFill(Color.rgb(190, 19, 99));
                c.setStroke(Color.BLACK);
                c.setStrokeWidth(3);
                root.getChildren().add(c);
            }
        }

        Scene theScene = new Scene(root, widthScreen, heightScreen);
        stage.setScene(theScene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
