package com.example.kickshot;

/**
 * Createdwith IntelliJ IDEA.
 * User: coleman
 * Date: 11/29 /12
 * Time: 4:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class GoalBlockLeftCard extends Card {
    GoalBlockLeftCard(int team)
    {
        super();
        this.set_rollMultiplier(1);
        this.set_rollDice(true);
        this.set_possessionChange(false);
        this.set_gameMode(3);
        if (team == -1)
            set_resID(R.drawable.home_block_left);	// dribble was here
        else										// did you want block left?
            set_resID(R.drawable.away_block_left);

    }
}
