package com.example.kickshot;

/**
 * Createdwith IntelliJ IDEA.
 * User: coleman
 * Date: 11/29 /12
 * Time: 4:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class DirectFreeKickCard extends Card {
    DirectFreeKickCard()
    {
        super();
        this.set_rollMultiplier(1);
        this.set_rollDice(true);
        this.set_possessionChange(true);
        this.set_gameMode(10);

        set_resID(R.drawable.ref_wild_card);
/*
        if (team == -1)
            set_resID(R.drawable.pass_home);
        else
            set_resID(R.drawable.pass_away);
*/
    }
}
