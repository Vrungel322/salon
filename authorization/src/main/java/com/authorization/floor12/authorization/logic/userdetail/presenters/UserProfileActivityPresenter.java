package com.authorization.floor12.authorization.logic.userdetail.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.authorization.floor12.authorization.App;
import com.authorization.floor12.authorization.base.BasePresenter;
import com.authorization.floor12.authorization.logic.userdetail.views.IUserProfileActivityView;

/**
 * Created by Alexander Svyatetsky on 05.05.2017.
 */

@InjectViewState public class UserProfileActivityPresenter
    extends BasePresenter<IUserProfileActivityView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().addFragmentSettings();
  }
}
