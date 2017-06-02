package com.apps.twelve.floor.salon.feature.settings.views;

import android.net.Uri;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alexandra on 18.04.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface ISettingsFragmentView
    extends MvpView {

  void setUserPhoto(Uri uri);

  void setUserName(String name);

  void setUserEmail(String email);

  void setUserPhone(String phone);

  void setUserGender(String gender);

  void showSetThemeDialog(int position);

  void hideUserSettings();

  @StateStrategyType(SingleStateStrategy.class) void closeSetThemeDialog();
}
