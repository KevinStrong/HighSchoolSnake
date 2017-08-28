package com.things;

import java.awt.*;
import java.util.Random;

import static com.display.Game.areaSize;
import static com.display.Game.heightPadding;

public class Pellet {
    private static Random generator = new Random();
    boolean[][] location;
    int x;
    int y;

    public Pellet() {
        location = new boolean[areaSize][areaSize + heightPadding];
        x = generator.nextInt(areaSize - 3) + 1;
        y = generator.nextInt(areaSize - 3) + 1;
    }

    public boolean atLocation(int aX, int aY) {
        return x == aX && y == aY;
    }

    public void drawPellot(Graphics g) {
        for (int x = 0; x < areaSize; x++) {
            for (int y = 2; y < areaSize + heightPadding; y++) {
                if (atLocation(x, y)) {
                    g.setColor(Color.BLUE);
                    g.fillRect(x * 10, y * 10, 10, 10);
                }
            }
        }
    }

}
