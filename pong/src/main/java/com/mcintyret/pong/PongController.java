package com.mcintyret.pong;

import com.mcintyret.physicsgames.Physics;

public class PongController {

    private static final int WIDTH = 600;

    private static final int HEIGHT = 600;

    private static final int PADDLE_WIDTH = 32;

    private static final int PADDLE_HEIGHT = 100;

    private static final int PADDLE_INITIAL_Y = (HEIGHT / 2) - (PADDLE_HEIGHT / 2);

    private static final int PADDLE_X_GAP = 20;

    private final Ball ball = new Ball(30, WIDTH / 2, HEIGHT / 2);

    private final Paddle leftPaddle = new Paddle(PADDLE_WIDTH, PADDLE_HEIGHT, PADDLE_X_GAP, PADDLE_INITIAL_Y);

    private final Paddle rightPaddle = new Paddle(PADDLE_WIDTH, PADDLE_HEIGHT, WIDTH - (PADDLE_X_GAP + PADDLE_WIDTH), PADDLE_INITIAL_Y);

    public Score updatePosition(long millis, Motion leftMotion, Motion rightMotion) {
        updatePaddlePosition(leftPaddle, millis, leftMotion);
        updatePaddlePosition(rightPaddle, millis, rightMotion);
        return updateBallPosition(millis);
    }

    private static void updatePaddlePosition(Paddle paddle, long millis, Motion motion) {
        double time = millis / 1000D;
        int y = paddle.getYpos();
        switch (motion) {
            case UP:
                y -= (paddle.getVerticalSpeed() * time);
                y = Math.max(y, 0);
                break;
            case DOWN:
                y += (paddle.getVerticalSpeed() * time);
                y = Math.min(y, HEIGHT - PADDLE_HEIGHT);
                break;
        }
        paddle.setyPos(y);
    }

    private Score updateBallPosition(long millis) {
        double time = millis / 1000D;

        double bearing = ball.getBearing();
        int diameter = ball.getDiameter();

        boolean up = bearing < Physics.PI;
        boolean left = bearing > Physics.HALF_PI && bearing < Physics.THREE_PI_OVER_TWO;

        double distance = ball.getSpeed() * time;

        double verticalD = Math.sin(bearing) * distance;
        double horizontalD = Math.cos(bearing) * distance;

        int newX = ball.getxPos() + (int) horizontalD;
        int newY = ball.getyPos() - (int) verticalD;

        if (up && newY < 0) {
            newY += 2 * -newY;
            double bearingDiff = Physics.PI - bearing;
            bearing += bearingDiff + bearingDiff;
        } else if (!up && newY > (HEIGHT - diameter)) {
            newY -= 2 * (newY - (HEIGHT - diameter));
            double bearingDiff = bearing - Physics.PI;
            bearing -= (bearingDiff + bearingDiff);
        }

        if (left && newX < PADDLE_X_GAP + PADDLE_WIDTH) {
            if (hitsPaddle(leftPaddle, newY)) {
                newX += (2 * ((PADDLE_X_GAP + PADDLE_WIDTH) - newX));
                double bearingDiff = Physics.THREE_PI_OVER_TWO - bearing;
                bearing = Physics.THREE_PI_OVER_TWO + bearingDiff;
                if (bearing > Physics.TWO_PI) {
                    bearing -= Physics.TWO_PI;
                }
            } else {
                return Score.RIGHT_SCORES;
            }
        }

        if (!left && newX > WIDTH - (PADDLE_X_GAP + PADDLE_WIDTH + diameter)) {
            if (hitsPaddle(rightPaddle, newY)) {
                newX -= 2 * (newX - (WIDTH - (PADDLE_X_GAP + PADDLE_WIDTH + diameter)));
                double bearingDiff = Physics.THREE_PI_OVER_TWO - bearing;
                bearing = Physics.THREE_PI_OVER_TWO + bearingDiff;
                if (bearing > Physics.TWO_PI) {
                    bearing -= Physics.TWO_PI;
                }

            } else {
                return Score.LEFT_SCORES;
            }
        }


        ball.setBearing(bearing);
        ball.setxPos(newX);
        ball.setyPos(newY);

        // Don't worry about left / right because that's where we hit the paddles!
        return Score.NO_GOAL;
    }

    public Ball getBall() {
        return ball;
    }

    public Paddle getLeftPaddle() {
        return leftPaddle;
    }

    public Paddle getRightPaddle() {
        return rightPaddle;
    }

    private static boolean hitsPaddle(Paddle paddle, int y) {
        return y >= paddle.getYpos() && y <= paddle.getYpos() + paddle.getHeight();
    }

    public void resetPositions() {
        ball.setxPos(WIDTH / 2);
        ball.setyPos(HEIGHT / 2);
        ball.resetBearing();
        leftPaddle.setyPos(PADDLE_INITIAL_Y);
        rightPaddle.setyPos(PADDLE_INITIAL_Y);
    }
}
