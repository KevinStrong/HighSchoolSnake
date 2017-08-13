package snake;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author KS70326
 */
public class GameFrame extends JFrame 
{
    int a = -1,ax, ay;
    int b = -1, bx, by;
    int c = -1, cx,cy;
    public void addPellot(int x, int y) {
        a = 1;
        ax = x;
        ay = y;
        repaint();
    }
   public  void drawSnake(int x, int y) {
        b = 1;
        bx = x;
        by = y;
        repaint();
    }
   public void eraseSnake(int x, int y) {
        c = 1;
        cx= x;
        cx= y;
        repaint();
    }
    boolean[][] Border = new boolean[50][50];

    public GameFrame() {
        setVisible(true);
        setSize(500, 500);
    }

    @Override
    public void update(Graphics g) {
        super.update(g);
    }
    
    @Override
    public void paint(Graphics g) 
    {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.drawRect(0,0,500,10);
        g.drawRect(0,0,10,500);
        g.drawRect(500,500,500,10);
        g.drawRect(500,500,10,500);
        if(a==1)
        {
           g.setColor(Color.GRAY);
           g.drawRect(ax*10, ay*10, 10, 10);
           a=0;
        }
        if(b==2)
        {
           g.setColor(Color.GREEN);
           g.drawRect(bx*10, by*10, 10, 10); 
           b=0;
        }
        if(c==0)
        {
           g.setColor(Color.WHITE);
           g.drawRect(cx*10,cy*10,10,10);
           c=0;
        }
    }
    
    

}
