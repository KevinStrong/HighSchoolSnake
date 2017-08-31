package com.things.snake;

import com.concepts.PointLocation;
import com.things.Thing;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

public class Snake extends KeyAdapter implements Thing {

    private static int initialSnakeLength = 9;

    final Queue<PointLocation> snakeLocations;
    PointLocation pastLocation;
    private static DIRECTION direction;
    private PointLocation currentHead;

    public enum DIRECTION {
        UP,
        DOWN,
        RIGHT,
        LEFT;
    }

    public Snake() {
        direction = DIRECTION.RIGHT;
        snakeLocations = new LinkedList<>();
        buildSnake();
    }

    public boolean selfCollision() {
        int numberOfMatchesOnHeadLocation = 0;
        for (PointLocation snakeLocation : snakeLocations) {
            if (currentHead.equals(snakeLocation)) {
                numberOfMatchesOnHeadLocation++;
            }
        }
        return numberOfMatchesOnHeadLocation > 1;
    }

    private void buildSnake() {
        PointLocation location = null;
        for (int x = 0; x <= initialSnakeLength; x++) {
            location = new PointLocation(x+ 2, 5);
            snakeLocations.add(location);
        }
        currentHead = location;
    }

    @Override
    public void draw(Graphics g) {
        synchronized (snakeLocations) {
            for (PointLocation snakeLocation : snakeLocations) {
                snakeLocation.draw( g, Color.magenta );
            }
            pastLocation.draw(g, Color.white);
        }
    }

    public void moveSnake() {
        synchronized (snakeLocations) {
            moveAndGrowSnake();
            pastLocation = snakeLocations.remove();
        }
    }

    public void moveAndGrowSnake() {
        currentHead = currentHead.createAdjacentLocation(direction);
        snakeLocations.add(currentHead);
    }

    @Override
    public Collection<PointLocation> getLocations() {
        return Arrays.asList( snakeLocations.toArray( new PointLocation[snakeLocations.size()] ));
    }

    @Override
    public boolean doesCollide(Collection<PointLocation> aLocations) {
        boolean doesCollide = false;
        for (PointLocation aLocation : aLocations) {
            //Currently only check the head, will need to check all snake locations if other objects start moving.
            if (aLocation.equals(currentHead)) {
                doesCollide = true;
                break;
            }
        }
        return doesCollide;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            direction = DIRECTION.LEFT;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direction = DIRECTION.RIGHT;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            direction = DIRECTION.UP;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            direction = DIRECTION.DOWN;
        }
    }
}
