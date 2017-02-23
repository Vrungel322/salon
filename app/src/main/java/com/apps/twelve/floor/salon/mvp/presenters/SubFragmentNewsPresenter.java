package com.apps.twelve.floor.salon.mvp.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.data.DataManager;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.ISubFragmentNewsPresenter;
import com.apps.twelve.floor.salon.mvp.views.ISubFragmentNewsView;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;

/**
 * Created by Vrungel on 23.02.2017.
 */

@InjectViewState public class SubFragmentNewsPresenter extends BasePresenter<ISubFragmentNewsView>
    implements ISubFragmentNewsPresenter {
  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    Subscription subscription = mDataManager.fetchNewsPreview()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(previewNewsEntity -> getViewState().updateNewsPreview(previewNewsEntity),
            Throwable::printStackTrace);
    addToUnsubscription(subscription);
  }
}
