package com.apps.twelve.floor.salon.feature.our_works.presenters;

import android.content.Context;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.data.model.OurWorkEntity;
import com.apps.twelve.floor.salon.feature.our_works.views.IOurWorkFragmentView;
import com.apps.twelve.floor.salon.utils.Converters;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 21.02.2017.
 */

@InjectViewState public class OurWorkFragmentPresenter extends BasePresenter<IOurWorkFragmentView> {

  @Inject DataManager mDataManager;
  @Inject RxBus mRxBus;
  @Inject Context mContext;

  private List<OurWorkEntity> mOurWorkEntities = new ArrayList<>();

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchListOfFavoriteWorks();
    //RxBus
    subscribeUpdateWorkList();
  }

  public void fetchListOfFavoriteWorks() {
    mOurWorkEntities.clear();
    getViewState().startRefreshingView();
    Subscription subscription =
        Observable.zip(mDataManager.fetchFavoritePhotos(), mDataManager.fetchListOfWorks(),
            (photoWorksEntities, ourWorkEntities) -> {
              mOurWorkEntities.add(0,
                  new OurWorkEntity(Converters.getUrl(R.drawable.booking_bonus_background),
                      photoWorksEntities.size(), photoWorksEntities));
              mOurWorkEntities.addAll(ourWorkEntities);
              return mOurWorkEntities;
            }).compose(ThreadSchedulers.applySchedulers()).subscribe(ourWorkEntities -> {
          getViewState().stopRefreshingView();
          getViewState().addListOfWorks(ourWorkEntities);
        }, throwable -> {
          getViewState().stopRefreshingView();
          Timber.e(throwable);
          showMessageConnectException(throwable);
        });
    addToUnsubscription(subscription);
  }

  private void subscribeUpdateWorkList() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.UpdateOurWorkList.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(ourWorkEntities -> fetchListOfFavoriteWorks(), Timber::e);
    addToUnsubscription(subscription);
  }
}
