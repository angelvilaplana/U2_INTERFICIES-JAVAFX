package orihuel.vilaplana.angel.activitats;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.stream.Stream;

public class EscacsListeners extends Application implements EventHandler<MouseEvent> {

    private Label indicatorLabel;

    private Rectangle lastClickRectangle;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Escacs Listeners");
        Group board = new Group();

        int recSize = 50;

        for (int i = 1; i <= 8; i++) {
            int posX = (recSize + 3) * i;
            for (int j = 1; j <= 8; j++) {
                int posY = (recSize + 3) * j;
                Rectangle rec = new Rectangle(posX, posY, recSize, recSize);
                rec.setStrokeWidth(3);
                squareColor(rec, posX, posY);
                rec.setId(i + " - " + j);
                rec.setOnMouseClicked(this);
                board.getChildren().add(rec);
            }
        }

        Label fixedIndicatorLabel = new Label("Posición: ");
        fixedIndicatorLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20));

        indicatorLabel = new Label(".....");
        indicatorLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20));

        HBox indicatorBox = new HBox(fixedIndicatorLabel, indicatorLabel);
        indicatorBox.setAlignment(Pos.CENTER);
        indicatorBox.setTranslateY(15);

        VBox root = new VBox(board, indicatorBox);

        Scene theScene = new Scene(root, 423, 475);
        stage.setScene(theScene);
        stage.setResizable(false);
        stage.show();
    }

    public void squareColor(Rectangle rec, int x, int y) {
        if ((x + y) % 2 == 0) {
            Color color = Color.rgb(130, 165, 210);
            rec.setFill(color);
            rec.setStroke(color);
        } else {
            Color color = Color.rgb(255, 255, 255);
            rec.setFill(color);
            rec.setStroke(color);
        }
    }

    @Override
    public void handle(MouseEvent event) {
        if (lastClickRectangle != null) {
            int[] position = Stream.of(lastClickRectangle.getId().split(" - "))
                    .mapToInt(Integer::parseInt).toArray();
            squareColor(lastClickRectangle, position[1], position[0]);
        }

        Rectangle rec = (Rectangle) event.getSource();
        rec.setStroke(Color.RED);
        indicatorLabel.setText(rec.getId());
        lastClickRectangle = rec;
    }

    public static void main(String[] args) {
        launch();
    }

}
