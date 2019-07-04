package com.ramesh.mvvm.util;

import java.util.Random;

public class CommonUtils {

    public static int getRandomNumber() {
        return new Random().nextInt((5-1)+1)+1;
    }
}
