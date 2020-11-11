package orihuel.vilaplana.angel.animacions;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class RedCircle extends Circle {

    private int moveDX;

    private int moveDY;

    public RedCircle(double centerX, double centerY) {
        super(centerX, centerY, 5, Color.RED);
        moveDX = new Random().nextInt(2) == 0 ? 1 : -1;
        moveDY = new Random().nextInt(2) == 0 ? 1: -1;
    }

    public void move() {
        setCenterX(getCenterX() + moveDX);
        setCenterY(getCenterY() + moveDY);
    }

}
