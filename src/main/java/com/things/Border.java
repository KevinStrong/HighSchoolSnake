package com.things;

import com.concepts.PointLocation;
import com.things.snake.Snake;

import java.awt.*;
import java.util.Collection;

import static com.display.Game.areaSize;
import static com.display.Game.heightPadding;

public class Border implements Thing{

    private boolean[][] border;

    public Border(Graphics aGraphics) {
        border = new boolean[areaSize][areaSize + heightPadding];
        initializeBorderArray();
        draw(aGraphics);
    }

    @Override
    public void draw(Graphics g) {
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

    @Override
    public boolean doesCollide(Collection<PointLocation> aLocations) {
        boolean isCollision = false;
        for (int x = 0; x < border.length; x++) {
            for (int y = 0; y < border[x].length; y++) {
                if (border[x][y]) {
                    for (PointLocation aLocation : aLocations) {
                        if (aLocation.atLocation(x, y)) {
                            isCollision = true;
                            break;
                        }
                    }
                }
            }
        }
        return isCollision;
    }

    public boolean isCollision(Snake snake) {
        int x = snake.getNextHorizontalLocation();
        int y = snake.getNextVerticalLocation();
        return border[x][y];
    }
}
