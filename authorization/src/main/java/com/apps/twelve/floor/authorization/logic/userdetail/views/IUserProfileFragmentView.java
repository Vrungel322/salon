package com.apps.twelve.floor.authorization.logic.userdetail.views;

import android.net.Uri;
import android.support.annotation.StringRes;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alexandra on 18.04.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IUserProfileFragmentView
    extends MvpView {

  void setUserPhoto(Uri uri);

  void setUserName(String name);

  void setUserEmail(String email);

  void setUserPhone(String phone);

  void setUserGender(String gender);

  void closeFragment();

  void showConnectErrorMessage();

  void showToastMessage(@StringRes int resId);

  void showChangeGenderDialog();

  void showExitDialog();

  void showExitProgressDialog();

  void closeChangeGenderDialog();

  void closeExitDialog();

  void closeExitProgressDialog();

  void setUserBirthDay(String date);
}
