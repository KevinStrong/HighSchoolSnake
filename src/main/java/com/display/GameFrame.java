package com.display;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import static com.display.Game.*;

/**
 *
 * @author KS70326
 */
public class GameFrame extends Frame {

    public GameFrame(KeyListener l) {

        setSize(500, 550);
        setVisible(true);

        addWindowListener(new adapter());
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
        draw(strategy.getDrawGraphics());

        strategy.show();
        repaint();
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    public void draw(Graphics g) {
        getGame().Clean(g);
        getGame().buildBorder(g);
        getGame().drawSnake(g);
        getGame().drawPellot(g);
        getGame().drawScore(g);
        g.dispose();
    }

    public class adapter extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }
}
