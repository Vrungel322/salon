package com.apps.twelve.floor.salon.feature.settings.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.feature.settings.views.IChangeUserInfoFragmentView;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;

import static com.apps.twelve.floor.salon.utils.Constants.ChangingUserInfoField.EMAIL;
import static com.apps.twelve.floor.salon.utils.Constants.ChangingUserInfoField.LOGIN;
import static com.apps.twelve.floor.salon.utils.Constants.ChangingUserInfoField.NAME;
import static com.apps.twelve.floor.salon.utils.Constants.ChangingUserInfoField.PASSWORD;
import static com.apps.twelve.floor.salon.utils.Constants.ChangingUserInfoField.PHONE;

/**
 * Created by Alexandra on 04.05.2017.
 */

@InjectViewState public class ChangeUserInfoFragmentPresenter
    extends BasePresenter<IChangeUserInfoFragmentView> {

  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
  }

  public void saveInfo(int field, String value) {
    switch (field) {
      case NAME:
        mDataManager.setProfileName(value);
        break;
      case LOGIN:
        mDataManager.setProfileLogin(value);
        break;
      case PASSWORD:
        mDataManager.setProfilePassword(value);
        break;
      case EMAIL:
        mDataManager.setProfileEmail(value);
        break;
      case PHONE:
        mDataManager.setProfilePhone(value);
        break;
    }
    getViewState().stopAnimation();
  }
}
