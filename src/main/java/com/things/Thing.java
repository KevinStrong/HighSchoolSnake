package com.things;

import com.concepts.PointLocation;

import java.awt.*;
import java.util.Collection;

public interface Thing {

    public void draw(Graphics aGraphics);

    public Collection<PointLocation> getLocations();

    public boolean doesCollide(Collection<PointLocation> aLocations);

}
