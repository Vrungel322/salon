package com.apps.twelve.floor.salon.mvp.presenters.pr_fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.data.DataManager;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.views.IOurWorkFragmentView;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;

/**
 * Created by Vrungel on 21.02.2017.
 */

@InjectViewState public class OurWorkFragmentPresenter extends BasePresenter<IOurWorkFragmentView> {

  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchListOfWorks();
  }

  public void fetchListOfWorks() {
    Subscription subscription = mDataManager.fetchListOfWorks()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(ourWorkEntities -> getViewState().addListOfWorks(ourWorkEntities),
            Throwable::printStackTrace);
    addToUnsubscription(subscription);
  }
}
