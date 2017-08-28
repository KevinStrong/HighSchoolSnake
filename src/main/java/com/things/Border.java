package com.things;

import java.awt.*;

import static com.display.Game.areaSize;
import static com.display.Game.heightPadding;

public class Border {

    private boolean[][] border;

    public Border(Graphics aGraphics) {
        border = new boolean[areaSize][areaSize + heightPadding];
        initializeBorderArray();
        buildBorder(aGraphics);
    }

    public boolean isBorderHere(int x, int y) {
        return border[x][y];
    }

    public void buildBorder(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(0, 20, 500, 10);
        g.fillRect(0, 0, 10, 500);
        g.fillRect(490, 0, 10, 500);
        g.fillRect(0, 490, 500, 10);
    }

    private void initializeBorderArray() {
        for (int x = 0; x < areaSize; x++) {
            border[x][heightPadding] = true;
            border[x][areaSize + 1] = true;
            border[0][x + heightPadding] = true;
            border[areaSize - 1][x + heightPadding] = true;
        }
    }
}
