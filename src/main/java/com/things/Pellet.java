package com.things;

import java.awt.*;
import java.util.Random;

import static com.display.Game.areaSize;
import static com.display.Game.heightPadding;

public class Pellet {
    private static Random generator = new Random();
    int x;
    int y;

    public Pellet() {
        x = generator.nextInt(areaSize - 3) + 1;
        y = generator.nextInt(areaSize - 3) + 1;
    }

    public boolean atLocation(int aX, int aY) {
        return x == aX && y == aY;
    }

    public void drawPellot(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x * 10, y * 10, 10, 10);
    }

}
