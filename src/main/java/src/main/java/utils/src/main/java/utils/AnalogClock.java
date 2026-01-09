package utils;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.time.LocalTime;

public class AnalogClock extends Pane {

    private static final double WIDTH = 140;
    private static final double CENTER = WIDTH / 2;

    private Line hourHand;
    private Line minuteHand;
    private Line secondHand;

    public AnalogClock() {
        setPrefSize(WIDTH, WIDTH);
        buildClock();
        runClock();
    }

    private void buildClock() {
        Circle background = new Circle(CENTER, CENTER, 55);
        background.setFill(Color.WHITE);
        background.setStroke(Color.BLACK);
        background.setStrokeWidth(2);

        getChildren().add(background);

        // Hour marks
        for (int i = 0; i < 12; i++) {
            double angle = Math.toRadians(i * 30 - 90);
            double startX = CENTER + 47 * Math.cos(angle);
            double startY = CENTER + 47 * Math.sin(angle);
            double endX = CENTER + 52 * Math.cos(angle);
            double endY = CENTER + 52 * Math.sin(angle);

            Line tick = new Line(startX, startY, endX, endY);
            tick.setStrokeWidth(2);
            getChildren().add(tick);
        }

        hourHand = new Line(CENTER, CENTER, CENTER, CENTER - 25);
        hourHand.setStrokeWidth(4);

        minuteHand = new Line(CENTER, CENTER, CENTER, CENTER - 38);
        minuteHand.setStrokeWidth(2.5);

        secondHand = new Line(CENTER, CENTER, CENTER, CENTER - 42);
        secondHand.setStroke(Color.RED);
        secondHand.setStrokeWidth(1);

        getChildren().addAll(hourHand, minuteHand, secondHand);

        Circle centerDot = new Circle(CENTER, CENTER, 3);
        getChildren().add(centerDot);
    }

    private void runClock() {
        Timeline timer = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> updateClock())
        );
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

        updateClock();
    }

    private void updateClock() {
        LocalTime now = LocalTime.now();

        double secondsAngle = Math.toRadians(now.getSecond() * 6 - 90);
        secondHand.setEndX(CENTER + 42 * Math.cos(secondsAngle));
        secondHand.setEndY(CENTER + 42 * Math.sin(secondsAngle));

        double minutesAngle = Math.toRadians(now.getMinute() * 6 - 90);
        minuteHand.setEndX(CENTER + 38 * Math.cos(minutesAngle));
        minuteHand.setEndY(CENTER + 38 * Math.sin(minutesAngle));

        double hoursAngle = Math.toRadians(
                (now.getHour() % 12) * 30 + now.getMinute() * 0.5 - 90
        );
        hourHand.setEndX(CENTER + 25 * Math.cos(hoursAngle));
        hourHand.setEndY(CENTER + 25 * Math.sin(hoursAngle));
    }
}
