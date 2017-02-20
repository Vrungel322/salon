package com.apps.twelve.floor.salon.mvp.data.local.mappers;

/**
 * Created by Vrungel on 26.01.2017.
 */

public interface Mapper<A, B> {
  B transform(A obj) throws RuntimeException;
}
