package com.mcintyret.helicopter;

import com.mcintyret.physicsgames.Rectangle;

public class Helicopter implements Rectangle {

    private static final double INITIAL_SPEED = 100D;

    private double verticalPos;

    private double verticalSpeed;

    private double horizontalSpeed = INITIAL_SPEED;

    private final int width;

    private final int height;

    public Helicopter(int width, int height, int initialPos) {
        this.width = width;
        this.height = height;
        this.verticalPos = initialPos;
    }

    public double getVerticalSpeed() {
        return verticalSpeed;
    }

    public void setVerticalSpeed(double verticalSpeed) {
        this.verticalSpeed = verticalSpeed;
    }

    @Override
    public int getYpos() {
        return (int) verticalPos;
    }

    public void setYpos(double verticalPos) {
        this.verticalPos = verticalPos;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getXpos() {
        return 10;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public double getHorizontalSpeed() {
        return horizontalSpeed;
    }
}
