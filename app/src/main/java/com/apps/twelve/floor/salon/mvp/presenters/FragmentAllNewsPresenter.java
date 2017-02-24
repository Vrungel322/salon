package com.apps.twelve.floor.salon.mvp.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.data.DataManager;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.IFragmentAllNewsPresenter;
import com.apps.twelve.floor.salon.mvp.views.IFragmentAllNewsView;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;

/**
 * Created by Vrungel on 24.02.2017.
 */

@InjectViewState public class FragmentAllNewsPresenter extends BasePresenter<IFragmentAllNewsView>
    implements IFragmentAllNewsPresenter {

  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchListOfNews();
  }

  @Override public void fetchListOfNews() {
    Subscription subscription = mDataManager.fetchAllNews()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(newsEntities -> getViewState().addListOfNews(newsEntities),
            Throwable::printStackTrace);
    addToUnsubscription(subscription);
  }
}
