package orihuel.vilaplana.angel.animacions.models;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class CircleExtension extends Circle {

    public CircleExtension() {
    }

    public CircleExtension(double centerX, double centerY, double radius, Paint fill) {
        super(centerX, centerY, radius, fill);
    }

    /**
     * Transició quan es canvia el radi del cercle
     *
     * @param radius el radi que volem que modifique
     */
    public void setRadiusTransition(int radius) {
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

}
