package com.santiago.javius.flappybird;

import com.badlogic.gdx.math.Rectangle;

public class Score
{
    public int myScore;
    private Rectangle bounds;

    public Score()
    {
        myScore = 0;
       // bounds = new Rectangle()
    }

    public void playerScored()
    {
        myScore += 1;
    }

    public int getScore()
    {
        return myScore;
    }
}
