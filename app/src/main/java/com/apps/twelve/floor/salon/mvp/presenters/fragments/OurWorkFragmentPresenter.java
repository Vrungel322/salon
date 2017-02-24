package com.apps.twelve.floor.salon.mvp.presenters.fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.data.DataManager;
import com.apps.twelve.floor.salon.mvp.presenters.base.FragmentBasePresenter;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.IOurWorkFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IOurWorkFragmentView;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;

/**
 * Created by Vrungel on 21.02.2017.
 */

@InjectViewState public class OurWorkFragmentPresenter extends FragmentBasePresenter<IOurWorkFragmentView>
    implements IOurWorkFragmentPresenter {

  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchListOfWorks();
  }

  @Override public void fetchListOfWorks() {
    Subscription subscription = mDataManager.fetchListOfWorks()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(ourWorkEntities -> getViewState().addListOfWorks(ourWorkEntities),
            Throwable::printStackTrace);
    addToUnsubscription(subscription);
  }
}
