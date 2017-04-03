package com.apps.twelve.floor.salon.mvp.presenters.pr_fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.data.DataManager;
import com.apps.twelve.floor.salon.mvp.data.model.ServiceEntity;
import com.apps.twelve.floor.salon.mvp.data.model.service_tree_item.ParentService;
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

  private List<ParentService> mServiceTreeEntities = new ArrayList<>();
  private List<ServiceEntity> mServiceAllEntities = new ArrayList<>();

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().setUpRvAllServices();
    getViewState().setUpRvTreeServices();
    fetchTreeOfServices();
  }

  public void fetchTreeOfServices() {
    getViewState().showProgressBar();
    Subscription subscription = mDataManager.fetchTreeServices(1, 10)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(parentServices -> {
          getViewState().hideProgressBar();
          getViewState().updateRvTreeServices(parentServices);
          mServiceTreeEntities.clear();
          mServiceTreeEntities.addAll(parentServices);
        }, throwable -> {
          Timber.e(throwable);
          getViewState().hideProgressBar();
          getViewState().showErrorMsg(throwable.getMessage());
        });
    addToUnsubscription(subscription);
  }

  public void fetchAllServices() {
    getViewState().showProgressBarAllServices();
    Subscription subscription = mDataManager.fetchAllServices(1, 10)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(serviceEntities -> {
          getViewState().updateRvAllServices(serviceEntities);
          mServiceAllEntities.clear();
          mServiceAllEntities.addAll(serviceEntities);
          getViewState().hideProgressBarAllServices();
        }, throwable -> {
          Timber.e(throwable);
          getViewState().hideProgressBarAllServices();
          getViewState().showErrorMsg(throwable.getMessage());
        });
    addToUnsubscription(subscription);
  }

  public void filterServices(String s) {
    Subscription subscription;
    if (!s.isEmpty()) {
      subscription = Observable.from(mServiceAllEntities)
          .filter(serviceEntity -> serviceEntity.getTitle().toLowerCase().contains(s.trim()))
          .toList()
          .subscribe(serviceEntities -> getViewState().updateRvAllServices(serviceEntities));
    } else {
      subscription = Observable.just(mServiceAllEntities)
          .subscribe(serviceEntities -> getViewState().updateRvAllServices(serviceEntities));
    }
    addToUnsubscription(subscription);
  }

  public void hideLLAllServices() {
    getViewState().hideLLAllServices();
  }

  public void setItemSelected(int position) {
    getViewState().setItemSelected(position);
  }

  public void showLLTreeServices() {
    getViewState().showLLTreeServices();
  }

  public void showLLAllServices() {
    getViewState().showLLAllServices();
  }

  public void hideLLTreeServices() {
    getViewState().hideLLTreeServices();
  }
}
