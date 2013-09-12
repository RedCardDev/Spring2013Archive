package com.example.kickshot;

/**
 * Createdwith IntelliJ IDEA.
 * User: coleman
 * Date: 11/29 /12
 * Time: 4:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class InterceptCard extends Card {
    InterceptCard(int team)
    {
        super();
        this.set_rollMultiplier(1);
        this.set_rollDice(false);
        this.set_possessionChange(true);
        this.set_gameMode(4);
        this.m_defensive = true;	//sets card to defensive
        	//not sure if defensive is needed
        if (team == -1)
            set_resID(R.drawable.home_intercept);
        else
            set_resID(R.drawable.away_intercept);

    }
}
