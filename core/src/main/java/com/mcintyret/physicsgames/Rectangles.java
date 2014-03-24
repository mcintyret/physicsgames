package com.mcintyret.physicsgames;

import java.awt.*;

public class Rectangles {

    public static void draw(Rectangle rectangle, Graphics2D g) {
        g.drawRect(rectangle.getXpos(), rectangle.getYpos(), rectangle.getWidth(), rectangle.getHeight());
    }

    public static void fill(Rectangle rectangle, Graphics2D g) {
        g.fillRect(rectangle.getXpos(), rectangle.getYpos(), rectangle.getWidth(), rectangle.getHeight());
    }

    public static boolean overlap(Rectangle r1, Rectangle r2) {
        int r1MinX = r1.getXpos();
        int r1MaxX = r1MinX + r1.getWidth();
        int r1MinY = r1.getYpos();
        int r1MaxY = r1MinY + r1.getHeight();

        int r2MinX = r2.getXpos();
        int r2MaxX = r2MinX + r2.getWidth();
        int r2MinY = r2.getYpos();
        int r2MaxY = r2MinY + r2.getHeight();


        return r1MaxX > r2MinX &&
            r1MinX < r2MaxX &&
            r1MaxY > r2MinY &&
            r1MinY < r2MaxY;
    }

}
