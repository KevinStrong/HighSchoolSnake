package com.things.snake;

import java.awt.*;

/**
 * @author KS70326
 */
public class Tail {

    private int x;
    private int y;
    private int previousX;
    private int previousY;

    public Tail(int a, int b) {
        x = a;
        y = b;
    }

    public void newLocation(int aX, int aY) {
        previousX = x;
        previousY = y;
        x = aX;
        y = aY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void cleanUpTail(Graphics aGraphics) {
        aGraphics.setColor(Color.WHITE);
        aGraphics.fillRect(previousX * 10, previousY * 10, 10, 10);
    }
}
