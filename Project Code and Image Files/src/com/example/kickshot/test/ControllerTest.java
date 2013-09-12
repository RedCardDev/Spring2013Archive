package com.example.kickshot.test;

/**
 * Created with IntelliJ IDEA.
 * User: coleman
 * Date: 11/12/12
 * Time: 1:49 PM
 * To change this template use File | Settings | File Templates.
 */

import com.example.kickshot.Card;
import com.example.kickshot.GameController;
import junit.framework.Assert;

public class ControllerTest {
   // @Test
    public void testEval() throws Exception
    {
        GameController controller = new GameController(1, 6);

        Card noRoll = new Card();
        noRoll.set_moveAmount(2);
        noRoll.set_rollMultiplier(1);
        noRoll.set_rollDice(false);

        Card halfRoll = new Card();
        halfRoll.set_rollMultiplier(.5);
        halfRoll.set_rollDice(true);

        controller.evaluateCard(noRoll, 1, 1);

        Assert.assertEquals(2, controller.getM_moveAmount());
        Assert.assertEquals(1.0, controller.getM_multiplier());

        for(int i=0; i< 1000; i++)    //roll dice multiple times
        {
            controller.evaluateCard(halfRoll, 1, 1);

            Assert.assertEquals(0.5, controller.getM_multiplier());
            Assert.assertTrue(controller.getM_moveAmount() <= 4);
            Assert.assertTrue(controller.getM_moveAmount() > 0);
            //System.out.println("Move Amount: " + controller.getM_moveAmount());
        }
    }
}
