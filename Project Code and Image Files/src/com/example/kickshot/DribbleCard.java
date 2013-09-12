package com.example.kickshot;

/**
 * Created with IntelliJ IDEA.
 * User: coleman
 * Date: 11/4/12
 * Time: 5:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class DribbleCard extends Card {
    public DribbleCard(int team) {
        super();
        this.set_moveAmount(1);
        this.set_rollMultiplier(1);
        this.set_rollDice(false);
        this.set_possessionChange(false);
        this.set_gameMode(1);
        if (team == -1)
            set_resID(R.drawable.home_dribble);
        else
            set_resID(R.drawable.away_dribble);
    }


}
