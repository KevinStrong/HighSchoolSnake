package com.things;

import java.util.Random;

import static com.display.Game.areaSize;
import static com.display.Game.heightPadding;

public class Pellot {
    private static Random generator = new Random();
    boolean[][] location;

    public Pellot() {
        location = new boolean[areaSize][areaSize + heightPadding];
        int x = generator.nextInt(areaSize - 3) + 1;
        int y = generator.nextInt(areaSize - 3) + 1;
        location[x][y] = true;
    }

    public boolean atLocation(int x, int y) {
        return location[x][y];
    }
}
