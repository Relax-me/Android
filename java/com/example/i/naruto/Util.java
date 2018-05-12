package com.example.i.naruto;

import java.util.Random;

/**
 * Created by 或跃在渊i on 2017/11/16.
 */

public class Util {
    public static Random random=new Random();
    public static int rand(int range){
        if (range==0)
            return 0;
        return Math.abs(random.nextInt()%range);
    }
}
