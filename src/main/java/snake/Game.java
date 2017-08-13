package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.*;

/**
 *
 * @author Kevin Strong
 */
class Game implements KeyListener {

    Random generator = new Random();
    boolean[][] Border = new boolean[50][52];
    boolean[][] snakeLocation = new boolean[50][52];
    boolean[][] pellotLocation = new boolean[50][52];
    boolean[][] snakeHead = new boolean[50][52];
    int[] snakeOrder = new int[1000];
    int snakeLength = 5;
    lastPart end;
    firstPart start;
    boolean control = true;
    GameFrame Frame;
    int Score = 0;
    int scoreFinal;
    private int highScore;
    public static Game instance;
    int direction = 2;

    public Game() {

        instance = this;
        Frame = new GameFrame(this);
        buildBorder(Frame.getGraphics());
        buildSnake(Frame.getGraphics());
        createNewPellot();
        while (control == true) {
            delay();
            buildBorder(Frame.getGraphics());
            if (pellotLocation[getMoveLocationX()][getMoveLocationY()] == true) {
                moveSnakePellot(getMoveLocationX(), getMoveLocationY());
            }
            if (snakeLocation[getMoveLocationX()][getMoveLocationY()] == true || Border[getMoveLocationX()][getMoveLocationY()] == true) {
                endGame();
            }
            if (pellotLocation[getMoveLocationX()][getMoveLocationY()] == false && (snakeLocation[getMoveLocationX()][getMoveLocationY()] == false || Border[getMoveLocationX()][getMoveLocationY()] == false)) {
                moveSnake(getMoveLocationX(), getMoveLocationY());
            }
        }
        System.out.println("The Game is Over, your score is " + getScore());
    }

    public int getScore() {
        return Score;
    }

    public void addScore(int x) {
        Score += x;
    }

    void Clean(Graphics g) {
        for (int x = 0; x < 50; x++) {
            for (int y = 0; y < 52; y++) {
                if (Border[x][y] == false && pellotLocation[x][y] == false) {
                    g.setColor(Color.white);
                    g.fillRect(x * 10, y * 10, 10, 10);
                }
            }
        }
    }

    void drawScore(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(400, 500, 100, 75);
        g.setColor(Color.black);
        g.drawString("Score:" + getScore(), 450, 525);
    }

    void endGame() {
        System.out.println("Your game is over!");
        control = false;
        scoreFinal = Score;
        if (scoreFinal > highScore) {
            highScore = scoreFinal;
        }
    }

    private void clearPellots() {
        for (int x = 0; x < 50; x++) {
            for (int y = 0; y < 50; y++) {
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
        } while (snakeLocation[x][y] == true);
        pellotLocation[x][y] = true;
    }

    public void drawPellot(Graphics g) {
        for (int x = 0; x < 50; x++) {
            for (int y = 2; y < 52; y++) {
                if (pellotLocation[x][y] == true) {
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
        if (snakeLocation[end.getX() - 1][end.getY()] == true) {
            x = end.getX() - 1;
            y = end.getY();
        } else if (snakeLocation[end.getX() + 1][end.getY()] == true) {
            x = end.getX() + 1;
            y = end.getY();
        } else if (snakeLocation[end.getX()][end.getY() - 1] == true) {
            x = end.getX();
            y = end.getY() - 1;
        } else if (snakeLocation[end.getX()][end.getY() + 1] == true) {
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
        for (int x = 0; x < 50; x++) {
            Border[x][2] = true;
            Border[x][51] = true;
            Border[0][x + 2] = true;
            Border[49][x + 2] = true;
        }
    }

    public void buildSnake(Graphics g) {
        snakeHead[10][5] = true;
        for (int x = 0; x <= snakeLength; x++) {
            snakeLocation[10 - x][5] = true;
            g.setColor(Color.red);
            g.fillRect(100 - x * 10, 50, 10, 10);
            if (x == snakeLength) {
                end = new lastPart(10 - x, 5);
            }
            if (x == 0) {
                start = new firstPart(10, 5);
            }

        }
    }

    public void drawSnake(Graphics g) {
        for (int x = 0; x < 50; x++) {
            for (int y = 0; y < 52; y++) {
                if (snakeLocation[x][y] == true) {
                    g.setColor(Color.magenta);
                    g.fillRect(x * 10, y * 10, 10, 10);
                }
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            direction = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direction = 2;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            direction = 3;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            direction = 4;
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    private int getMoveLocationX() {
        switch (direction) {
            case 1:
                return start.getX() - 1;
            case 2:
                return start.getX() + 1;
            case 3:
                return start.getX();
            case 4:
                return start.getX();
        }
        return start.getX() + 1;
    }

    private int getMoveLocationY() {
        switch (direction) {
            case 1:
                return start.getY();
            case 2:
                return start.getY();
            case 3:
                return start.getY() - 1;
            case 4:
                return start.getY() + 1;
        }
        return start.getY();
    }

    public static Game getGame() {
        return instance;
    }

    @SuppressWarnings("empty-statement")
    private static void delay() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException ex) {

        }

    }
}
