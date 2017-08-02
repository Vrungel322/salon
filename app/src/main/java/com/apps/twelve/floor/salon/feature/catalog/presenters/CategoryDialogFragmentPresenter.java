package com.apps.twelve.floor.salon.feature.catalog.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.model.category.Genre;
import com.apps.twelve.floor.salon.feature.catalog.views.ICategoryDialogFragmentView;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import java.util.ArrayList;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

import static com.apps.twelve.floor.salon.utils.Constants.StatusCode.RESPONSE_200;

/**
 * Created by Vrungel on 29.05.2017.
 */
@InjectViewState public class CategoryDialogFragmentPresenter
    extends BasePresenter<ICategoryDialogFragmentView> {

  private ArrayList<Genre> mGenres = new ArrayList<Genre>();

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchCategories();
  }

  @SuppressWarnings("ConstantConditions") private void fetchCategories() {
    mGenres.clear();
    getViewState().startProgressBar();
    Subscription subscription = mDataManager.fetchCategories().concatMap(response -> {
      if (response.code() == RESPONSE_200) {
        return Observable.from(response.body());
      } else {
        return Observable.error(new Exception("Not response 200"));
      }
    }).concatMap(goodsCategoryEntity -> {
      mGenres.add(new Genre(goodsCategoryEntity.getTitle(), goodsCategoryEntity.getChildren()));
      return Observable.just(mGenres);
    }).compose(ThreadSchedulers.applySchedulers()).subscribe(genres -> {
      getViewState().stopProgressBar();
      getViewState().fillCategories(genres);
    }, throwable -> {
      showMessageException(throwable);
      getViewState().stopProgressBar();
      Timber.e(throwable);
    });
    addToUnsubscription(subscription);
  }

  public void postEventToReloadList(Integer id, String title) {
    mRxBus.post(new RxBusHelper.ReloadCatalogByCategory(id, title));
  }

  public void postToShowResetBtn() {
    mRxBus.post(new RxBusHelper.ShowResetBtn() );
  }
}
