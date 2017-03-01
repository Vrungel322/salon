package com.apps.twelve.floor.salon.mvp.presenters.interfaces;

/**
 * Created by Vrungel on 01.03.2017.
 */

public interface IMyLastBookingAdapterPresenter {
  void cancelOrder(int position);

  void postponeOrder(int position);
}
