package com.apps.twelve.floor.salon.feature.our_works.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.our_works.views.IWorkDetailsFragmentView;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Alexandra on 23.02.2017.
 */

@InjectViewState public class WorkDetailsFragmentPresenter
    extends BasePresenter<IWorkDetailsFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  public void addFavorite(int idPhoto) {
    Timber.d("add favorite");
    Subscription subscription = rx.Observable.just(idPhoto)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(integer -> getViewState().setStatusFavorite(true), Timber::e);
    addToUnsubscription(subscription);
  }

  public void deleteFavorite(int idPhoto) {
    Timber.d("delete favorite");
    Subscription subscription = rx.Observable.just(idPhoto)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(integer -> getViewState().setStatusFavorite(false), Timber::e);
    addToUnsubscription(subscription);
  }
}
