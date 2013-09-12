package com.example.kickshot;

/**
 * Createdwith IntelliJ IDEA.
 * User: coleman
 * Date: 11/29 /12
 * Time: 4:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class GoalShotRightCard extends Card {
    GoalShotRightCard(int team)
    {
        super();
        this.set_rollMultiplier(1);
        this.set_rollDice(true);
        this.set_possessionChange(true);
        this.set_gameMode(2);
        if (team == 1)	//should be -1
            set_resID(R.drawable.home_shot_right);
        else
            set_resID(R.drawable.away_shot_right);

    }
}
