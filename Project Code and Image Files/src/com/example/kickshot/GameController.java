package com.example.kickshot;

/**
 * Created with IntelliJ IDEA.
 * User: coleman
 * Date: 10/11/12
 * Time: 10:08 PM
 * To change this template use File | Settings | File Templates.
 */

public class GameController {

    private Player[] m_players;
    private int m_possession, m_moveAmount, m_gameMode, m_numRefCards;
    private boolean turnover;
    private double m_rollMultiplier;
    private Deck m_refCards;
    private int[] m_roll;
    public int[] m_score;
    private boolean posC;
    

    public GameController(int numPlayers, int handSize)
    {
        //set number of players
        m_players = new Player[numPlayers];
        //set teams: -1 = home, 1 = away
        for(int i = 0; i < numPlayers; i++)
        {
            int team = 1;
            if (i % 2 == 0)
                team = -1;
            m_players[i] = new Player(42, handSize, team);
        }

        m_possession = -1;
        m_rollMultiplier = 0.0;
        m_moveAmount = 0;
        m_gameMode = 1;
        m_numRefCards = 0;
        m_refCards = new Deck(m_numRefCards);
        m_roll = new int[2];
        m_roll[0] = 1;
        m_roll[1] = 1;
        m_score = new int[2];
        m_score[0] = 0;
        m_score[1] = 0;
    	posC = false;

    }

    public void setPossession(int possession)
    {
        m_possession = possession;
    }
    public int possession()
    {
        return m_possession;
    }

    //setters for the card class to use
    public void setM_moveAmount(int spaces)
    {
        m_moveAmount = spaces;
    }

    public void setM_multiplier(double multiplier)
    {
        m_rollMultiplier = multiplier;
    }

    public void setM_gameMode(int mode)
    {
        m_gameMode = mode;
    }

    //accessors for main controller
    public int getM_moveAmount()
    {
        return m_moveAmount;
    }

    public double getM_multiplier()
    {
        return m_rollMultiplier;
    }

    public boolean poschange()
    {
    	return posC;
    }
//Evaluate Card
    public void evaluateCard(Card card, int team, int boardteam)
    {
        Dice dice;
//        m_possession = ballteam;
        if(card.possessionChange())
        {
            m_possession *= -1;
        }
//    	posC = card.possessionChange();
        m_gameMode = card.gameMode();
        m_rollMultiplier = card.rollMultiplier();

        if(card.rollDice())
        {
            int i[];
            dice = new Dice();
            i = dice.roll();
            m_roll = i;

            int num;
            num = Math.max(i[0], i[1]);

            if(card.rollMultiplier() != 1)
            {
                num *= card.rollMultiplier();
                if (num == 0)
                    num++;
            }
        
            m_moveAmount = num;
            if(i[0] == i[1])
                m_moveAmount++; //bonus for rolling a double

    		if(card.gameMode() == 2 || card.gameMode() == 3)
    		{
            	m_moveAmount *= -1;	//if goalshot(L/R) card, move in opposite direction            		
    		}//end if

    		if(card.gameMode() == 10)	//if DFK ref card
            {
        		m_possession *= -1;
        		if(boardteam == 1)
        		{
                	m_moveAmount *= -1;	//if DFK, swap possession, and move in opposite direction            		
        		}//end if
            	if(m_possession != team)	//is this ALWAYS true?
            	{            		
            		m_possession *= -1;
            	}//end if
        	}//end if
        }
        else
        {
            m_moveAmount = card.moveAmount();
        }
    }
//end evaluate card

    public int[] getRoll()
    {
    	return m_roll;
    }
    
    public int[] getScore()
    {
        return m_score;
    }
}
