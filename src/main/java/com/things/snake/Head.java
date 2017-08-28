package com.things.snake;

/**
 * @author KS70326
 */
public class Head {

    int x;
    int y;

    public Head(int aX, int aY) {
        x = aX;
        y = aY;
        //   System.out.println("Making new start with locations of" + x+ " " + y);
    }

    public void newlocation(int aX, int aY) {
        x = aX;
        y = aY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
