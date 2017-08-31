package com.display;

import java.awt.*;

public class ScoreBoard {

    private int previousScore;
    private int score;
    private int highScore;

    public ScoreBoard() {
        score = 0;
    }

    public void drawScore(Graphics g) {
        g.setColor(Color.white);
        g.drawString("currentScore:" + getPreviousScore(), 400, 575);
        g.setColor(Color.black);
        g.drawString("currentScore:" + getCurrentScore(), 400, 575);
    }

    public int getCurrentScore() {
        return score;
    }

    private int getPreviousScore() {
        return previousScore;
    }

    public void incrementScore(int aPoints) {
        previousScore = score;
        score += aPoints;
    }

    public void endGame() {
        if (highScore < score) {
            highScore = score;
        }
    }
}
