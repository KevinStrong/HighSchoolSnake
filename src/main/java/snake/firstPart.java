package snake;

/**
 *
 * @author Kevin Strong
 */
class firstPart {
    boolean[][] location=new boolean[50][50];

    public firstPart(int x,int y) 
    {
       location[x][y] = true;
    }
    public void clearlocation()
    {
        for(int x =0;x<50;x++)
           for(int y=0;y<50;y++)
               location[x][y]=false;
    }
    public void newlocation(int x,int y)
    {
        clearlocation();
        location[x][y] = true;
    }
    public int getX()
    {
        for(int x =0;x<50;x++)
           for(int y=0;y<50;y++)
               if(location[x][y]=true)
                   return x;
        return -1;
    }
    public int getY()
    {
        for(int x =0;x<50;x++)
           for(int y=0;y<50;y++)
               if(location[x][y]=true)
                   return y;
        return -1;
    }
}
