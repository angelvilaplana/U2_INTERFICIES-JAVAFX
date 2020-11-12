package orihuel.vilaplana.angel.animacions;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

/**
 * Escena principal
 */
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

        // Començar per el menú principal
        startMenu();

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Menú principal
     */
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

    /**
     * Menú quan la bola blava es queda sense
     * vides
     */
    private void endMenu() {
        VBox box = new VBox(20);
        box.setPrefHeight(HEIGHT_SCENE);
        box.setPrefWidth(WIDTH_SCENE);
        box.setAlignment(Pos.CENTER);
        Label title = new Label("GAME OVER");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
        box.getChildren().add(title);

        // Transició del Label "GAME OVER" baixant
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), title);
        translateTransition.setFromY(-50);
        translateTransition.setToY(0);
        translateTransition.play();

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

    /**
     * Joc i el exercici principal
     */
    private void startGame() {
        // Creem la bola blava
        blueCircle = new BlueCircle(this);
        root.getChildren().add(blueCircle);

        // Creem les boles rojes
        redCircles = new ArrayList<>();
        addRedCircles();

        // Label de les vides
        labelBlueLives = new Label("Vides: " + blueCircle.getLives());
        labelBlueLives.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        labelBlueLives.toFront();
        labelBlueLives.setLayoutX(10);
        labelBlueLives.setLayoutY(10);
        root.getChildren().add(labelBlueLives);

        // Compte enrere per al temps que te la bola blava en menjar a una roja
        countDownEatRedCircles = new CountDown(10);
        countDownEatRedCircles.start();
        labelSecondsRemaining = new Label("Temps restant: " + countDownEatRedCircles.getSecond());
        labelSecondsRemaining.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        labelSecondsRemaining.toFront();
        labelSecondsRemaining.setLayoutX(WIDTH_SCENE / 1.65);
        labelSecondsRemaining.setLayoutY(10);
        root.getChildren().add(labelSecondsRemaining);

        // Controls del teclat de la bola blava
        blueCircle.setControls();

        // Temporizador principal del joc
        setTimer();
        timer.start();
    }

    /**
     * Asignar temporizador al joc
     */
    private void setTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Moviment de la bola blava
                blueCircle.move();
                // Menjar els cercles rojos
                eatReadCircles();
                // Moviment de les boles rojes
                moveRedCircles();

                // Si no esta viu para els timers i
                // acaba el joc
                if (!blueCircle.isLive()) {
                    countDownEatRedCircles.stop();
                    timer.stop();
                    endAnimation();
                }

                // Si el compte enrerere es igual a 0
                // Li baixa una vida i renicia el compte enrere
                if (countDownEatRedCircles.isFinished()) {
                    blueCircle.removeLive();
                    countDownEatRedCircles.restart();
                }

                // Actualizar labels
                labelBlueLives.setText("Vides: " + blueCircle.getLives());
                labelSecondsRemaining.setText("Temps restant: " + countDownEatRedCircles.getSecond());
            }
        };
    }

    /**
     * Animació principal del joc
     *
     * Desapareix el que hi havia i
     * neteja el Group fent una animacio de aparicio i
     * iniciant el joc
     */
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

    /**
     * Animació final del joc
     *
     * Desapareix el que hi havia i
     * neteja el Group fent una animacio de aparicio i
     * va al menú de Game Over
     */
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

    /**
     * Moviment de les boles rojes
     */
    private void moveRedCircles() {
        for (RedCircle redCircle : redCircles) {
            redCircle.move();
        }
    }

    /**
     * Eliminar boles rojes
     */
    public void removeRedCircles() {
        for (RedCircle redCircle : redCircles) {
            root.getChildren().remove(redCircle);
        }

        redCircles = new ArrayList<>();
    }

    /**
     * Menjar boles rojes si
     * la bola blova esta dins del rang.
     * Fent que li puje una vida
     */
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

    /**
     * Afegir boles rojes si la llista
     * està incompleta
     */
    public void addRedCircles() {
        while (NUMBER_RED_CIRCLES > redCircles.size()) {
            addRedCircle();
        }
    }

    /**
     * Afegir una bola roja i
     * posarla de fons
     */
    public void addRedCircle() {
        RedCircle redCircle = new RedCircle(this);
        root.getChildren().add(redCircle);
        redCircles.add(redCircle);
        redCircle.toBack();
    }

    public Scene getScene() {
        return scene;
    }

    /**
     * Obtindre l'amplaria de la finestra
     */
    public double getWidthScene() {
        return WIDTH_SCENE;
    }

    /**
     * Obtindre l'altura de la finestra
     */
    public double getHeightScene() {
        return HEIGHT_SCENE;
    }

    /**
     * Obtindre el cercle blau
     */
    public BlueCircle getBlueCircle() {
        return blueCircle;
    }

    /**
     * Obtindre els cercles rojos
     */
    public List<RedCircle> getRedCircles() {
        return redCircles;
    }

    /**
     * Reniciar el compte enrere
     */
    public void restartCountDown() {
        countDownEatRedCircles.restart();
    }

    public static void main(String[] args) {
        launch();
    }

}
