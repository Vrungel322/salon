package com.apps.twelve.floor.salon.mvp.presenters.pr_fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.data.DataManager;
import com.apps.twelve.floor.salon.mvp.data.model.ServiceEntity;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.views.IChooseServiceFragmentView;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 29.03.2017.
 */

@InjectViewState public class ChooseServiceFragmentPresenter
    extends BasePresenter<IChooseServiceFragmentView> {
  @Inject DataManager mDataManager;

  private List<ServiceEntity> mServiceEntities = new ArrayList<>();

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().setUpRvServices();
  }

  public void fetchAllServices() {
    Subscription subscription = mDataManager.fetchServices(1, 10)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(serviceEntities -> {
          getViewState().updateRvServices(serviceEntities);
          mServiceEntities.clear();
          mServiceEntities.addAll(serviceEntities);
        }, Timber::e);
    addToUnsubscription(subscription);
  }

  public void filterServices(String s) {
    Subscription subscription;
    if (!s.isEmpty()) {
      subscription = Observable.from(mServiceEntities)
          .filter(serviceEntity -> serviceEntity.getTitle().toLowerCase().contains(s.trim()))
          .toList()
          .subscribe(serviceEntities -> getViewState().updateRvServices(serviceEntities));
    } else {
      subscription = Observable.just(mServiceEntities)
          .subscribe(serviceEntities -> getViewState().updateRvServices(serviceEntities));
    }
    addToUnsubscription(subscription);
  }

  public void showRvAllServices() {
    getViewState().showRvAllServices();
  }

  public void hideRvAllServices() {
    getViewState().hideRvAllServices();
  }

  public void setItemSelected(int position) {
    getViewState().setItemSelected(position);
  }
}
