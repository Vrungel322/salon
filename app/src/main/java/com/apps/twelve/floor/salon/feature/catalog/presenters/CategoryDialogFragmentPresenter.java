package com.apps.twelve.floor.salon.feature.catalog.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.data.model.category.Genre;
import com.apps.twelve.floor.salon.feature.catalog.views.ICategoryDialogFragmentView;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import java.util.ArrayList;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;

/**
 * Created by Vrungel on 29.05.2017.
 */
@InjectViewState public class CategoryDialogFragmentPresenter
    extends BasePresenter<ICategoryDialogFragmentView> {
  @Inject DataManager mDataManager;
  private ArrayList<Genre> mGenres = new ArrayList<Genre>();

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchCategories();
  }

  private void fetchCategories() {
    mGenres.clear();
    Subscription subscription = mDataManager.fetchCategories()
        .compose(ThreadSchedulers.applySchedulers())
        .concatMap(Observable::from)
        .concatMap(goodsCategoryEntity -> {
          mGenres.add(new Genre(goodsCategoryEntity.getTitle(), goodsCategoryEntity.getChildren()));
          return Observable.just(mGenres);
        })
        .subscribe(genres -> {

          getViewState().fillCategories(genres);
        });
    addToUnsubscription(subscription);
  }
}
