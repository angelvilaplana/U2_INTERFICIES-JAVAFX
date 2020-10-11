package orihuel.vilaplana.angel.activitats;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Escacs extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Escacs");
        Group root = new Group();

        int recSize = 50;

        for (int i = 0; i < 8; i++) {
            int posX = recSize * i;
            for (int j = 0; j < 8; j++) {
                int posY = recSize * j;
                Rectangle rec = new Rectangle(posX, posY, recSize, recSize);
                if ((i + j) % 2 == 0) {
                    rec.setFill(Color.rgb(130, 165, 210));
                } else {
                    rec.setFill(Color.rgb(255, 255, 255));
                }
                root.getChildren().add(rec);
            }
        }

        Scene theScene = new Scene(root, 400, 400);
        stage.setScene(theScene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
