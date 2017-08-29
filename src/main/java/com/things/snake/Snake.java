package com.things.snake;

import com.things.Pellet;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static com.display.Game.areaSize;
import static com.display.Game.heightPadding;

public class Snake extends KeyAdapter {

    private static int initialSnakeLength = 9;

    private boolean[][] snakeLocation = new boolean[areaSize][areaSize + heightPadding];
    private Tail end;
    private Head start;
    private static DIRECTION direction;

    public enum DIRECTION {
        UP,
        DOWN,
        RIGHT,
        LEFT
    }

    public Snake() {
        direction = DIRECTION.RIGHT;
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

    private Tail getTail() {
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

    public void moveSnake() {
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
        getSnakeLocation()[getNextHorizontalLocation()][getNextVerticalLocation()] = true;
        getHead().newlocation(getNextHorizontalLocation(), getNextVerticalLocation());
    }

    public void moveSnakeHeadOnly() {
        getSnakeLocation()[getNextHorizontalLocation()]
                [getNextVerticalLocation()] = true;
        getHead().newlocation(getNextHorizontalLocation(),
                getNextVerticalLocation());
    }

    public  int getNextHorizontalLocation() {
        int newLocation = getHead().getX();
        switch (direction) {
            case LEFT:
                newLocation -= 1;
                break;
            case RIGHT:
                newLocation += 1;
                break;
        }
        return newLocation;
    }

    public int getNextVerticalLocation() {
        int newLocation = getHead().getY();
        switch (direction) {
            case UP:
                newLocation -= 1;
                break;
            case DOWN:
                newLocation += 1;
                break;
        }
        return newLocation;
    }

    public boolean eats(Pellet currentPellet) {
        return currentPellet.atLocation( getNextHorizontalLocation(), getNextVerticalLocation() );
    }

    public boolean isCollision(Snake snake) {
        return snake.getSnakeLocation()[getNextHorizontalLocation()][getNextVerticalLocation()];
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            direction = DIRECTION.LEFT;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direction = DIRECTION.RIGHT;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            direction = DIRECTION.UP;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            direction = DIRECTION.DOWN;
        }
    }
}
