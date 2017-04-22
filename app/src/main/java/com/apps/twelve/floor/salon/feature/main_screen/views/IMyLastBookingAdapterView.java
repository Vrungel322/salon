package com.apps.twelve.floor.salon.feature.main_screen.views;

import android.content.DialogInterface;
import com.apps.twelve.floor.salon.feature.main_screen.adapters.MyLastBookingAdapter;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Vrungel on 01.03.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IMyLastBookingAdapterView
    extends MvpView {
  @StateStrategyType(AddToEndStrategy.class) void removeBookedServiceFromList(int position);

  @StateStrategyType(AddToEndSingleStrategy.class) void showConfirmationDialog(int position);

  @StateStrategyType(AddToEndSingleStrategy.class) void cancelAlertDialog();
}
