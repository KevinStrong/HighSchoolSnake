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

    public boolean[][] getSnakeLocation() {
        return snakeLocation;
    }

    public void drawSnake(Graphics g) {
        g.setColor(Color.white);
        getTail().cleanUpTail(g);
        for (int x = 0; x < areaSize; x++) {
            for (int y = 0; y < areaSize + heightPadding; y++) {
                if (getSnakeLocation()[x][y]) {
                    g.setColor(Color.magenta);
                    g.fillRect(x * 10, y * 10, 10, 10);
                }
            }
        }
    }
}
