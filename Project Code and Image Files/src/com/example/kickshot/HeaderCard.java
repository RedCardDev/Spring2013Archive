package com.example.kickshot;

/**
 * Created with IntelliJ IDEA.
 * User: coleman
 * Date: 11/4/12
 * Time: 8:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeaderCard extends Card {
    public HeaderCard(int team) {
        super();
        this.set_rollMultiplier(0.5);
        this.set_rollDice(true);
        this.set_possessionChange(false);
        this.set_gameMode(1);
        if (team == -1)
            set_resID(R.drawable.home_header);
        else
            set_resID(R.drawable.away_header);
    }
}
