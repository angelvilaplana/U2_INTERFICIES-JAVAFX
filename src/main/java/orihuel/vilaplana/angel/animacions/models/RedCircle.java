package orihuel.vilaplana.angel.animacions.models;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import orihuel.vilaplana.angel.animacions.MainScene;

import java.util.Random;

public class RedCircle extends CircleExtension {

    private final MainScene mainScene;

    private int moveDX;

    private int moveDY;

    /**
     * Genera el cercle roig en el escenari principal.
     * També generem la posició on el cercle començara a moure
     * @param mainScene Escenari principal
     */
    public RedCircle(MainScene mainScene) {
        this.mainScene = mainScene;
        setRedCircle();
        moveDX = new Random().nextInt(2) == 0 ? 1 : -1;
        moveDY = new Random().nextInt(2) == 0 ? 1: -1;
    }

    /**
     * Moviment de la bola
     */
    public void move() {
        // Condició per a comprovar si està dalt o baix de la pantalla i canviar la seua direcció X
        if ((getCenterX() >= (mainScene.getWidthScene() - getRadius())) || getCenterX() <= (0 + getRadius())) {
            changeMoveDX();
        }

        // Condició per a comprovar si està a l'esquerre o dreta de la pantalla i canviar la seua direcció Y
        if ((getCenterY() <= (0 + getRadius())) || (getCenterY() >= (mainScene.getHeightScene() - getRadius()))) {
            changeMoveDY();
        }

        // Comprobar els cercles rojos si es toquen
        for (RedCircle redCircle : mainScene.getRedCircles()) {
            // Condició si els cercles es choquen i que no siguen el mateix
            if (redCircle != this && redCircle.getBoundsInParent().intersects(getBoundsInParent())) {
                // Comprobar diferencies entre els dos cercles en l'eix X
                double dx = Math.abs((redCircle.getCenterX() + redCircle.getRadius()) - (getCenterX() + getRadius()));
                // Comprobar diferencies entre els dos cercles en l'eix Y
                double dy = Math.abs((redCircle.getCenterY() + redCircle.getRadius()) - (getCenterY() + getRadius()));

                // Si l'eix X es major al radi del cercle
                // Canviem de direcció al moviment X dels dos cercles
                if (dx > getRadius()) {
                    changeMoveDX();
                    redCircle.changeMoveDX();
                    redCircle.moveDX();
                }

                // Si l'eix Y es major al radi del cercle
                // Canviem de direcció al moviment Y dels dos cercles
                if (dy > getRadius()) {
                    changeMoveDY();
                    redCircle.changeMoveDY();
                    redCircle.moveDY();
                }
            }
        }

        // Move el cercle en l'eix X
        moveDX();
        // Move el cercle en l'eix Y
        moveDY();
    }

    /**
     * Moviment de la bola en l'eix X
     */
    public void moveDX() {
        setCenterX(getCenterX() + moveDX);
    }

    /**
     * Moviment de la bola en l'eix Y
     */
    public void moveDY() {
        setCenterY(getCenterY() + moveDY);
    }

    /**
     * Cambiem el moviment de la bola en l'eix X
     */
    public void changeMoveDX() {
        moveDX *= -1;
    }

    /**
     * Cambiem el moviment de la bola en l'eix Y
     */
    public void changeMoveDY() {
        moveDY *= -1;
    }

    /**
     * Comprobem si el cercle a comparar esta prop del cercle actual
     *
     * @param circle Cercle a comparar
     * @return true  - Si està en el rang de proximitat
     *         false - Si no està en el rang de proximitat
     */
    private boolean checkIsNearCircle(Circle circle) {
        // isDXNear - Si esta prop en l'eix X
        // isDYNear - Si està prop en l'eix Y
        boolean isDXNear, isDYNear;
        // Espai mínim de proximitat
        double spacing = 2;

        // Condició si la posició de l'eix X del cercle a comparar es major a la posició de l'eix X del cercle actual
        // Comprobar si cumpleix la condició de proximitat en l'eix X
        if (circle.getCenterX() > getCenterX()) {
            isDXNear = Math.abs(circle.getCenterX() - circle.getRadius() - getCenterX()) < (circle.getRadius() * spacing);
        } else {
            isDXNear = Math.abs(circle.getCenterX() + circle.getRadius() - getCenterX()) < (circle.getRadius() * spacing);
        }

        // Condició si la posició de l'eix Y del cercle a comparar es major a la posició de l'eix Y del cercle actual
        // Comprobar si cumpleix la condició de proximitat en l'eix Y
        if (circle.getCenterY() > getCenterY()) {
            isDYNear = Math.abs(circle.getCenterY() - circle.getRadius() - getCenterY()) < (circle.getRadius() * spacing);
        } else {
            isDYNear = Math.abs(circle.getCenterY() + circle.getRadius() - getCenterY()) < (circle.getRadius() * spacing);
        }

        // Si estàn props en els dos eixos tornarà true
        return isDXNear && isDYNear;
    }

    /**
     * Comprobar si alguns de les boles rojes està prop de la bola actual
     * @return true  - Si està prop d'alguna de les boles
     *         false - Si no cumpleix la condició de estar prop
     */
    private boolean checkIsNearRedCircles() {
        for (RedCircle redCircle : mainScene.getRedCircles()) {
            if (checkIsNearCircle(redCircle)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Asignar la bola roja a la escena
     * evitant que es toquen en altres boles de la escena
     */
    private void setRedCircle() {
        // Radi del cercle
        int radiusCircleRed = 10;

        // Valors màxim  y mínims per a que no estiguen fora de la escena
        double maxDX = mainScene.getWidthScene() - (radiusCircleRed * 4);
        double minDX = radiusCircleRed * 4;
        double maxDY = mainScene.getHeightScene() - (radiusCircleRed * 4);
        double minDY = radiusCircleRed * 4;

        int dx, dy;
        boolean isNearCircles;
        // Bucle que es repetira fins que la posició asignada no estiga prop
        // de cap cercle de la escena
        do {
            dx = (int) Math.floor(Math.random() * (maxDX - minDX + 1) + (minDX));
            dy = (int) Math.floor(Math.random() * (maxDY - minDY + 1) + (minDY));
            boolean isNearBlueCircle = checkIsNearCircle(mainScene.getBlueCircle());
            boolean isNearRedCircles = checkIsNearRedCircles();
            isNearCircles = isNearBlueCircle || isNearRedCircles;
        } while (isNearCircles);

        setCircle(dx, dy);
    }

    /**
     * Asignar els valors principals del cercle roig
     * @param dx eix X on es situara
     * @param dy eix Y on es situara
     */
    private void setCircle(double dx, double dy) {
        setCenterX(dx);
        setCenterY(dy);
        setRadius(0);
        setRadiusTransition(10);
        setFill(Color.RED);
    }

}
