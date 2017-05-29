package com.apps.twelve.floor.salon.feature.my_bonus.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.feature.my_bonus.views.IBonusHowFragmentView;
import com.apps.twelve.floor.salon.utils.RxBus;
import javax.inject.Inject;

/**
 * Created by Alexandra on 29.05.2017.
 */

public class BonusHowFragmentPresenter extends BasePresenter<IBonusHowFragmentView> {

  @Inject DataManager mDataManager;
  @Inject RxBus mRxBus;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
  }
}