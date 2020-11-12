package orihuel.vilaplana.angel.animacions.models;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import orihuel.vilaplana.angel.animacions.MainScene;

public class BlueCircle extends Circle {

    private final MainScene mainScene;

    private int lives;

    private boolean up, down, right, lest;

    /**
     * Cercle blau on es moura el jugador
     *
     * @param mainScene Escena principal
     */
    public BlueCircle(MainScene mainScene) {
        super(mainScene.getWidthScene() / 2, mainScene.getHeightScene() / 2, 20, Color.BLUE);
        this.mainScene = mainScene;
        // Vides de la bola blava
        this.lives = 3;
    }

    /**
     * Asignar els controls al jugador
     */
    public void setControls() {
        // Mentres el jugador pulsa alguna de les tecles introduïdes.
        // Aquella opció s'activara
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

        // Quan el jugador deixa de polsar alguna de les tecles introduïdes.
        // Aquella opció es desactivara
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

    /**
     * Moviment de la bola blava
     */
    public void move() {
        // Velocitat de la bola
        double speed = 2;

        // Si va amunt
        if (up) {
            // Si no arribaa dalt de la finestra, el jugador es moura
            // cap amunt. Si no, aixó voldra dir que ha colisionat amb la pared i
            // li se llevara una vida
            if (getCenterY() > (0 + getRadius())) {
                setCenterY(getCenterY() - speed);
            } else {
                removeLive();
            }
        }

        // Si va per abaix
        if (down) {
            // Si no arribaa baix de la finestra, el jugador es moura
            // cap abaix. Si no, aixó voldra dir que ha colisionat amb la pared i
            // li se llevara una vida
            if (getCenterY() < (mainScene.getHeightScene() - getRadius())) {
                setCenterY(getCenterY() + speed);
            } else {
                removeLive();
            }
        }
        // Si va a la dreta
        if (right) {
            // Si no arribaa a la dreta de la finestra, el jugador es moura
            // cap a la dreta. Si no, aixó voldra dir que ha colisionat amb la pared i
            // li se llevara una vida
            if (getCenterX() < (mainScene.getWidthScene() - getRadius())) {
                setCenterX(getCenterX() + speed);
            } else {
                removeLive();
            }
        }

        // Si va a l'esquerre
        if (lest) {
            // Si no arribaa a l'esquerre de la finestra, el jugador es moura
            // cap a l'esquerre. Si no, aixó voldra dir que ha colisionat amb la pared i
            // li se llevara una vida
            if (getCenterX() > (0 + getRadius())) {
                setCenterX(getCenterX() - speed);
            } else {
                removeLive();
            }
        }
    }

    /**
     * Li afegim una vida a la bola blava
     */
    public void addLive() {
        lives++;
        // Transició de color de verd a blau
        FillTransition fillTransition = new FillTransition(Duration.millis(500), this, Color.GREEN, Color.BLUE);
        fillTransition.play();

        // Si la vida de la pilota es menor a 9 aumentara el seu tamany a 5 amb una animació
        // Ho faig aixi per a que el programa no pete a l'hora de introduïr un nou cercle roig
        if (lives < 9) {
            setRadiusTransition(5);
        }

        // Reniciar el compte enrere
        mainScene.restartCountDown();
        // Anyadir un nou cercle roig a l'escena
        mainScene.addRedCircle();
    }

    /**
     * Eliminem una vida al cercle blau
     */
    public void removeLive() {
        lives--;
        // Renicia el compte enrere
        mainScene.restartCountDown();

        // Si encara te vides
        if (isLive()) {
            // Eliminem els cercles de la escena
            mainScene.removeRedCircles();
            // Posem el cercle blau al centre de la escena
            setCenterX(mainScene.getWidthScene() / 2);
            setCenterY(mainScene.getHeightScene() / 2);
            // Anyadim cercles a l'escena
            mainScene.addRedCircles();
            // Animació al cercle cambiant de color a negre a blau
            FillTransition fillTransition = new FillTransition(Duration.millis(500), this, Color.BLACK, Color.BLUE);
            fillTransition.play();

            // Si la bola es menor a 8 li baixarem el seu radi
            // Aixó es per a evitar problemes anteriorment a l'hora de calcular els cercles rojos a on es coloquen
            if (lives < 8) {
                setRadiusTransition(-5);
            }
        } else {
            // Transició o de esvaniment quan la pilota blava es queda sense vides
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), this);
            fadeTransition.setFromValue(10);
            fadeTransition.setToValue(0);
            fadeTransition.play();
        }
    }

    /**
     * Transició quan es canvia el radi del cercle blau
     *
     * @param radius el radi que volem que modifique
     */
    private void setRadiusTransition(int radius) {
        AnimationTimer animationRadius = new AnimationTimer() {
            double actualRadius = 0;
            final double speed = .5;

            @Override
            public void handle(long now) {
                // Si el radi actual es major al radi asignat
                // Li baixarem a la velocitat asignada.
                // Si no, serà el contrari
                // Quan siguen iguals aquesta animació
                // es parara
                if (actualRadius > radius) {
                    setRadius(getRadius() - speed);
                    actualRadius -= speed;
                } else if (actualRadius < radius) {
                    setRadius(getRadius() + speed);
                    actualRadius += speed;
                } else {
                    stop();
                }
            }
        };
        animationRadius.start();
    }

    /**
     * Obtindre les vides del cercle blau
     *
     * @return vides de la bola blava
     */
    public int getLives() {
        return lives;
    }

    /**
     * Si la bola està viva
     *
     *  @return Si està viu o no la bola blava
     */
    public boolean isLive() {
        return lives > 0;
    }

}
