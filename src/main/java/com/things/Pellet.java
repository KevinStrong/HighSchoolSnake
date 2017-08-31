package com.things;

import com.concepts.PointLocation;

import java.awt.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import static com.display.Game.areaSize;

public class Pellet extends PointLocation implements Thing {
    private static Random generator = new Random();

    public Pellet() {
        super(generator.nextInt(areaSize - 3) + 1, generator.nextInt(areaSize - 3) + 1);
    }

    @Override
    public boolean doesCollide(Collection<PointLocation> aLocations) {
        boolean isCollision = false;
        for (PointLocation aLocation : aLocations) {
            if (aLocation.atLocation(getX(), getY())) {
                isCollision = true;
                break;
            }
        }
        return isCollision;
    }

    @Override
    public Collection<PointLocation> getLocations() {
        return Collections.singletonList(this);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(getX() * 10, getY() * 10, 10, 10);
    }
}
