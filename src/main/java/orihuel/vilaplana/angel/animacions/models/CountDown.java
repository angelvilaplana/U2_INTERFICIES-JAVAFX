package orihuel.vilaplana.angel.animacions.models;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class CountDown {

    private final int initSecond;

    private int second;

    private final Timeline countDown;

    /**
     * Un compte enrere en segons que va restant els segons inicials
     * fins que arriba a 0
     *
     * @param initSecond Els segons on comença
     */
    public CountDown(int initSecond) {
        this.initSecond = initSecond;
        this.second = initSecond;
        this.countDown = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            second--;
            if (second == 0) {
                stop();
            }
        }));
        countDown.setCycleCount(Animation.INDEFINITE);
    }

    /**
     * Comença el compte enrere
     */
    public void start() {
        countDown.play();
    }

    /**
     * Para el compte enrere
     */
    public void stop() {
        countDown.stop();
    }

    /**
     * Renicia el compte enrere
     */
    public void restart() {
        countDown.stop();
        second = initSecond;
        countDown.play();
    }

    /**
     * Obté els segons actual
     */
    public int getSecond() {
        return second;
    }

    /**
     * Si el compte enrere ha finalizat
     */
    public boolean isFinished() {
        return  second == 0;
    }

}
