package com.example.kickshot;
import java.util.Vector;
import java.lang.Math;
import android.graphics.BitmapFactory;

public class Deck {
	private Vector<Card> m_deck;
	public int counter;
	
	Deck(int deckSize)
	{
		m_deck = new Vector<Card>(deckSize);
		counter = deckSize;
	}


    public void addCard(Card newCard)
	{
		m_deck.add(newCard);
	}

    public Card drawCard()
	{
        //return a random card from the stack
        int index = (int) (Math.random() * 10000) % m_deck.size();
		return m_deck.remove(index);
	}

    public Card getCard(int index)
    {
    	Card c;
    	c =  m_deck.remove(index);
    	m_deck.add(c);
    	return c;
    }
    
    public Card removeIndex(int index)
    {
        return m_deck.remove(index);
    }

    public Vector<Card> getDeck()
    {
    	return this.m_deck;
    }
    public int getSize()
    {
        return m_deck.size();
    }

    public Vector<Card> getCards()
    {
        return m_deck;
    }
}
