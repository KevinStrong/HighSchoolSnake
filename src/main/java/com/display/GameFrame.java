package com.display;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

/**
 * @author KS70326
 */
public class GameFrame extends Frame {

    private final Game mainGame;

    public GameFrame(Game aGame) {

        setSize(530, 600);
        setVisible(true);

        addWindowListener(new CloseWindowAdapter());
        addKeyListener(aGame.getSnake());
        mainGame = aGame;
    }

    BufferStrategy strategy = null;

    @Override
    public void paint(Graphics aGraphics) {
        if (strategy == null) {
            createBufferStrategy(2);
            strategy = getBufferStrategy();
        }
        super.paint(getBufferStrategy().getDrawGraphics());
        draw(getBufferStrategy().getDrawGraphics());

        getBufferStrategy().show();
        repaint();
    }

    @Override
    public void update(Graphics aGraphics) {
        paint(aGraphics);
    }

    public void draw(Graphics aGraphics) {
        mainGame.drawObjects(aGraphics);
        aGraphics.dispose();
    }

    private class CloseWindowAdapter extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }
}
