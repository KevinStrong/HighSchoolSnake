package com.things;

import com.concepts.PointLocation;

import java.awt.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import static com.display.Game.areaSize;

public class Pellet implements Thing {

    private final PointLocation pelletLocation;
    private static Random generator = new Random();

    public Pellet() {
        pelletLocation = new PointLocation(generator.nextInt(areaSize - 3) + 1, generator.nextInt(areaSize - 3) + 1);
    }

    @Override
    public boolean doesCollide(Collection<PointLocation> aLocations) {
        boolean isCollision = false;
        for (PointLocation aLocation : aLocations) {
            if (pelletLocation.equals(aLocation)) {
                isCollision = true;
                break;
            }
        }
        return isCollision;
    }

    @Override
    public Collection<PointLocation> getLocations() {
        return Collections.singletonList(pelletLocation);
    }

    @Override
    public void draw(Graphics g) {
        pelletLocation.draw(g, Color.BLUE);
    }
}
