package orihuel.vilaplana.angel.animacions;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import orihuel.vilaplana.angel.animacions.models.BlueCircle;
import orihuel.vilaplana.angel.animacions.models.CountDown;
import orihuel.vilaplana.angel.animacions.models.RedCircle;

import java.util.ArrayList;
import java.util.List;

public class MainScene extends Application {

    private Stage primaryStage;
    private Scene scene;
    private Group root;
    private AnimationTimer timer;

    private final double WIDTH_SCENE = 400;
    private final double HEIGHT_SCENE = 400;

    private BlueCircle blueCircle;
    private CountDown countDownEatRedCircles;
    private Label labelBlueLives;
    private Label labelSecondsRemaining;

    private final int NUMBER_RED_CIRCLES = 3;
    private List<RedCircle> redCircles;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("Animacions");
        root = new Group();
        scene = new Scene(root, WIDTH_SCENE, HEIGHT_SCENE);

        startMenu();
        //startGame();

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void startMenu() {
        VBox box = new VBox(20);
        box.setPrefHeight(HEIGHT_SCENE);
        box.setPrefWidth(WIDTH_SCENE);
        box.setAlignment(Pos.CENTER);
        Label title = new Label("Joc Animacions");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
        box.getChildren().add(title);

        Button btnPlay = new Button("Jugar");
        btnPlay.setPrefWidth(150);
        box.getChildren().add(btnPlay);
        btnPlay.setOnMouseClicked(e -> startAnimation());

        Button btnExit = new Button("Eixir");
        btnExit.setPrefWidth(150);
        btnExit.setOnMouseClicked(e -> primaryStage.close());
        box.getChildren().add(btnExit);

        root.getChildren().add(box);
    }

    private void endMenu() {
        VBox box = new VBox(20);
        box.setPrefHeight(HEIGHT_SCENE);
        box.setPrefWidth(WIDTH_SCENE);
        box.setAlignment(Pos.CENTER);
        Label title = new Label("GAME OVER");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
        box.getChildren().add(title);

        Button btnPlay = new Button("Tornar a jugar");
        btnPlay.setPrefWidth(150);
        box.getChildren().add(btnPlay);
        btnPlay.setOnMouseClicked(e -> startAnimation());

        Button btnExit = new Button("Eixir");
        btnExit.setPrefWidth(150);
        btnExit.setOnMouseClicked(e -> primaryStage.close());
        box.getChildren().add(btnExit);

        root.getChildren().add(box);
    }

    private void startGame() {
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

        countDownEatRedCircles = new CountDown(10);
        countDownEatRedCircles.start();
        labelSecondsRemaining = new Label("Temps restant: " + countDownEatRedCircles.getSecond());
        labelSecondsRemaining.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        labelSecondsRemaining.toFront();
        labelSecondsRemaining.setLayoutX(WIDTH_SCENE / 1.65);
        labelSecondsRemaining.setLayoutY(10);
        root.getChildren().add(labelSecondsRemaining);

        blueCircle.setControls(scene);

        setTimer();
        timer.start();
    }

    private void setTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                blueCircle.move();
                eatReadCircles();
                moveRedCircles();

                if (!blueCircle.isLive()) {
                    countDownEatRedCircles.stop();
                    timer.stop();
                    endAnimation();
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

    private void startAnimation() {
        FadeTransition fadeDisappear = new FadeTransition(Duration.millis(500), root);
        fadeDisappear.setFromValue(10);
        fadeDisappear.setToValue(0);
        fadeDisappear.play();
        fadeDisappear.setOnFinished(event -> {
            root.getChildren().clear();
            FadeTransition fadeAppear = new FadeTransition(Duration.millis(500), root);
            fadeAppear.setFromValue(0);
            fadeAppear.setToValue(10);
            fadeAppear.play();
            startGame();
        });
    }

    private void endAnimation() {
        Timeline timelineEndMenu = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            FadeTransition fadeDisappear = new FadeTransition(Duration.millis(500), root);
            fadeDisappear.setFromValue(10);
            fadeDisappear.setToValue(0);
            fadeDisappear.play();
            fadeDisappear.setOnFinished(event -> {
                root.getChildren().clear();
                FadeTransition fadeAppear = new FadeTransition(Duration.millis(500), root);
                fadeAppear.setFromValue(0);
                fadeAppear.setToValue(10);
                fadeAppear.play();
                endMenu();
            });
        }));
        timelineEndMenu.play();
    }

    private void moveRedCircles() {
        for (RedCircle redCircle : redCircles) {
            redCircle.move();
        }
    }

    public void removeRedCircles() {
        for (RedCircle redCircle : redCircles) {
            root.getChildren().remove(redCircle);
        }

        redCircles = new ArrayList<>();
    }

    private void eatReadCircles() {
        for (RedCircle redCircle : redCircles) {
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

    public void addRedCircle() {
        RedCircle redCircle = new RedCircle(this);
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

    public BlueCircle getBlueCircle() {
        return blueCircle;
    }

    public List<RedCircle> getRedCircles() {
        return redCircles;
    }

    public void restartCountDown() {
        countDownEatRedCircles.restart();
    }

    public static void main(String[] args) {
        launch();
    }

}
