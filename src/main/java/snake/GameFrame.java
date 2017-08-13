package snake;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

/**
 *
 * @author KS70326
 */
public class GameFrame extends Frame {

    int q = 1;

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
        double k = 0;
        //while (true) {
            if (q == 1) {
                Game.getGame().buildSnake(g);
                q++;
            }
            Game.getGame().Clean(g);
            Game.getGame().buildBorder(g);
            Game.getGame().drawSnake(g);
            Game.getGame().drawPellot(g);
            Game.getGame().drawScore(g);
            //     repaint();
        g.dispose();
    }

    public class adapter extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }
}
