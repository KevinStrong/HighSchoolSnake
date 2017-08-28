package com.display;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import static com.display.Game.*;

/**
 * @author KS70326
 */
public class GameFrame extends Frame {

    public GameFrame(KeyListener l) {

        setSize(500, 550);
        setVisible(true);

        addWindowListener(new CloseWindowAdapter());
        addKeyListener(l);
    }

    BufferStrategy strategy = null;

    @Override
    public void paint(Graphics g) {
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
    public void update(Graphics g) {
        paint(g);
    }

    public void draw(Graphics aGraphics) {
        getGame().drawStuff(aGraphics);
        aGraphics.dispose();
    }

    private class CloseWindowAdapter extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }
}
