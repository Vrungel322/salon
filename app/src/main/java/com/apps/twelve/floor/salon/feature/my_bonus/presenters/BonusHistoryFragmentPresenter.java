package com.apps.twelve.floor.salon.feature.my_bonus.presenters;

import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.model.BonusHistoryEntity;
import com.apps.twelve.floor.salon.feature.my_bonus.views.IBonusHistoryFragmentView;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_TOKEN_EXPIRED;
import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_UNAUTHORIZED;
import static com.apps.twelve.floor.salon.utils.Constants.StatusCode.RESPONSE_200;

/**
 * Created by Alexandra on 30.05.2017.
 */

@InjectViewState public class BonusHistoryFragmentPresenter
    extends BasePresenter<IBonusHistoryFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getBonusCount();
    getBonusHistory();
  }

  private void getBonusCount() {
    Subscription subscription = mDataManager.getBonusCountObservable()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(count -> getViewState().setBonusCount(count), Timber::e);
    addToUnsubscription(subscription);
  }

  private void getBonusHistory() {

    getViewState().addBonusHistoryList(mDataManager.getAllElementsFromDB(BonusHistoryEntity.class));
    getViewState().startRefreshingView();
    Subscription subscription =
        mAuthorizationManager.checkToken(mDataManager.fetchBonusHistory()).concatMap(response -> {
          if (response.code() == RESPONSE_TOKEN_EXPIRED) {
            return mAuthorizationManager.checkToken(mDataManager.fetchBonusHistory());
          }
          return Observable.just(response);
        }).compose(ThreadSchedulers.applySchedulers()).subscribe(response -> {
          switch (response.code()) {
            case RESPONSE_200:
              cacheEntities(response.body());
              getViewState().addBonusHistoryList(response.body());
              getViewState().stopRefreshingView();
              break;
            case RESPONSE_UNAUTHORIZED:
              mAuthorizationManager.getAuthRxBus().post(new AuthRxBusHelper.UnauthorizedEvent());
              getViewState().stopRefreshingView();
              break;
            default:
              getViewState().stopRefreshingView();
              showMessageException();
              break;
          }
        }, throwable -> {
          Timber.e(throwable);
          showMessageException(throwable);
          getViewState().stopRefreshingView();
        });
    addToUnsubscription(subscription);
  }

  public void startRefreshing() {
    getBonusHistory();
  }
}
