package orihuel.vilaplana.angel.animacions;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainScene extends Application {

    private Group root;
    private AnimationTimer timer;

    private final double WIDTH_SCENE = 400;
    private final double HEIGHT_SCENE = 400;

    private BlueCircle blueCircle;
    private CountDown countDownEatRedCircles;
    private Label labelBlueLives;
    private Label labelSecondsRemaining;

    private final int NUMBER_RED_CIRCLES = 3;
    private List<Circle> redCircles;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Animacions");
        root = new Group();

        blueCircle = new BlueCircle(this);
        root.getChildren().add(blueCircle);

        redCircles = new ArrayList<>();
        addRedCircles();

        labelBlueLives = new Label("Vides: " + blueCircle.getLives());
        labelBlueLives.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        labelBlueLives.toFront();
        labelBlueLives.setLayoutX(10);
        labelBlueLives.setLayoutY(10);
        root.getChildren().add(labelBlueLives);

        countDownEatRedCircles = new CountDown(5);
        countDownEatRedCircles.start();
        labelSecondsRemaining = new Label("Temps restant: " + countDownEatRedCircles.getSecond());
        labelSecondsRemaining.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        labelSecondsRemaining.toFront();
        labelSecondsRemaining.setLayoutX(WIDTH_SCENE / 1.65);
        labelSecondsRemaining.setLayoutY(10);
        root.getChildren().add(labelSecondsRemaining);

        Scene scene = new Scene(root, WIDTH_SCENE, HEIGHT_SCENE);
        blueCircle.setControls(scene);

        setTimer();
        timer.start();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void setTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                blueCircle.move();
                eatReadCircles();

                if (!blueCircle.isLive()) {
                    countDownEatRedCircles.stop();
                    timer.stop();
                }

                if (countDownEatRedCircles.isFinished()) {
                    blueCircle.removeLive();
                    countDownEatRedCircles.restart();
                }

                labelBlueLives.setText("Vides: " + blueCircle.getLives());
                labelSecondsRemaining.setText("Temps restant: " + countDownEatRedCircles.getSecond());
            }
        };
    }

    public void removeRedCircles() {
        for (Circle redCircle : redCircles) {
            root.getChildren().remove(redCircle);
        }

        redCircles = new ArrayList<>();
    }

    private void eatReadCircles() {
        for (Circle redCircle : redCircles) {
            if (blueCircle.getBoundsInLocal().contains(redCircle.getCenterX(), redCircle.getCenterY(),
                                                   redCircle.getRadius() * .6, redCircle.getRadius() * .6)) {
                root.getChildren().remove(redCircle);
                redCircles.remove(redCircle);
                blueCircle.addLive();
                return;
            }
        }
    }

    public void addRedCircles() {
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

    public void addRedCircle() {
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

    public double getWidthScene() {
        return WIDTH_SCENE;
    }

    public double getHeightScene() {
        return HEIGHT_SCENE;
    }

    public void restartCountDown() {
        countDownEatRedCircles.restart();
    }

    public static void main(String[] args) {
        launch();
    }

}
