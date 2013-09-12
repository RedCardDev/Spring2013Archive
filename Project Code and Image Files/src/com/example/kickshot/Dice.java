package com.example.kickshot;

import java.lang.Math;

/**
 * Created with IntelliJ IDEA.
 * User: coleman
 * Date: 10/10/12
 * Time: 9:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class Dice {

    public Dice()
    {

    }

    public int[] roll()
    {
        int[] results = new int[2];

        results[0] = ((int) (Math.random() * 10) % 6) + 1;
        results[1] = ((int) (Math.random() * 10) % 6) + 1;

        return results;
    }
}
