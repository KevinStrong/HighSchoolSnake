package com.display;

import com.snake.FirstPart;
import com.snake.LastPart;
import com.snake.SnakeLocation;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.event.*;

/**
 * @author Kevin Strong
 */
public class Game extends KeyAdapter {

    private static final int areaSize = 50;
    private static final int heightPadding = 2;
    private Random generator = new Random();
    private boolean[][] Border = new boolean[areaSize][areaSize + heightPadding];
    private boolean[][] snakeLocation = new boolean[areaSize][areaSize + heightPadding];
    private boolean[][] pellotLocation = new boolean[areaSize][areaSize + heightPadding];
    private int initialSnakeLength = 5;
    private LastPart end;
    private FirstPart start;
    private boolean control = true;
    private int Score = 0;
    private int scoreFinal;
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

        instance = this;
        GameFrame frame = new GameFrame(this);
        buildBorder(frame.getGraphics());
        buildSnake(frame.getGraphics());
        createNewPellot();
        while (control) {
            delay();
            buildBorder(frame.getGraphics());
            if (pellotLocation[getMoveLocationX()][getMoveLocationY()]) {
                moveSnakePellot(getMoveLocationX(), getMoveLocationY());
            }
            if (snakeLocation[getMoveLocationX()][getMoveLocationY()] || Border[getMoveLocationX()][getMoveLocationY()]) {
                endGame();
            }
            if (!pellotLocation[getMoveLocationX()][getMoveLocationY()] && (!snakeLocation[getMoveLocationX()][getMoveLocationY()] || !Border[getMoveLocationX()][getMoveLocationY()])) {
                moveSnake(getMoveLocationX(), getMoveLocationY());
            }
        }
        System.out.println("The Game is Over, your score is " + getScore());
    }

    private int getScore() {
        return Score;
    }

    //Future pellet types may eventually increment your score by more than 1
    @SuppressWarnings("SameParameterValue")
    private void addScore(int x) {
        Score += x;
    }

    public void Clean(Graphics g) {
        for (int x = 0; x < areaSize; x++) {
            for (int y = 0; y < areaSize + heightPadding; y++) {
                if (!Border[x][y] && !pellotLocation[x][y]) {
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
        g.drawString("Score:" + getScore(), 450, 525);
    }

    private void endGame() {
        System.out.println("Your game is over!");
        control = false;
        scoreFinal = Score;
        if (scoreFinal > highScore) {
            highScore = scoreFinal;
        }
    }

    private void clearPellots() {
        for (int x = 0; x < areaSize; x++) {
            for (int y = 0; y < areaSize; y++) {
                pellotLocation[x][y] = false;
            }
        }
    }

    private void createNewPellot() {
        int x, y;
        clearPellots();
        do {

            x = generator.nextInt(47) + 1;
            y = generator.nextInt(47) + 1;
        } while (snakeLocation[x][y]);
        pellotLocation[x][y] = true;
    }

    public void drawPellot(Graphics g) {
        for (int x = 0; x < areaSize; x++) {
            for (int y = 2; y < areaSize + heightPadding; y++) {
                if (pellotLocation[x][y]) {
                    g.setColor(Color.BLUE);
                    g.fillRect(x * 10, y * 10, 10, 10);
                }
            }
        }
    }

    private void moveSnakePellot(int x, int y) {
        snakeLocation[x][y] = true;
        start.newlocation(x, y);
        createNewPellot();
        addScore(1);
    }

    private void moveSnake(int a, int b) {
        int x = -1, y = -1;
        if (snakeLocation[end.getX() - 1][end.getY()]) {
            x = end.getX() - 1;
            y = end.getY();
        } else if (snakeLocation[end.getX() + 1][end.getY()]) {
            x = end.getX() + 1;
            y = end.getY();
        } else if (snakeLocation[end.getX()][end.getY() - 1]) {
            x = end.getX();
            y = end.getY() - 1;
        } else if (snakeLocation[end.getX()][end.getY() + 1]) {
            x = end.getX();
            y = end.getY() + 1;
        }
        snakeLocation[end.getX()][end.getY()] = false;
        end.newlocation(x, y);
        snakeLocation[a][b] = true;
        start.newlocation(a, b);


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

    public void buildSnake(Graphics g) {
        for (int x = 0; x <= initialSnakeLength; x++) {
            snakeLocation[10 - x][5] = true;
            g.setColor(Color.red);
            g.fillRect(100 - x * 10, areaSize, 10, 10);
            if (x == initialSnakeLength) {
                end = new LastPart(10 - x, 5);
            }
            if (x == 0) {
                start = new FirstPart(10, 5);
            }

        }
    }

    public void drawSnake(Graphics g) {
        for (int x = 0; x < areaSize; x++) {
            for (int y = 0; y < areaSize + heightPadding; y++) {
                if (snakeLocation[x][y]) {
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

    private int getMoveLocationX() {
        int newLocation = start.getX();
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

    private int getMoveLocationY() {
        int newLocation = start.getY();
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
            Thread.sleep(20);
        } catch (InterruptedException ex) {
            System.out.println("Nap time interrupted! What happened!");
        }

    }
}
