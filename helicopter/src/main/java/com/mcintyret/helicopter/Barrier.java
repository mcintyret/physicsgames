package com.mcintyret.helicopter;

import com.mcintyret.physicsgames.Rectangle;

public class Barrier implements Rectangle {

    private final int width;

    private final int height;

    private final int yPos;

    private int xPos;

    public Barrier(int width, int height, int xPos, int yPos) {
        this.width = width;
        this.height = height;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getYpos() {
        return yPos;
    }

    @Override
    public int getXpos() {
        return xPos;
    }

    public void setXpos(int xPos) {
        this.xPos = xPos;
    }
}
