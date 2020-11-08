package orihuel.vilaplana.angel.animacions;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Animacio extends Application {

    private Scene scene;
    private Group root;

    private final double WIDTH_SCENE = 400;
    private final double HEIGHT_SCENE = 400;

    private Circle blueCircle;
    private int livesBlue = 3;
    private AnimationTimer movePlayer;

    private final int NUMBER_RED_CIRCLES = 3;
    private List<Circle> redCircles;

    private boolean up, down, right, lest;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Animacions");
        root = new Group();

        blueCircle = new Circle(WIDTH_SCENE / 2, HEIGHT_SCENE / 2, 20, Color.BLUE);
        root.getChildren().add(blueCircle);

        redCircles = new ArrayList<>();
        addRedCircles();

        scene = new Scene(root, WIDTH_SCENE, HEIGHT_SCENE);
        setControlsPlayer();

        setMovePlayer();
        movePlayer.start();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void setControlsPlayer() {
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

    private void setMovePlayer() {
        movePlayer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double speed = 2;
                if (up) {
                    if (blueCircle.getCenterY() > (0 + blueCircle.getRadius())) {
                        blueCircle.setCenterY(blueCircle.getCenterY() - speed);
                    } else {
                        collisionWall();
                    }
                }
                if (down) {
                    if (blueCircle.getCenterY() < (HEIGHT_SCENE - blueCircle.getRadius())) {
                        blueCircle.setCenterY(blueCircle.getCenterY() + speed);
                    } else {
                        collisionWall();
                    }
                }
                if (right) {
                    if (blueCircle.getCenterX() < (WIDTH_SCENE - blueCircle.getRadius())) {
                        blueCircle.setCenterX(blueCircle.getCenterX() + speed);
                    } else {
                        collisionWall();
                    }
                }
                if (lest) {
                    if (blueCircle.getCenterX() > (0 + blueCircle.getRadius())) {
                        blueCircle.setCenterX(blueCircle.getCenterX() - speed);
                    } else {
                        collisionWall();
                    }
                }
            }
        };
    }

    private void collisionWall() {
        livesBlue--;

        if (livesBlue > 0) {
            blueCircle.setCenterX(WIDTH_SCENE / 2);
            blueCircle.setCenterY(HEIGHT_SCENE / 2);
            blueCircle.setRadius(blueCircle.getRadius() - 5);
        } else {
            movePlayer.stop();
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), blueCircle);
            fadeTransition.setFromValue(10);
            fadeTransition.setToValue(0);
            fadeTransition.play();
        }
    }

    private void addRedCircles() {
        while (NUMBER_RED_CIRCLES > redCircles.size()) {
            addRedCircle();
        }
    }

    private boolean checkIsNearCircle(Circle circleOrigin, Circle circleAux) {
        boolean isDXNear, isDYNear;
        double spacing = 2;

        if (circleOrigin.getCenterX() > circleAux.getCenterX()) {
            isDXNear = Math.abs(circleOrigin.getCenterX() - circleOrigin.getRadius() - circleAux.getCenterX()) < (circleOrigin.getRadius() * spacing);
        } else {
            isDXNear = Math.abs(circleOrigin.getCenterX() + circleOrigin.getRadius() - circleAux.getCenterX()) < (circleOrigin.getRadius() * spacing);
        }

        if (circleOrigin.getCenterY() > circleAux.getCenterY()) {
            isDYNear = Math.abs(circleOrigin.getCenterY() - circleOrigin.getRadius() - circleAux.getCenterY()) < (circleOrigin.getRadius() * spacing);
        } else {
            isDYNear = Math.abs(circleOrigin.getCenterY() + circleOrigin.getRadius() - circleAux.getCenterY()) < (circleOrigin.getRadius() * spacing);
        }

        return isDXNear && isDYNear;
    }

    private boolean checkIsNearRedCircles(Circle circleAux) {
        for (Circle redCircle : redCircles) {
            if (checkIsNearCircle(redCircle, circleAux)) {
                return true;
            }
        }

        return false;
    }

    private void addRedCircle() {
        int radiusCircleRed = 10;

        double maxDX = WIDTH_SCENE - (radiusCircleRed * 4);
        double minDX = radiusCircleRed * 4;
        double maxDY = HEIGHT_SCENE - (radiusCircleRed * 4);
        double minDY = radiusCircleRed * 4;

        int dx, dy;
        boolean isNearCircles;
        Circle redCircle;
        do {
            dx = (int) Math.floor(Math.random() * (maxDX - minDX + 1) + (minDX));
            dy = (int) Math.floor(Math.random() * (maxDY - minDY + 1) + (minDY));
            redCircle = new Circle(dx, dy, 10, Color.RED);
            boolean isNearBlueCircle = checkIsNearCircle(blueCircle, redCircle);
            boolean isNearRedCircles = checkIsNearRedCircles(redCircle);
            isNearCircles = isNearBlueCircle || isNearRedCircles;
        } while (isNearCircles);

        root.getChildren().add(redCircle);
        redCircles.add(redCircle);
        redCircle.toBack();
    }

    public static void main(String[] args) {
        launch();
    }

}
