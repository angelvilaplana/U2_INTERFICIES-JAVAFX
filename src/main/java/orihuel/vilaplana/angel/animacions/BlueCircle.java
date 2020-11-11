package orihuel.vilaplana.angel.animacions;

import javafx.animation.FadeTransition;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class BlueCircle extends Circle {

    private MainScene mainScene;

    private int blueLives;

    private boolean up, down, right, lest;

    public BlueCircle(MainScene mainScene) {
        super(mainScene.getWidthScene() / 2, mainScene.getHeightScene() / 2, 20, Color.BLUE);
        blueLives = 3;
    }

    private void setControls() {
        mainScene.getScene().setOnKeyPressed(keyEvent ->
        {
            if ((keyEvent.getCode() == KeyCode.UP) || (keyEvent.getCode() == KeyCode.W)) {
                up = true;
            } else if ((keyEvent.getCode() == KeyCode.DOWN) || (keyEvent.getCode() == KeyCode.S)) {
                down = true;
            } else if ((keyEvent.getCode() == KeyCode.LEFT) || (keyEvent.getCode() == KeyCode.A)) {
                lest = true;
            } else if ((keyEvent.getCode() == KeyCode.RIGHT) || (keyEvent.getCode() == KeyCode.D)) {
                right = true;
            }
        });

        mainScene.getScene().setOnKeyReleased(keyEvent ->
        {
            if ((keyEvent.getCode() == KeyCode.UP) || (keyEvent.getCode() == KeyCode.W)) {
                up = false;
            } else if ((keyEvent.getCode() == KeyCode.DOWN) || (keyEvent.getCode() == KeyCode.S)) {
                down = false;
            } else if (((keyEvent.getCode() == KeyCode.LEFT) || (keyEvent.getCode() == KeyCode.A))) {
                lest = false;
            } else if (((keyEvent.getCode() == KeyCode.RIGHT) || (keyEvent.getCode() == KeyCode.D))) {
                right = false;
            }
        });
    }

    private void move() {
        double speed = 2;

        if (up) {
            if (getCenterY() > (0 + getRadius())) {
                setCenterY(getCenterY() - speed);
            } else {
                collisionWall();
            }
        }
        if (down) {
            if (getCenterY() < (mainScene.getHeightScene() - getRadius())) {
                setCenterY(getCenterY() + speed);
            } else {
                collisionWall();
            }
        }
        if (right) {
            if (getCenterX() < (mainScene.getWidthScene() - getRadius())) {
                setCenterX(getCenterX() + speed);
            } else {
                collisionWall();
            }
        }
        if (lest) {
            if (getCenterX() > (0 + getRadius())) {
                setCenterX(getCenterX() - speed);
            } else {
                collisionWall();
            }
        }
    }

    private void collisionWall() {
        removeLive();

        if (blueLives > 0) {
            setCenterX(mainScene.getWidthScene() / 2);
            setCenterY(mainScene.getHeightScene() / 2);
        }
    }

    private void removeLive() {
        blueLives--;

        if (blueLives > 0) {
            setRadius(getRadius() - 5);
        } else {
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), this);
            fadeTransition.setFromValue(10);
            fadeTransition.setToValue(0);
            fadeTransition.play();
        }
    }

}
