package com.concepts;

public abstract class PointLocation {

    private int x;
    private int y;

    public PointLocation(int aX, int aY) {
        x = aX;
        y = aY;
    }

    protected int getX() {
        return x;
    }

    protected int getY() {
        return y;
    }

    public boolean atLocation(int aX, int aY) {
        return getX() == aX && getY() == aY;
    }

}
