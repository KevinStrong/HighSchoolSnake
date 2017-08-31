package com.things;

import com.concepts.PointLocation;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

import static com.display.Game.areaSize;

public class Border implements Thing {

    private final Collection<PointLocation> borderPoints;

    public Border(Graphics aGraphics) {
        borderPoints = new ArrayList<>();
        initializeBorderArray();
        draw(aGraphics);
    }

    @Override
    public void draw(Graphics aGraphics) {
        aGraphics.setColor(Color.orange);
        for (PointLocation borderPoint : new ArrayList<>(borderPoints)) {
            borderPoint.draw(aGraphics, Color.orange);
        }
    }

    private void initializeBorderArray() {
        for (int x = 0; x < areaSize; x++) {
            borderPoints.add(new PointLocation(x, 0));
            borderPoints.add(new PointLocation(x, areaSize ));
            borderPoints.add(new PointLocation(0, x ));
            borderPoints.add(new PointLocation(areaSize, x));
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
