package com.mcintyret.pong;

import com.mcintyret.physicsgames.Rectangle;

public class Paddle implements Rectangle {

    private final int width;

    private final int height;

    private double verticalSpeed = 0;

    private final int xPos;

    private int yPos;

    public Paddle(int width, int height, int xPos) {
        this.width = width;
        this.height = height;
        this.xPos = xPos;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public int getXpos() {
        return xPos;
    }

    @Override
    public int getYpos() {
        return yPos;
    }

    public int getHeight() {
        return height;
    }

    public double getVerticalSpeed() {
        return verticalSpeed;
    }

    public void setVerticalSpeed(double verticalSpeed) {
        this.verticalSpeed = verticalSpeed;
    }
}
