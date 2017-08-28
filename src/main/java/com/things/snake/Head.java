package com.things.snake;

import static com.display.Game.areaSize;
import static com.display.Game.heightPadding;

/**
 * @author KS70326
 */
public class Head {

    int x;
    int y;

    public Head(int aX, int aY) {
        x = aX;
        y = aY;
        //   System.out.println("Making new start with locations of" + x+ " " + y);
    }

    public void newlocation(int aX, int aY) {
        x = aX;
        y = aY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
