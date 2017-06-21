package com.apps.twelve.floor.salon.feature.our_works.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.model.OurWorkEntity;
import com.apps.twelve.floor.salon.feature.our_works.views.IOurWorkFragmentView;
import com.apps.twelve.floor.salon.utils.Converters;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 21.02.2017.
 */

@InjectViewState public class OurWorkFragmentPresenter extends BasePresenter<IOurWorkFragmentView> {

  private List<OurWorkEntity> mOurWorkEntities = new ArrayList<>();

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchWorksCondition();
    //RxBus
    subscribeUpdateWorkList();
  }

  public void fetchWorksCondition() {
    if (mAuthorizationManager.isAuthorized()) {
      fetchListOfFavoriteWorks();
    } else {
      fetchListOfWorks();
    }
  }

  @SuppressWarnings("ConstantConditions") private void fetchListOfWorks() {
    mOurWorkEntities.clear();
    getViewState().startRefreshingView();
    Subscription subscription = mDataManager.fetchListOfWorks()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(ourWorkEntities -> {
          mOurWorkEntities.add(0,
              new OurWorkEntity(Converters.getUrl(R.drawable.ic_favorite_our_work_32dp), 0, null));
          mOurWorkEntities.addAll(ourWorkEntities);
          getViewState().stopRefreshingView();
          getViewState().addListOfWorks(mOurWorkEntities);
        }, throwable -> {
          getViewState().stopRefreshingView();
          Timber.e(throwable);
          showMessageException(throwable);
        });
    addToUnsubscription(subscription);
  }

  @SuppressWarnings("ConstantConditions") private void fetchListOfFavoriteWorks() {
    mOurWorkEntities.clear();
    getViewState().startRefreshingView();
    Subscription subscription =
        Observable.zip(mDataManager.fetchListOfWorks(), mDataManager.fetchFavoritePhotos(),
            (ourWorkEntities, photoWorksEntities) -> {
              if (photoWorksEntities.code() != 400 && photoWorksEntities.code() != 401) {
                mOurWorkEntities.add(0,
                    new OurWorkEntity(Converters.getUrl(R.drawable.ic_favorite_our_work_32dp),
                        photoWorksEntities.body().size(), photoWorksEntities.body()));
              } else {
                //mAuthorizationManager.refreshToken();
              }
              if (photoWorksEntities.code() == 500) {
                mAuthorizationManager.clear();
                getViewState().startLoginActivity();
              }
              mOurWorkEntities.addAll(ourWorkEntities);
              return mOurWorkEntities;
            }).compose(ThreadSchedulers.applySchedulers()).subscribe(ourWorkEntities -> {
          getViewState().stopRefreshingView();
          getViewState().addListOfWorks(ourWorkEntities);
        }, throwable -> {
          getViewState().stopRefreshingView();
          Timber.e(throwable);
          showMessageException(throwable);
        });
    addToUnsubscription(subscription);
  }

  private void subscribeUpdateWorkList() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.UpdateOurWorkList.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(ourWorkEntities -> fetchWorksCondition(), Timber::e);
    addToUnsubscription(subscription);
  }

  public void showAlertDialog() {
    mRxBus.post(new RxBusHelper.ShowAuthDialog());
  }
}
