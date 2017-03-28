package com.apps.twelve.floor.salon.mvp.presenters.pr_fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_interfaces.IWorkDetailsFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IWorkDetailsFragmentView;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Alexandra on 23.02.2017.
 */

@InjectViewState public class WorkDetailsFragmentPresenter
    extends BasePresenter<IWorkDetailsFragmentView> implements IWorkDetailsFragmentPresenter {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override public void addFavorite(int idPhoto) {
    Timber.d("add favorite");
    Subscription subscription = rx.Observable.just(idPhoto)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(integer -> getViewState().setStatusFavorite(true), Timber::e);
    addToUnsubscription(subscription);
  }

  @Override public void deleteFavorite(int idPhoto) {
    Timber.d("delete favorite");
    Subscription subscription = rx.Observable.just(idPhoto)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(integer -> getViewState().setStatusFavorite(false), Timber::e);
    addToUnsubscription(subscription);
  }
}
