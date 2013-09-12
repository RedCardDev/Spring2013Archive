
 package com.example.kickshot.test;
 
/**
* Created with IntelliJ IDEA.
* User: coleman
* Date: 10/10/12
* Time: 10:07 PM
* To change this template use File | Settings | File Templates.
*/

import com.example.kickshot.Dice;
import junit.framework.Assert;
import junit.framework.Test;
//import org.junit.Assert;

public class DiceTest2 {
  //  @org.junit.Test
    public void testRoll() throws Exception {
        Dice dice = new Dice();
        for(int i=0; i<100; i++)
        {
            int[] diceRoll = dice.roll();

            //Check that values are correct
            Assert.assertEquals(true, (diceRoll[0] <= 6 && diceRoll[0] >= 1));
            Assert.assertEquals(true, (diceRoll[1] <= 6 && diceRoll[1] >= 1));
        }
    }
}
