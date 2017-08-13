package snake;

import java.util.Random;

/**
 *
 * @author KS70326
 */
public class Main 
{
 
 Random generator = new Random();
 boolean[][] Border = new boolean[50][50];
 boolean[][] snakeLocation =  new boolean[50][50];
 boolean[][] pellotLocation = new boolean[50][50];
 boolean[][] snakeHead = new boolean[50][50];
 int[] snakeOrder = new int[1000];
 int snakeLength = 5;
 lastPart end; firstPart start;
 boolean k = true;
 GameFrame Frame;
    
    
    public void main(String[] args) 
    {   
        Game aGame = new Game();
        buildBorder();
        buildSnake();
        Frame = new GameFrame();
        while( k=true)
        {
            if(pellotLocation[getMoveLocationX()][getMoveLocationY()] == true);
            {
                moveSnakePellot(getMoveLocationX(),getMoveLocationY());
            }
            if(snakeLocation[getMoveLocationX()][getMoveLocationY()] == true || Border[getMoveLocationX()][getMoveLocationY()] == true);
            {
                k=false;
                aGame.endGame();
            }
            if(pellotLocation[getMoveLocationX()][getMoveLocationY()] == false && (snakeLocation[getMoveLocationX()][getMoveLocationY()] == false || Border[getMoveLocationX()][getMoveLocationY()] == false));
            {
                moveSnake(getMoveLocationX(),getMoveLocationY());
            }  
        }   
        System.out.println("The Game is Over, your score is Game.getScore");
    }

    private void clearPellots() {
        for(int x =0;x<50;x++)
           for(int y=0;y<50;y++)
               pellotLocation[x][y]=false;
    }

    private void createNewPellot() 
    {
        clearPellots();
        int x = generator.nextInt(48) + 1;
        int y = generator.nextInt(48) + 1;
        Frame.addPellot(x,y);
        pellotLocation[x][y]=true;
    }
    private void moveSnakePellot(int x,int y) 
    {
        snakeLocation[x][y] = true;
        Frame.drawSnake(x,y);
        createNewPellot();
    }
    private void moveSnake(int a, int b)
    {
        int x = -1, y = -1;
       if(snakeLocation[end.getX()-1][end.getY()]=true)
       {
           x=end.getX()-1; 
           y=end.getY();
       } 
       else if(snakeLocation[end.getX()+1][end.getY()-1]=true)
       {
           x=end.getX()+1; 
           y=end.getY()-1;
       } 
       else if(snakeLocation[end.getX()-1][end.getY()-1]=true)
       {
           x=end.getX()-1; 
           y=end.getY()-1;
       }
        else if(snakeLocation[end.getX()+1][end.getY()+1]=true)
       {
           x=end.getX()+1; 
           y=end.getY()+1;
       } 
         Frame.eraseSnake(end.getX(),end.getY());
         snakeLocation[x][y]=false;
         end.newlocation(x,y);
         snakeLocation[a][b] = true;
         Frame.drawSnake(a, b);
    }
    private void buildBorder() 
    {
        for(int x=0;x<50;x++)
        {
               Border[x][0]= true;
               Border[x][49]=true;
               Border[0][x]=true;
               Border[49][x]=true;
        }
    }
    @SuppressWarnings("empty-statement")
    private static void delay()
    {
        for(int x = 0; x<10000000;x++);
            
    }
   
    private void buildSnake() 
    {
        snakeHead[10][3]=true;
        for(int x=0;x<=snakeLength;x++)
        {
            snakeLocation[10-x][3] = true;
            Frame.drawSnake(x,3);
            if(x==snakeLength)
                end = new lastPart(10-x,3);
            if(x==0)
                start = new firstPart(0,3);
        }
    }
private int getMoveLocationX() 
    {
        if(input.movingRight())
            return (start.getX()) + 1;
        if(input.movingleft())
            return (start.getX()) - 1;
        return -1;
    }
    private int getMoveLocationY() 
    {
        if(input.movingUp())
            return (start.getY()) - 1;
        if(input.movingleft())
            return (start.getY()) + 1;
        return -1;
    }
}
