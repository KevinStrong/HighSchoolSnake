package snake;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kevin Strong
 */
class Game {
    int Score = 0;
    int scoreFinal;
    private int highScore;

    public Game() {
    }
    public int getScore()
    {
        return Score;
    }
    public void addScore(int x)
    {
        Score+= x;
    }

    void endGame() 
    {
        
         scoreFinal = Score;
         if(scoreFinal > highScore)
            highScore=scoreFinal;
    }
    public int getScoreFinal()
    {
        return scoreFinal;
    }

}
