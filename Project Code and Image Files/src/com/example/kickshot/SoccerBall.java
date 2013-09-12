package com.example.kickshot;
import java.lang.Math;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

public class SoccerBall
{
    private int pos;    // ball's position on the field.  Should be from -12 to +12
    private int m_team;   // team currently in possession.  
    private int m_resID;
    
    SoccerBall(int initialTeam)
    {
        pos = 0;
        if (Math.abs(initialTeam) != 1)
            initialTeam = 1;


        m_team = initialTeam;
        set_resID();
    }

    public void resetball()
    {
    	pos = 0;	//resets ball to middle of board
    }
    
    public void move(int magnitude)
    {
        pos += magnitude * m_team;

        //ensure that ball stays within bounds of board
        if( Math.abs(pos) > 12 )
            pos = 12*m_team;
    }
    
    public void turnover()
    {
        m_team = m_team*(-1);
        set_resID();
    }

    public int getPos()
    {
        return pos;
    }
    private void set_resID()
    {
        if(m_team==-1)
        {
            m_resID=R.drawable.ballchiphome;
        }
        else
        {
            m_resID=R.drawable.ballchipaway;
        }
    }
    public int resID()
    {
        return m_resID;
    }
    public int getTeam()
    {
    	return m_team;
    }
    
}
