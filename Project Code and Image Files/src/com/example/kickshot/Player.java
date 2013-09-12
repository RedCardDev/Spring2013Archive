package com.example.kickshot;

import java.util.Vector;

/* Player is the logical representation of a player's actions
 * contains a hand of cards and card effect variables
 */
public class Player
{

    private Deck m_deck;
    private Deck m_hand;
    private Deck m_discard;
    private int m_playedCardIndex;
    private int m_team;

    public Player(int maxDeckSize, int maxHandSize, int team)
    {
        m_hand = new Deck(maxHandSize);
        m_deck = new Deck(maxDeckSize);
        m_discard = new Deck(maxDeckSize);
        m_team = team;
        InitDeck(maxDeckSize);
    }

    public int getTeam() {
        return m_team;
    }

    public void InitDeck(int maxDeckSize)
    {	//should set all the cards in the deck to a value
    	for(int i = 0; i < 17; i++)	// number of pass cards
    	{
//            this.addCardtoDeck(new DribbleCard(m_team));
            this.addCardtoDeck(new PassCard(m_team));
            
    	}//end for
    	for(int i = 0; i < 5; i++)	// number of pass cards
    	{
//            this.addCardtoDeck(new DribbleCard(m_team));
            this.addCardtoDeck(new GoalShotRightCard(m_team));
            
    	}//end for
    	for(int i = 0; i < 5; i++)	// number of pass cards
    	{
//            this.addCardtoDeck(new DribbleCard(m_team));
            this.addCardtoDeck(new GoalShotLeftCard(m_team));
            
    	}//end for

    	for(int i = 0; i < 5; i++)	// number of pass cards
    	{
//            this.addCardtoDeck(new DribbleCard(m_team));
            this.addCardtoDeck(new GoalBlockLeftCard(m_team));
            
    	}//end for
    	for(int i = 0; i < 5; i++)	// number of pass cards
    	{
//            this.addCardtoDeck(new DribbleCard(m_team));
            this.addCardtoDeck(new GoalBlockRightCard(m_team));
            
    	}//end for

    	for(int i = 0; i < 5; i++)	// number of pass cards
    	{
//            this.addCardtoDeck(new DribbleCard(m_team));
            this.addCardtoDeck(new InterceptCard(m_team));
            
    	}//end for


//this should probably not be a loop, more of a list of which cards are supposed to be in a deck
    }
    
    public void removeCardfromHand(Card removedcard)
    {	//should remove the specified card from the hand
    	addCardtoDiscard(removedcard);	//adds the card to the discard pile
    	//maybe use a loop to cycle until a card matches the card to remove?
    	
    }
    
    public void addCardtoDeck(Card drawnCard)
    {	// should reaact just like addcard, but moves cards to deck instead
    	m_deck.addCard(drawnCard);		//calls addcard from deck.java
    }
    
    public void addCardtoDiscard(Card drawnCard)
    {	// should reaact just like addcard, but moves cards to discard pile instead
    	m_discard.addCard(this.playCard());		//calls addcard from deck.java	//this is not working....
//    	m_discard.addCard(drawnCard);		//calls addcard from deck.java
//    	this.playCard();		//removes card from hand
    	
    }
    
    //add an external card to the player's hand
    public void addCard(Card drawnCard)
    {
        m_hand.addCard(drawnCard);			//calls addcard from deck.java
    }

    //draw a card from the deck
    public void drawDeckCard()
    {
       m_hand.addCard(m_deck.drawCard());
    }

    public void drawrefCard(Deck d)
	{	//pulls a card from the input deck and adds it to the hand
    	//this is most likely used for the ref cards
    	m_hand.addCard(d.drawCard());
    	d.counter--;
	}

    public int getHandSize()
    {
        return m_hand.getSize();
    }

    public int getDeckSize()
    {
        return m_deck.getSize();
    }
    public Vector<Card> getHand()
    {
    	return this.m_hand.getDeck();
    }
    public void setPlayedCardIndex(int handIndex)
    {
    	this.m_playedCardIndex = handIndex;
    }
    public int getPlayedCardIndex()
    {
    	return this.m_playedCardIndex;
    }

    public Card playCard()
    {
        return m_hand.removeIndex(m_playedCardIndex);
    }

    public Card getCard()
    {
    	return m_hand.getCard(m_playedCardIndex);
    }
}
