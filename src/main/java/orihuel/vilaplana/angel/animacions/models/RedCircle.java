package orihuel.vilaplana.angel.animacions.models;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import orihuel.vilaplana.angel.animacions.MainScene;

import java.util.Random;

public class RedCircle extends Circle {

    private final MainScene mainScene;

    private int moveDX;

    private int moveDY;

    public RedCircle(MainScene mainScene) {
        this.mainScene = mainScene;
        setRedCircle();
        moveDX = new Random().nextInt(2) == 0 ? 1 : -1;
        moveDY = new Random().nextInt(2) == 0 ? 1: -1;
    }

    public void move() {
        if ((getCenterX() >= (mainScene.getWidthScene() - getRadius())) || getCenterX() <= (0 + getRadius())) {
            changeMoveDX();
        }

        if ((getCenterY() <= (0 + getRadius())) || (getCenterY() >= (mainScene.getHeightScene() - getRadius()))) {
            changeMoveDY();
        }

        for (RedCircle redCircle : mainScene.getRedCircles()) {
            if (redCircle != this && redCircle.getBoundsInParent().intersects(getBoundsInParent())) {
                double dx = Math.abs((redCircle.getCenterX() + redCircle.getRadius()) - (getCenterX() + getRadius()));
                double dy = Math.abs((redCircle.getCenterY() + redCircle.getRadius()) - (getCenterY() + getRadius()));

                if (dx > getRadius()) {
                    changeMoveDX();
                    redCircle.changeMoveDX();
                    redCircle.moveDX();
                }

                if (dy > getRadius()) {
                    changeMoveDY();
                    redCircle.changeMoveDY();
                    redCircle.moveDY();
                }
            }
        }

        moveDX();
        moveDY();
    }

    public void moveDX() {
        setCenterX(getCenterX() + moveDX);
    }

    public void moveDY() {
        setCenterY(getCenterY() + moveDY);
    }

    public void changeMoveDX() {
        moveDX *= -1;
    }

    public void changeMoveDY() {
        moveDY *= -1;
    }

    private boolean checkIsNearCircle(Circle circle) {
        boolean isDXNear, isDYNear;
        double spacing = 2;

        if (circle.getCenterX() > getCenterX()) {
            isDXNear = Math.abs(circle.getCenterX() - circle.getRadius() - getCenterX()) < (circle.getRadius() * spacing);
        } else {
            isDXNear = Math.abs(circle.getCenterX() + circle.getRadius() - getCenterX()) < (circle.getRadius() * spacing);
        }

        if (circle.getCenterY() > getCenterY()) {
            isDYNear = Math.abs(circle.getCenterY() - circle.getRadius() - getCenterY()) < (circle.getRadius() * spacing);
        } else {
            isDYNear = Math.abs(circle.getCenterY() + circle.getRadius() - getCenterY()) < (circle.getRadius() * spacing);
        }

        return isDXNear && isDYNear;
    }

    private boolean checkIsNearRedCircles() {
        for (RedCircle redCircle : mainScene.getRedCircles()) {
            if (checkIsNearCircle(redCircle)) {
                return true;
            }
        }

        return false;
    }

    private void setRedCircle() {
        int radiusCircleRed = 10;

        double maxDX = mainScene.getWidthScene() - (radiusCircleRed * 4);
        double minDX = radiusCircleRed * 4;
        double maxDY = mainScene.getHeightScene() - (radiusCircleRed * 4);
        double minDY = radiusCircleRed * 4;

        int dx, dy;
        boolean isNearCircles;
        do {
            dx = (int) Math.floor(Math.random() * (maxDX - minDX + 1) + (minDX));
            dy = (int) Math.floor(Math.random() * (maxDY - minDY + 1) + (minDY));
            setCircle(dx, dy);
            boolean isNearBlueCircle = checkIsNearCircle(mainScene.getBlueCircle());
            boolean isNearRedCircles = checkIsNearRedCircles();
            isNearCircles = isNearBlueCircle || isNearRedCircles;
        } while (isNearCircles);
    }

    private void setCircle(double dx, double dy) {
        setCenterX(dx);
        setCenterY(dy);
        setRadius(10);
        setFill(Color.RED);
    }

}
