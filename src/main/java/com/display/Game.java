package com.display;

import com.things.Pellot;
import com.things.snake.SnakeLocation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;

/**
 * @author Kevin Strong
 */
public class Game extends KeyAdapter {

    public static final int areaSize = 50;
    public static final int heightPadding = 2;
    private boolean[][] Border = new boolean[areaSize][areaSize + heightPadding];
    private Pellot currentPellot;
    private SnakeLocation snakeLocation;
    public static int initialSnakeLength = 5;

    private boolean control = true;
    private int currentScore = 0;
    private int highScore;
    private static Game instance;
    private static DIRECTION direction = DIRECTION.RIGHT;

    private enum DIRECTION {
        UP,
        DOWN,
        RIGHT,
        LEFT
    }

    public Game() {

        snakeLocation = new SnakeLocation();
        instance = this;
        GameFrame frame = new GameFrame(this);
        buildBorder(frame.getGraphics());
        createNewPellot();
        while (control) {
            delay();
            if (isCollision()) {
                endGame();
            }
            if (isEatingPellot()) {
                moveSnakePellot();
            } else {
                moveSnake(getNextHorizontalLocation(), getNextVerticalLocation());
            }

        }
        System.out.println("The Game is Over, your score is " + getCurrentScore());
    }

    private boolean isEatingPellot() {
        return currentPellot.atLocation(getNextHorizontalLocation(), getNextVerticalLocation());
    }

    private boolean isCollision() {
        return getSnakeLocation().getSnakeLocation()[getNextHorizontalLocation()][getNextVerticalLocation()] || Border[getNextHorizontalLocation()][getNextVerticalLocation()];
    }

    private int getCurrentScore() {
        return currentScore;
    }

    //Future pellet types may eventually increment your score by more than 1
    @SuppressWarnings("SameParameterValue")
    private void addScore(int x) {
        currentScore += x;
    }

    public void Clean(Graphics g) {
        for (int x = 0; x < areaSize; x++) {
            for (int y = 0; y < areaSize + heightPadding; y++) {
                if (!Border[x][y] && !currentPellot.atLocation(x,y)) {
                    g.setColor(Color.white);
                    g.fillRect(x * 10, y * 10, 10, 10);
                }
            }
        }
    }

    public void drawScore(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(400, 500, 100, 75);
        g.setColor(Color.black);
        g.drawString("currentScore:" + getCurrentScore(), 450, 525);
    }

    private void endGame() {
        System.out.println("Your game is over!");
        control = false;
        if (getCurrentScore() > highScore) {
            highScore = getCurrentScore();
        }
    }

    private void createNewPellot() {
        currentPellot = new Pellot();
    }

    public void drawPellot(Graphics g) {
        for (int x = 0; x < areaSize; x++) {
            for (int y = 2; y < areaSize + heightPadding; y++) {
                if (currentPellot.atLocation(x,y)) {
                    g.setColor(Color.BLUE);
                    g.fillRect(x * 10, y * 10, 10, 10);
                }
            }
        }
    }

    private void moveSnakePellot() {
        //Currently moveSnakePellot also moves the snake (but omits moving the tail, that is how the snake grows!)
        getSnakeLocation().getSnakeLocation()[getNextHorizontalLocation()][getNextVerticalLocation()] = true;
        getSnakeLocation().getHead().newlocation(getNextHorizontalLocation(), getNextVerticalLocation());
        createNewPellot();
        addScore(1);
    }

    private void moveSnake(int a, int b) {
        int x = -1, y = -1;
        if (getSnakeLocation().getSnakeLocation()[getSnakeLocation().getTail().getX() - 1][getSnakeLocation().getTail().getY()]) {
            x = getSnakeLocation().getTail().getX() - 1;
            y = getSnakeLocation().getTail().getY();
        } else if (getSnakeLocation().getSnakeLocation()[getSnakeLocation().getTail().getX() + 1][getSnakeLocation().getTail().getY()]) {
            x = getSnakeLocation().getTail().getX() + 1;
            y = getSnakeLocation().getTail().getY();
        } else if (getSnakeLocation().getSnakeLocation()[getSnakeLocation().getTail().getX()][getSnakeLocation().getTail().getY() - 1]) {
            x = getSnakeLocation().getTail().getX();
            y = getSnakeLocation().getTail().getY() - 1;
        } else if (getSnakeLocation().getSnakeLocation()[getSnakeLocation().getTail().getX()][getSnakeLocation().getTail().getY() + 1]) {
            x = getSnakeLocation().getTail().getX();
            y = getSnakeLocation().getTail().getY() + 1;
        }
        getSnakeLocation().getSnakeLocation()[getSnakeLocation().getTail().getX()][getSnakeLocation().getTail().getY()] = false;
        getSnakeLocation().getTail().newlocation(x, y);
        getSnakeLocation().getSnakeLocation()[a][b] = true;
        getSnakeLocation().getHead().newlocation(a, b);


    }

    public void buildBorder(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(0, 20, 500, 10);
        g.fillRect(0, 0, 10, 500);
        g.fillRect(490, 0, 10, 500);
        g.fillRect(0, 490, 500, 10);
        for (int x = 0; x < areaSize; x++) {
            Border[x][2] = true;
            Border[x][51] = true;
            Border[0][x + 2] = true;
            Border[49][x + 2] = true;
        }
    }

    public void drawSnake(Graphics g) {
        for (int x = 0; x < areaSize; x++) {
            for (int y = 0; y < areaSize + heightPadding; y++) {
                if (getSnakeLocation().getSnakeLocation()[x][y]) {
                    g.setColor(Color.magenta);
                    g.fillRect(x * 10, y * 10, 10, 10);
                }
            }
        }
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

    private int getNextHorizontalLocation() {
        int newLocation = getSnakeLocation().getHead().getX();
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

    private int getNextVerticalLocation() {
        int newLocation = getSnakeLocation().getHead().getY();
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

    public static Game getGame() {
        return instance;
    }

    @SuppressWarnings("empty-statement")
    private static void delay() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            System.out.println("Nap time interrupted! What happened!");
        }

    }

    public SnakeLocation getSnakeLocation() {
        return snakeLocation;
    }
}
