package com.display;

import com.things.Border;
import com.things.Pellet;
import com.things.snake.Snake;

import java.awt.Graphics;
import java.util.Collections;

/**
 * @author Kevin Strong
 */
public class Game {

    public static final int areaSize = 50;
    public static final int heightPadding = 2;
    private static long timeOfLastSleep;
    private Border border;
    private Pellet currentPellet;
    protected Snake snake;
    private ScoreBoard scoreBoard;

    private boolean isPlayingGame = true;

    public Game() {
        scoreBoard = new ScoreBoard();
        snake = new Snake();
        GameFrame frame = new GameFrame(this);
        border = createBorder(frame);
        createNewPellet();
        mainGameLoop();
    }

    private void mainGameLoop() {
        while (isPlayingGame) {
            if (getSnake().doesCollide(currentPellet.getLocations())) {
                eatPellet();
            } else  {
                getSnake().moveSnake();
            }
            if (isCollision()) {
                endGame();
            }
            delay();
        }
        System.out.println("The Game is Over, your score is " + scoreBoard.getCurrentScore());
    }

    public void drawObjects(Graphics aGraphics) {
        border.draw(aGraphics);
        snake.draw(aGraphics);
        currentPellet.draw(aGraphics);
        scoreBoard.drawScore(aGraphics);
    }

    private Border createBorder(GameFrame frame) {
        return new Border(frame.getGraphics());
    }

    private boolean isCollision() {
        return getSnake().doesCollide(border.getLocations())
                || getSnake().selfCollision();
    }

    private void endGame() {
        isPlayingGame = false;
        scoreBoard.endGame();
    }

    private void createNewPellet() {
        currentPellet = new Pellet();
    }

    private void eatPellet() {
        getSnake().moveAndGrowSnake();
        createNewPellet();
        scoreBoard.incrementScore(1);
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

    public Snake getSnake() {
        return snake;
    }
}
