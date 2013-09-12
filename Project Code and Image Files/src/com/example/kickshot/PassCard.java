package com.example.kickshot;

/**
 * Createdwith IntelliJ IDEA.
 * User: coleman
 * Date: 11/29 /12
 * Time: 4:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class PassCard extends Card {
    PassCard(int team)
    {
        super();
        this.set_rollMultiplier(1);
        this.set_rollDice(true);
        this.set_possessionChange(false);
        this.set_gameMode(1);
        if (team == -1)
            set_resID(R.drawable.home_pass);
        else
            set_resID(R.drawable.away_pass);

    }
}
