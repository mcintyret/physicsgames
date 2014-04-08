package com.mcintyret.pong;

import java.util.Random;

public class Ball {

    private static final double INITIAL_SPEED = 10D;

    private static final Random RNG = new Random();

    private final int radius;

    private double bearing; // radians

    private double speed;

    public Ball(int radius) {
        this.radius = radius;
        this.bearing = RNG.nextDouble() * 2 * Math.PI;
        this.speed = INITIAL_SPEED;
    }

    public int getRadius() {
        return radius;
    }

    public double getBearing() {
        return bearing;
    }

    public void setBearing(double bearing) {
        this.bearing = bearing;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
