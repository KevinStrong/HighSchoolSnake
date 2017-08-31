package com.things;

import com.concepts.PointLocation;
import com.things.snake.Snake;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

import static com.display.Game.areaSize;
import static com.display.Game.heightPadding;

public class Border implements Thing {

    Collection<PointLocation> borderPoints;

    public Border(Graphics aGraphics) {
        borderPoints = new ArrayList<>();
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
            borderPoints.add(new PointLocation(x, heightPadding));
            borderPoints.add(new PointLocation(x, areaSize + 1));
            borderPoints.add(new PointLocation(0, x + heightPadding));
            borderPoints.add(new PointLocation(areaSize - 1, x + heightPadding));
        }
    }

    @Override
    public boolean doesCollide(Collection<PointLocation> aLocations) {
        boolean isCollision = false;
        for (PointLocation aLocation : aLocations) {
            for (PointLocation borderPoint : borderPoints) {
                if (borderPoint.equals(aLocation)) {
                    isCollision = true;
                    break;
                }
            }
        }
        return isCollision;
    }

    @Override
    public Collection<PointLocation> getLocations() {
        return borderPoints;
    }

}
