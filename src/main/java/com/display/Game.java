package com.display;

import com.things.Border;
import com.things.Pellet;
import com.things.snake.SnakeLocation;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Kevin Strong
 */
public class Game {

    public static final int areaSize = 50;
    public static final int heightPadding = 2;
    private static long timeOfLastSleep;
    private Border border;
    private Pellet currentPellet;
    protected SnakeLocation snakeLocation;

    private boolean isPlayingGame = true;
    private int currentScore = 0;
    private int highScore;

    public Game() {
        snakeLocation = new SnakeLocation();
        GameFrame frame = new GameFrame(this);
        border = createBorder(frame);
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
                getSnakeLocation().moveSnake(  );
            }
        }
        System.out.println("The Game is Over, your score is " + getCurrentScore());
    }

    public void drawObjects(Graphics aGraphics) {
        border.buildBorder(aGraphics);
        snakeLocation.drawSnake(aGraphics);
        currentPellet.drawPellet(aGraphics);
        drawScore(aGraphics);
    }

    private Border createBorder(GameFrame frame) {
        return new Border(frame.getGraphics());
    }

    private boolean isEatingPellot() {
        return currentPellet.atLocation(getSnakeLocation().getNextHorizontalLocation(),
                getSnakeLocation().getNextVerticalLocation(  ));
    }

    private boolean isCollision() {
        return border.isCollision(getSnakeLocation())
                || getSnakeLocation().isCollision(getSnakeLocation());
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
        isPlayingGame = false;
        if (getCurrentScore() > highScore) {
            highScore = getCurrentScore();
        }
    }

    private void createNewPellet() {
        currentPellet = new Pellet();
    }

    private void moveSnakePellot() {
        //Currently moveSnakePellot also moves the snake (but omits moving the tail, that is how the snake grows!)
        getSnakeLocation().getSnakeLocation()[getSnakeLocation().getNextHorizontalLocation()]
                [getSnakeLocation().getNextVerticalLocation()] = true;
        getSnakeLocation().getHead().newlocation(getSnakeLocation().getNextHorizontalLocation(),
                getSnakeLocation().getNextVerticalLocation());
        createNewPellet();
        addScore(1);
    }

    @SuppressWarnings("empty-statement")
    private static void delay() {
        long timeElapsed = System.currentTimeMillis() - timeOfLastSleep;
        if (50 > timeElapsed) {
            try {
                Thread.sleep(50 - timeElapsed);
            } catch (InterruptedException ex) {
                System.out.println("Nap time interrupted! What happened!");
            }
        }
        timeOfLastSleep = System.currentTimeMillis();

    }

    public SnakeLocation getSnakeLocation() {
        return snakeLocation;
    }
}
