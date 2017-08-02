package com.apps.twelve.floor.salon.feature.my_bonus.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.my_bonus.views.IBonusHowFragmentView;
import com.apps.twelve.floor.salon.utils.Constants;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import rx.Subscription;

/**
 * Created by Alexandra on 29.05.2017.
 */

@InjectViewState public class BonusHowFragmentPresenter
    extends BasePresenter<IBonusHowFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchString();
  }

  private void fetchString() {
    getViewState().showStringBody(mDataManager.getStringEntity(Constants.RemoteText.BONUS));
    Subscription subscription = mDataManager.fetchString(Constants.RemoteText.BONUS)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(remoteStringEntity -> {
          mDataManager.storeStringEntity(Constants.RemoteText.BONUS, remoteStringEntity.getText());
          getViewState().showStringBody(remoteStringEntity.getText());
        });
    addToUnsubscription(subscription);
  }
}