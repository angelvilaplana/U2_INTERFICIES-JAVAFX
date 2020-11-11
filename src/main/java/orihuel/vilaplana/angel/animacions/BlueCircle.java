package orihuel.vilaplana.angel.animacions;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class BlueCircle extends Circle {

    private final MainScene mainScene;

    private int lives;

    private boolean up, down, right, lest;

    public BlueCircle(MainScene mainScene) {
        super(mainScene.getWidthScene() / 2, mainScene.getHeightScene() / 2, 20, Color.BLUE);
        this.mainScene = mainScene;
        this.lives = 3;
    }

    public void setControls(Scene scene) {
        scene.setOnKeyPressed(keyEvent ->
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

        scene.setOnKeyReleased(keyEvent ->
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

    public void move() {
        double speed = 2;

        if (up) {
            if (getCenterY() > (0 + getRadius())) {
                setCenterY(getCenterY() - speed);
            } else {
                removeLive();
            }
        }
        if (down) {
            if (getCenterY() < (mainScene.getHeightScene() - getRadius())) {
                setCenterY(getCenterY() + speed);
            } else {
                removeLive();
            }
        }
        if (right) {
            if (getCenterX() < (mainScene.getWidthScene() - getRadius())) {
                setCenterX(getCenterX() + speed);
            } else {
                removeLive();
            }
        }
        if (lest) {
            if (getCenterX() > (0 + getRadius())) {
                setCenterX(getCenterX() - speed);
            } else {
                removeLive();
            }
        }
    }

    public void addLive() {
        lives++;
        setRadius(getRadius() + 5);
        mainScene.restartCountDown();
        mainScene.addRedCircle();
    }

    public void removeLive() {
        lives--;
        mainScene.restartCountDown();

        if (isLive()) {
            mainScene.removeRedCircles();
            setCenterX(mainScene.getWidthScene() / 2);
            setCenterY(mainScene.getHeightScene() / 2);
            mainScene.addRedCircles();
            setRadius(getRadius() - 5);
        } else {
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), this);
            fadeTransition.setFromValue(10);
            fadeTransition.setToValue(0);
            fadeTransition.play();
        }
    }

    public int getLives() {
        return lives;
    }

    public boolean isLive() {
        return lives > 0;
    }

}
