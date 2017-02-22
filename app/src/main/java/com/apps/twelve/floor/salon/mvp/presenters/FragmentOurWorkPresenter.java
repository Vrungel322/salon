package com.apps.twelve.floor.salon.mvp.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.data.DataManager;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.IFragmentOurWorkPresenter;
import com.apps.twelve.floor.salon.mvp.views.IFragmentOurWorkView;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Vrungel on 21.02.2017.
 */

@InjectViewState public class FragmentOurWorkPresenter extends BasePresenter<IFragmentOurWorkView>
    implements IFragmentOurWorkPresenter {
  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override public void fetchListOfWorks() {
    Subscription subscription = mDataManager.fetchListOfWorks()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(ourWorkEntities -> getViewState().updateListOfWorks(ourWorkEntities),
            Throwable::printStackTrace);
    addToUnsubscription(subscription);
  }
}
