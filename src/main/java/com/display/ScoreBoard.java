package com.display;

import java.awt.*;

public class ScoreBoard {

    private int score;
    private int highScore;

    public ScoreBoard() {
        score = 0;
    }

    public void drawScore(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(400, 500, 100, 75);
        g.setColor(Color.black);
        g.drawString("currentScore:" + getCurrentScore(), 450, 525);
    }

    public int getCurrentScore() {
        return score;
    }

    public void incrementScore(int aPoints) {
        score += aPoints;
    }

    public void endGame() {
        if (highScore < score) {
            highScore = score;
        }
    }
}
