package com.apps.twelve.floor.salon.feature.news.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.news.views.IListNewsDetailFragmentView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by John on 11.07.2017.
 */

@InjectViewState public class ListNewsDetailPresenter
    extends BasePresenter<IListNewsDetailFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
