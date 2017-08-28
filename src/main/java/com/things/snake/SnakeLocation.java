package com.things.snake;

import java.awt.*;

import static com.display.Game.areaSize;
import static com.display.Game.heightPadding;

public class SnakeLocation {

    private static int initialSnakeLength = 5;

    private boolean[][] snakeLocation = new boolean[areaSize][areaSize + heightPadding];
    private Tail end;
    private Head start;

    public SnakeLocation() {
        this.snakeLocation = new boolean[areaSize][areaSize + heightPadding];
        buildSnake();
        end = new Tail(10 - initialSnakeLength, 5);
        start = new Head(10, 5);
    }

    public void buildSnake() {
        for (int x = 0; x <= initialSnakeLength; x++) {
            snakeLocation[10 - x][5] = true;
        }

    }

    public Tail getTail() {
        return end;
    }

    public Head getHead() {
        return start;
    }

    public boolean isSnakeHere(int aX, int aY) {
        return snakeLocation[aX][aY];
    }

    public void drawSnake(Graphics g) {
        g.setColor(Color.white);
        getTail().cleanUpTail(g);
        for (int x = 0; x < areaSize; x++) {
            for (int y = 0; y < areaSize + heightPadding; y++) {
                if (snakeLocation[x][y]) {
                    g.setColor(Color.magenta);
                    g.fillRect(x * 10, y * 10, 10, 10);
                }
            }
        }
    }

    public boolean[][] getSnakeLocation() {
        return snakeLocation;
    }

    public void moveSnake(int aX, int aY) {
        int x = -1, y = -1;
        if (isSnakeHere(getTail().getX() - 1,getTail().getY())) {
            x = getTail().getX() - 1;
            y = getTail().getY();
        } else if (isSnakeHere(getTail().getX() + 1,getTail().getY())) {
            x = getTail().getX() + 1;
            y = getTail().getY();
        } else if (isSnakeHere(getTail().getX(),getTail().getY() - 1)) {
            x = getTail().getX();
            y = getTail().getY() - 1;
        } else if (isSnakeHere(getTail().getX(),getTail().getY() + 1)) {
            x = getTail().getX();
            y = getTail().getY() + 1;
        }
        getSnakeLocation()[getTail().getX()][getTail().getY()] = false;
        getTail().newLocation(x, y);
        getSnakeLocation()[aX][aY] = true;
        getHead().newlocation(aX, aY);
    }
}
