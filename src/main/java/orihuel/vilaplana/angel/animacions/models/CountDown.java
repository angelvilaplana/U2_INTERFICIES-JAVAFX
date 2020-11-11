package orihuel.vilaplana.angel.animacions.models;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class CountDown {

    private final int initSecond;

    private int second;

    private final Timeline countDown;

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

    public void start() {
        countDown.play();
    }

    public void stop() {
        countDown.stop();
    }

    public void restart() {
        countDown.stop();
        second = initSecond;
        countDown.play();
    }

    public int getSecond() {
        return second;
    }

    public boolean isFinished() {
        return  second == 0;
    }

}
