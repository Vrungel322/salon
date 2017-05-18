package com.apps.twelve.floor.salon.feature.catalog.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.feature.catalog.views.ICatalogFragmentView;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;

/**
 * Created by John on 17.05.2017.
 */

@InjectViewState public class CatalogFragmentPresenter extends BasePresenter<ICatalogFragmentView> {
  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().setUpUi();
    fetchStaffList();
  }

  private void fetchStaffList() {
    Subscription subscription = mDataManager.fetchStaff()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(staffEntities -> {
          getViewState().updateStaffList(staffEntities);
        });
    addToUnsubscription(subscription);
  }
}
