package com.apps.twelve.floor.salon.utils;

import java.util.Random;

/**
 * Created by Vrungel on 28.04.2017.
 */

public class Randomizer {
  public static int getRandomNumberInRange(int min, int max) {

    if (min >= max) {
      throw new IllegalArgumentException("max must be greater than min");
    }

    Random r = new Random();
    return r.nextInt((max - min) + 1) + min;
  }
}
