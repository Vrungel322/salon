package com.authorization.floor12.authorization.logic.userdetail.views;

import android.net.Uri;
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

  void setUserPassword(String password);

  void setUserEmail(String email);

  void setUserPhone(String phone);

  void setUserGender(String gender);

  void showSignIn();

  void showConnectErrorMessage();
}
