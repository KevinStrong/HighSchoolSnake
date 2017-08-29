package com.display;

import com.things.Border;
import com.things.Pellet;
import com.things.snake.Snake;

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
    protected Snake snake;

    private boolean isPlayingGame = true;
    private ScoreBoard scoreBoard;



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
            delay();
            if (isCollision()) {
                endGame();
            }
            if (isEatingPellot()) {
                moveSnakePellot();
            } else {
                getSnake().moveSnake(  );
            }
        }
        System.out.println("The Game is Over, your score is " + scoreBoard.getCurrentScore());
    }

    public void drawObjects(Graphics aGraphics) {
        border.buildBorder(aGraphics);
        snake.drawSnake(aGraphics);
        currentPellet.drawPellet(aGraphics);
        scoreBoard.drawScore(aGraphics);
    }

    private Border createBorder(GameFrame frame) {
        return new Border(frame.getGraphics());
    }

    private boolean isEatingPellot() {
        return currentPellet.atLocation(getSnake().getNextHorizontalLocation(),
                getSnake().getNextVerticalLocation(  ));
    }

    private boolean isCollision() {
        return border.isCollision(getSnake())
                || getSnake().isCollision(getSnake());
    }

    private void endGame() {
        isPlayingGame = false;
        scoreBoard.endGame();
    }

    private void createNewPellet() {
        currentPellet = new Pellet();
    }

    private void moveSnakePellot() {
        //Currently moveSnakePellot also moves the snake (but omits moving the tail, that is how the snake grows!)
        getSnake().getSnakeLocation()[getSnake().getNextHorizontalLocation()]
                [getSnake().getNextVerticalLocation()] = true;
        getSnake().getHead().newlocation(getSnake().getNextHorizontalLocation(),
                getSnake().getNextVerticalLocation());
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
