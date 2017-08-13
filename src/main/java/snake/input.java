package snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Kevin Strong
 */
class input 
implements KeyListener, MouseListener 
{
        boolean movingLeft = false;
     	boolean movingRight = false;
     	boolean movingUp = false;
     	boolean movingDown = false;
	public void init() 
	{
     	addKeyListener(this);
        addMouseListener(this);
  	}
  		
     public boolean movingRight() {
        return movingRight;
    }

     public boolean movingLeft() {
        return movingLeft;
    }
     public boolean movingUp() {
       return movingUp;
    }
     public boolean movingDown() {
       return movingDown;
     }
  	//Paul's part
  	
  	public void keyPressed( KeyEvent e ) 
  	{ 
  	if(e.getKeyCode() == KeyEvent.VK_LEFT)
            {
                movingLeft=true;
                movingRight=false;
                movingUp=false;
                movingDown=false;
            }
  	if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {	movingLeft=false;
                movingRight=true;
                movingUp=false;
                movingDown=false;
        }
  	if(e.getKeyCode() == KeyEvent.VK_UP)
        {	movingLeft=false;
                movingRight=false;
                movingUp=true;
                movingDown=false;
        }
  	if(e.getKeyCode() == KeyEvent.VK_DOWN)
        {	movingLeft=false;
                movingRight=false;
                movingUp=false;
                movingDown=true;
  	}
        }
  		
  	public void keyReleased( KeyEvent e ) { }
 	public void keyTyped( KeyEvent e ) { }
 	public void mouseEntered( MouseEvent e ) { }
  	public void mouseExited( MouseEvent e ) { }
   	public void mousePressed( MouseEvent e ) { }
   	public void mouseReleased( MouseEvent e ) { }
   	public void mouseClicked( MouseEvent e ) { }

    private void addKeyListener(input aThis) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void addMouseListener(input aThis) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
