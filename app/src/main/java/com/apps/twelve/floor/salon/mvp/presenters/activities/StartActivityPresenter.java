package com.apps.twelve.floor.salon.mvp.presenters.activities;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.base.ActivityBasePresenter;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.IStartActivityPresenter;
import com.apps.twelve.floor.salon.mvp.views.IStartActivityView;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;

/**
 * Created by Vrungel on 20.02.2017.
 */

@InjectViewState public class StartActivityPresenter extends ActivityBasePresenter<IStartActivityView>
    implements IStartActivityPresenter {

  @Inject RxBus mRxBus;

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().addFragmentMain();
    rxBusShowAppBarLayout();
  }

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  private void rxBusShowAppBarLayout() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.ShowAppBarLayout.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(shoeToolBar -> {
          getViewState().showAppBarLayout();
        }, Throwable::printStackTrace);
    addToUnsubscription(subscription);
  }
}
