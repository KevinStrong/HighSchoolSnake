package com.things.snake;

/**
 *
 * @author KS70326
 */
public class Tail
{
    boolean[][] location=new boolean[50][52];

    public Tail(int a, int b)
    {
       location[a][b] = true;
    //   System.out.println("Making new end with locations of" + x+ " " + y);
    }
    public void clearlocation()
    {
        for(int x =0;x<50;x++)
           for(int y=0;y<52;y++)
               location[x][y]=false;
    }
    public void newlocation(int a,int b)
    {
        clearlocation();
        location[a][b] = true;
    }
    public int getX()
    {
        int k = -1;
        for(int x=0;x<50;x++)
           for(int y=0;y<52;y++)
               if(location[x][y])
                    k = x;
        return k;
    }
    public int getY()
    {
        int k = -1;
        for(int x=0;x<50;x++)
           for(int y=0;y<52;y++)
               if(location[x][y])
                    k = y;
        return k;
    }
}