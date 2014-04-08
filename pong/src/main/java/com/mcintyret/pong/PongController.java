package com.mcintyret.pong;

import com.mcintyret.physicsgames.Physics;

public class PongController {

    private static final int PADDLE_WIDTH = 16;

    private static final int PADDLE_HEIGHT = 40;

    private int ballX;

    private int ballY;

    private final Ball ball = new Ball(10);

    private final Paddle leftPaddle = new Paddle(PADDLE_WIDTH, PADDLE_HEIGHT, 20);

    private final Paddle rightPaddle = new Paddle(PADDLE_WIDTH, PADDLE_HEIGHT, 580);


    public boolean updatePosition(long millis, Motion motion) {
        updateBallPosition(millis);
        return false;
    }

    private void updateBallPosition(long millis) {
        double time = millis / 1000D;

        boolean up = ball.getBearing() < Physics.TWO_PI;

        double distance = ball.getSpeed() * time;

        double verticalD = Math.sin(ball.getBearing()) * distance;
        double horizontalD = Math.cos(ball.getBearing()) * distance;

        int newX = ballX + (int) horizontalD;
        int newY = ballY + (int) verticalD;




    }


}
