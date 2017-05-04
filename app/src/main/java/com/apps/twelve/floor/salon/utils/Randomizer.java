package com.apps.twelve.floor.salon.utils;

import java.util.Random;

/**
 * Created by Vrungel on 28.04.2017.
 */

public final class Randomizer {
  public static int getRandomNumberInRange(int min, int max) {

    if (min > max) {
      throw new IllegalArgumentException("max must be greater than min");
    }
    if (max == 1) return 0;

    Random r = new Random();
    int generetedNum = r.nextInt((max - min) + 1) + min;
    if (generetedNum == max) return generetedNum - 1;
    return generetedNum;
  }
}
