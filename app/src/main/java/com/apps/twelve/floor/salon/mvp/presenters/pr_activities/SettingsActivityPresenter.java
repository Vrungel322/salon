package com.apps.twelve.floor.salon.mvp.presenters.pr_activities;

import android.net.Uri;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.data.local.PreferencesHelper;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.views.ISettingsActivityView;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;

/**
 * Created by Alexandra on 18.04.2017.
 */

@InjectViewState public class SettingsActivityPresenter
    extends BasePresenter<ISettingsActivityView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Inject PreferencesHelper mPref;

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
  }

  public void setUpPhoto() {
    if (!mPref.getProfileImage().equals("")) {
      getViewState().setUserPhoto(Uri.parse(mPref.getProfileImage()));
    }
  }

  public void savePhoto(String uri) {
    mPref.setProfileImage(uri);
    setUpPhoto();
  }
}
