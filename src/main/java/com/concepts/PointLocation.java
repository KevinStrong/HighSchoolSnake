package com.concepts;

import com.things.snake.Snake;

import java.awt.*;

//The smallest unit of measurement in the game.  Can be used for collision.
public class PointLocation {

    private final int x;
    private final int y;

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

    public void draw(Graphics aGraphics, Color aColor) {
        aGraphics.setColor(aColor);
        aGraphics.fillRect( 10 + getX() * 10, 50 + getY() * 10, 10,10 );
    }

    public PointLocation createAdjacentLocation(Snake.DIRECTION aDirection) {
        int horizontalDelta = 0;
        int verticalDelta = 0;
        switch (aDirection) {
            case UP:
                verticalDelta--;
                break;
            case DOWN:
                verticalDelta++;
                break;
            case RIGHT:
                horizontalDelta++;
                break;
            case LEFT:
                horizontalDelta--;
                break;
        }
        return new PointLocation(getX() + horizontalDelta, getY() + verticalDelta);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null ) {
            return false;
        }
        if (!(o instanceof PointLocation)) {
            return false;
        }

        PointLocation that = (PointLocation) o;
        return (x == that.x && y == that.y);
    }
}
