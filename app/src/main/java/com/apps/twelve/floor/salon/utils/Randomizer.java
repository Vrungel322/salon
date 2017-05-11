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
    int generatedNum = r.nextInt((max - min) + 1) + min;
    if (generatedNum == max) return generatedNum - 1;
    return generatedNum;
  }
}
