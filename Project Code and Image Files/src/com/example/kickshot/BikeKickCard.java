package com.example.kickshot;

/**
 * Created with IntelliJ IDEA.
 * User: coleman
 * Date: 11/4/12
 * Time: 8:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class BikeKickCard extends Card {
    public BikeKickCard(int team) {
        super();
        this.set_rollMultiplier(0.5);
        this.set_rollDice(true);
        this.set_possessionChange(false);
        this.set_gameMode(1);
        if (team == -1)
            set_resID(R.drawable.home_bicycle_kick);
        else
            set_resID(R.drawable.away_bicycle_kick);
    }
}
