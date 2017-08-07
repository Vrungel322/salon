package com.apps.twelve.floor.salon.feature.our_works.presenters;

import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.model.OurWorkEntity;
import com.apps.twelve.floor.salon.data.model.PhotoWorksEntity;
import com.apps.twelve.floor.salon.feature.our_works.views.IOurWorkFragmentView;
import com.apps.twelve.floor.salon.utils.Converters;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import io.realm.RealmList;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_TOKEN_EXPIRED;
import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_UNAUTHORIZED;
import static com.apps.twelve.floor.salon.utils.Constants.StatusCode.RESPONSE_200;
import static com.apps.twelve.floor.salon.utils.Constants.StatusCode.RESPONSE_503;

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
    getViewState().addListOfWorks(mDataManager.getAllElementsFromDB(OurWorkEntity.class));
    mOurWorkEntities.clear();
    getViewState().startRefreshingView();
    Subscription subscription = mDataManager.fetchListOfWorks()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(response -> {
          if (response.code() == RESPONSE_200) {
            mOurWorkEntities.add(0,
                new OurWorkEntity(Converters.getUrl(R.drawable.ic_favorite_our_work_32dp), 0,
                    mContext.getString(R.string.menu_favourite), null));
            mOurWorkEntities.addAll(response.body());
            cacheEntities(mOurWorkEntities,OurWorkEntity.class);
            getViewState().stopRefreshingView();
            getViewState().addListOfWorks(mOurWorkEntities);
          }
          if (response.code() == RESPONSE_503) {
            getViewState().showServerErrorMsg();
            getViewState().stopRefreshingView();
          }
        }, throwable -> {
          getViewState().stopRefreshingView();
          Timber.e(throwable);
          showMessageException(throwable);
        });
    addToUnsubscription(subscription);
  }

  private void fetchListOfFavoriteWorks() {
    getViewState().addListOfWorks(mDataManager.getAllElementsFromDB(OurWorkEntity.class));
    mOurWorkEntities.clear();
    getViewState().startRefreshingView();
    Subscription subscription = Observable.zip(fetchListOfWorksAuth(), fetchFavoritePhotos(),
        (ourWorkEntities, photoWorksEntities) -> {
          RealmList<PhotoWorksEntity> realmList = new RealmList<PhotoWorksEntity>();
          realmList.addAll(photoWorksEntities);
          mOurWorkEntities.add(0,
              new OurWorkEntity(Converters.getUrl(R.drawable.ic_favorite_our_work_32dp),
                  photoWorksEntities.size(), mContext.getString(R.string.menu_favourite),
                  realmList));
          mOurWorkEntities.addAll(ourWorkEntities);
          return mOurWorkEntities;
        }).compose(ThreadSchedulers.applySchedulers()).subscribe(ourWorkEntities -> {
      cacheEntities(ourWorkEntities,OurWorkEntity.class);
      getViewState().stopRefreshingView();
      getViewState().addListOfWorks(ourWorkEntities);
    }, throwable -> {
      getViewState().stopRefreshingView();
      Timber.e(throwable);
      showMessageException(throwable);
    });
    addToUnsubscription(subscription);
  }

  private Observable<List<OurWorkEntity>> fetchListOfWorksAuth() {
    return mAuthorizationManager.checkToken(mDataManager.fetchListOfWorksAuth())
        .concatMap(listResponse -> {
          if (listResponse.code() == RESPONSE_TOKEN_EXPIRED) {
            return mAuthorizationManager.checkToken(mDataManager.fetchListOfWorksAuth());
          } else {
            return Observable.just(listResponse);
          }
        })
        .compose(ThreadSchedulers.applySchedulers())
        .concatMap(listResponse -> {
          switch (listResponse.code()) {
            case RESPONSE_200:
              return Observable.just(listResponse.body());
            case RESPONSE_UNAUTHORIZED:
              mAuthorizationManager.getAuthRxBus().post(new AuthRxBusHelper.UnauthorizedEvent());
              getViewState().stopRefreshingView();
              return Observable.empty();
            default:
              getViewState().stopRefreshingView();
              showMessageException();
              return Observable.empty();
          }
        });
  }

  private Observable<List<PhotoWorksEntity>> fetchFavoritePhotos() {
    return mAuthorizationManager.checkToken(mDataManager.fetchFavoritePhotos())
        .concatMap(listResponse -> {
          if (listResponse.code() == RESPONSE_TOKEN_EXPIRED) {
            return mAuthorizationManager.checkToken(mDataManager.fetchFavoritePhotos());
          } else {
            return Observable.just(listResponse);
          }
        })
        .compose(ThreadSchedulers.applySchedulers())
        .concatMap(listResponse -> {
          switch (listResponse.code()) {
            case RESPONSE_200:
              return Observable.just(listResponse.body());
            case RESPONSE_UNAUTHORIZED:
              mAuthorizationManager.getAuthRxBus().post(new AuthRxBusHelper.UnauthorizedEvent());
              getViewState().stopRefreshingView();
              return Observable.empty();
            default:
              getViewState().stopRefreshingView();
              showMessageException();
              return Observable.empty();
          }
        });
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
