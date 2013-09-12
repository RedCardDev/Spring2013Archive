package com.example.kickshot;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.widget.ImageView;

public class Card {
	
	private double m_rollMultiplier;
    private int m_moveAmount;
	private boolean m_possessionChange;
    private int m_resID;
    private boolean m_rollDice;
    private int m_gameMode;
    public boolean m_defensive;	//this should be some sort of enumerator (Offense, defense, neutral)
    
	public Card()
	{
		this.m_rollMultiplier = 1;
        this.m_moveAmount = 0;
        this.m_gameMode = 1;
        this.m_resID = 0;
        this.m_possessionChange = false;
        this.m_rollDice = false;
        this.m_defensive = false;	//sets defensive attribute to false
	}

    public void set_resID(int ID)
    {
        m_resID = ID;
    }
    public int resID()
    {
        return m_resID;
    }

    public void set_rollMultiplier(double mult)
    {
        m_rollMultiplier = mult;
    }
    public double rollMultiplier()
    {
        return m_rollMultiplier;
    }

    public void set_moveAmount(int amount)
    {
        m_moveAmount = amount;
    }
    public int moveAmount()
    {
        return m_moveAmount;
    }

    public void set_possessionChange(boolean change)
    {
        m_possessionChange = change;
    }
	public boolean possessionChange()
	{
		return m_possessionChange;
	}

    public void set_rollDice(boolean condition)
    {
        m_rollDice = condition;
    }
    public boolean rollDice()
    {
        return m_rollDice;
    }

    public void set_gameMode(int mode)
    {
        m_gameMode = mode;
    }
    public int gameMode()
    {
        return m_gameMode;
    }
}
