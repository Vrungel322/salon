package com.apps.twelve.floor.salon.feature.our_works.presenters;

import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.our_works.views.IWorkDetailsFragmentView;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_TOKEN_EXPIRED;
import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_UNAUTHORIZED;
import static com.apps.twelve.floor.salon.utils.Constants.StatusCode.RESPONSE_204;

/**
 * Created by Alexandra on 23.02.2017.
 */

@InjectViewState public class WorkDetailsFragmentPresenter
    extends BasePresenter<IWorkDetailsFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  public void addFavorite(int idPhoto) {
    Subscription subscription =
        mAuthorizationManager.checkToken(mDataManager.addToFavoritePhoto(idPhoto))
            .concatMap(response -> {
              if (response.code() == RESPONSE_TOKEN_EXPIRED) {
                return mAuthorizationManager.checkToken(mDataManager.addToFavoritePhoto(idPhoto));
              }
              return Observable.just(response);
            })
            .compose(ThreadSchedulers.applySchedulers())
            .subscribe(response -> {
              switch (response.code()) {
                case RESPONSE_204:
                  getViewState().setStatusFavorite(true);
                  mRxBus.post(new RxBusHelper.UpdateOurWorkList());
                  break;
                case RESPONSE_UNAUTHORIZED:
                  mAuthorizationManager.getAuthRxBus()
                      .post(new AuthRxBusHelper.UnauthorizedEvent());
                  break;
                default:
                  showMessageException();
                  break;
              }
            }, throwable -> {
              Timber.e(throwable);
              showMessageException(throwable);
            });
    addToUnsubscription(subscription);
  }

  public void deleteFavorite(int idPhoto) {
    Subscription subscription =
        mAuthorizationManager.checkToken(mDataManager.removeFromFavoritePhoto(idPhoto))
            .concatMap(response -> {
              if (response.code() == RESPONSE_TOKEN_EXPIRED) {
                return mAuthorizationManager.checkToken(
                    mDataManager.removeFromFavoritePhoto(idPhoto));
              }
              return Observable.just(response);
            })
            .compose(ThreadSchedulers.applySchedulers())
            .subscribe(response -> {
              switch (response.code()) {
                case RESPONSE_204:
                  getViewState().setStatusFavorite(false);
                  mRxBus.post(new RxBusHelper.UpdateOurWorkList());
                  break;
                case RESPONSE_UNAUTHORIZED:
                  mAuthorizationManager.getAuthRxBus()
                      .post(new AuthRxBusHelper.UnauthorizedEvent());
                  break;
                default:
                  showMessageException();
                  break;
              }
            }, throwable -> {
              Timber.e(throwable);
              showMessageException(throwable);
            });
    addToUnsubscription(subscription);
  }

  public void showAlertDialog() {
    mRxBus.post(new RxBusHelper.ShowAuthDialog());
  }
}
