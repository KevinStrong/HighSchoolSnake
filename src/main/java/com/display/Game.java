package com.display;

import com.things.Border;
import com.things.Pellet;
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
    private Border border;
    private Pellet currentPellet;
    protected SnakeLocation snakeLocation;

    private boolean isPlayingGame = true;
    private int currentScore = 0;
    private int highScore;
    private static DIRECTION direction = DIRECTION.RIGHT;

    public void drawStuff(Graphics aGraphics) {
        border.buildBorder(aGraphics);
        snakeLocation.drawSnake(aGraphics);
        drawPellot(aGraphics);
        drawScore(aGraphics);
    }

    private enum DIRECTION {
        UP,
        DOWN,
        RIGHT,
        LEFT
    }

    public Game() {
        snakeLocation = new SnakeLocation();
        GameFrame frame = new GameFrame(this);
        border = buildBorder(frame);
        createNewPellet();
        mainGameLoop();
    }

    private void mainGameLoop() {
        while (isPlayingGame) {
            delay();
            if (isCollision()) {
                endGame();
            }
            if (isEatingPellot()) {
                moveSnakePellot();
            } else {
                moveSnake(getNextHorizontalLocation(), getNextVerticalLocation());
            }
            System.out.println("The Game is Over, your score is " + getCurrentScore());
        }
    }

    private Border buildBorder(GameFrame frame) {
        return new Border(frame.getGraphics());
    }

    private boolean isEatingPellot() {
        return currentPellet.atLocation(getNextHorizontalLocation(), getNextVerticalLocation());
    }

    private boolean isCollision() {
        return getSnakeLocation().getSnakeLocation()[getNextHorizontalLocation()][getNextVerticalLocation()] || border.borderExists(getNextHorizontalLocation(), getNextVerticalLocation());
    }

    private int getCurrentScore() {
        return currentScore;
    }

    //Future pellet types may eventually increment your score by more than 1
    @SuppressWarnings("SameParameterValue")
    private void addScore(int x) {
        currentScore += x;
    }

    public void drawScore(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(400, 500, 100, 75);
        g.setColor(Color.black);
        g.drawString("currentScore:" + getCurrentScore(), 450, 525);
    }

    private void endGame() {
        System.out.println("Your game is over!");
        isPlayingGame = false;
        if (getCurrentScore() > highScore) {
            highScore = getCurrentScore();
        }
    }

    private void createNewPellet() {
        currentPellet = new Pellet();
    }

    public void drawPellot(Graphics g) {
        for (int x = 0; x < areaSize; x++) {
            for (int y = 2; y < areaSize + heightPadding; y++) {
                if (currentPellet.atLocation(x, y)) {
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
        createNewPellet();
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
        getSnakeLocation().getTail().newLocation(x, y);
        getSnakeLocation().getSnakeLocation()[a][b] = true;
        getSnakeLocation().getHead().newlocation(a, b);


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
