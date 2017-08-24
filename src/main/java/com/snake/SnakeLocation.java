package com.snake;

import static com.display.Game.areaSize;
import static com.display.Game.heightPadding;
import static com.display.Game.initialSnakeLength;

public class SnakeLocation {

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

    public boolean[][] getSnakeLocation(){
        return snakeLocation;
    }
}
