package com.display;

import java.awt.*;

import static com.display.Game.areaSize;

public class ScoreBoard {

    private int previousScore;
    private int score;
    private int highScore;

    public ScoreBoard() {
        score = 0;
    }

    public void drawScore(Graphics g) {
        g.setColor(Color.white);
        int horizontalLocation = areaSize * 10 - 100;
        int verticalLocation = areaSize * 10 + 75;
        g.drawString("currentScore:" + getPreviousScore(), horizontalLocation, verticalLocation);
        g.setColor(Color.black);
        g.drawString("currentScore:" + getCurrentScore(), horizontalLocation, verticalLocation);
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
