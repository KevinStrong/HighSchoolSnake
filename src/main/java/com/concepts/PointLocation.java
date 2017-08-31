package com.concepts;

import com.things.snake.Snake;

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

    public boolean atLocation(int aX, int aY) {
        return getX() == aX && getY() == aY;
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
