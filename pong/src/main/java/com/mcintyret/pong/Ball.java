package com.mcintyret.pong;

import com.mcintyret.physicsgames.Physics;

import java.util.Random;

public class Ball {

    private static final double INITIAL_SPEED = 170D;

    private static final Random RNG = new Random();

    private final int diameter;

    private double bearing; // radians

    private double speed;

    private int xPos;

    private int yPos;

    public Ball(int diameter, int initialX, int initialY) {
        this.diameter = diameter;
        resetBearing();
        this.speed = INITIAL_SPEED;
        this.xPos = initialX;
        this.yPos = initialY;
    }

    public void resetBearing() {
        this.bearing = getInitialBearing();
    }

    public int getDiameter() {
        return diameter;
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

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    private static double getInitialBearing() {
        double bearing = RNG.nextDouble() * Physics.PI / 4;
        if (RNG.nextBoolean()) {
            bearing = Physics.TWO_PI - bearing;
        }
        return bearing;
    }
}
