package com.apps.twelve.floor.salon.mvp.presenters.pr_fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.data.DataManager;
import com.apps.twelve.floor.salon.mvp.data.model.BookingEntity;
import com.apps.twelve.floor.salon.mvp.data.model.CategoryEntity;
import com.apps.twelve.floor.salon.mvp.data.model.ServiceEntity;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.views.IChooseServiceFragmentView;
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
 * Created by Vrungel on 29.03.2017.
 */

@InjectViewState public class ChooseServiceFragmentPresenter
    extends BasePresenter<IChooseServiceFragmentView> {

  @Inject DataManager mDataManager;
  @Inject BookingEntity mBookingEntity;
  @Inject RxBus mRxBus;

  private List<ServiceEntity> mServiceAllEntities = new ArrayList<>();
  private List<List<CategoryEntity>> mListListCategories = new ArrayList<>();
  private List<String> mPathList = new ArrayList<>();
  private StringBuilder mPath = new StringBuilder();

  private static final String SLASH = "/";

  @Override protected void inject() {
    App.getBookingComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().setUpRvAllServices();
    getViewState().setUpRvCategory();
    fetchCategory();
    backCategories();
  }

  public void fetchCategory() {
    getViewState().showProgressBar();
    Subscription subscription = mDataManager.fetchCategory()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(parentServices -> {
          getViewState().hideProgressBar();
          getViewState().updateRvCategory(parentServices);
          mListListCategories.add(parentServices);
        }, throwable -> {
          Timber.e(throwable);
          getViewState().hideProgressBar();
          getViewState().showErrorMsg(throwable.getMessage());
        });
    addToUnsubscription(subscription);
  }

  public void getServiceWithParentId(int parentId) {
    getViewState().stateCategoriesServices(false);
    Subscription subscription = mDataManager.fetchServicesOfCategoryWithId(parentId)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(serviceEntities -> {
          getViewState().setServicesWithParentId(serviceEntities);
          mServiceAllEntities.clear();
          mServiceAllEntities.addAll(serviceEntities);
          mListListCategories.add(new ArrayList<>());
          getViewState().showTextPath(String.valueOf(mPath));
          getViewState().stateCategoriesServices(true);
        }, Timber::e);
    addToUnsubscription(subscription);
  }

  public void getCategoriesWithParentId(int parentId) {
    getViewState().stateCategoriesServices(false);
    Subscription subscription = mDataManager.fetchCategoriesOfCategoryWithId(parentId)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(categoryEntities -> {
          getViewState().setCategoriesWithParentId(categoryEntities);
          mListListCategories.add(categoryEntities);
          getViewState().showTextPath(String.valueOf(mPath));
          getViewState().stateCategoriesServices(true);
        }, Timber::e);
    addToUnsubscription(subscription);
  }

  public void fetchAllServices() {
    getViewState().showProgressBarAllServices();
    Subscription subscription = mDataManager.fetchAllServices()
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
    mBookingEntity.setServiceId(String.valueOf(mServiceAllEntities.get(position).getServiceId()));
    mBookingEntity.setServiceName(String.valueOf(mServiceAllEntities.get(position).getTitle()));
    mBookingEntity.setDurationServices(String.valueOf(mServiceAllEntities.get(position).getTime()));
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

  public void showTextPath(String text) {
    mPath.append(text).append(SLASH);
    mPathList.add(text);
  }

  private void backCategories() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.BackCategories.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(backCategories -> {
          if (mListListCategories.size() > 1) {
            getViewState().setCategoriesWithParentId(
                mListListCategories.get(mListListCategories.size() - 2));
            mListListCategories.remove(mListListCategories.size() - 1);

            mPath.setLength(0);
            mPathList.remove(mPathList.size() - 1);
            for (String s : mPathList) {
              mPath.append(s).append(SLASH);
            }
            getViewState().showTextPath(String.valueOf(mPath));

            if (mListListCategories.size() == 1) {
              getViewState().hideTextPath();
            }
          } else {
            getViewState().backCategory();
          }
        });
    addToUnsubscription(subscription);
  }
}
