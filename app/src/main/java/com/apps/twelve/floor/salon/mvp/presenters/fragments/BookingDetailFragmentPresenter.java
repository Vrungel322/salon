package com.apps.twelve.floor.salon.mvp.presenters.fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.data.DataManager;
import com.apps.twelve.floor.salon.mvp.data.model.BookingEntity;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.IBookingDetailFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IBookingDetailFragmentView;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by Vrungel on 23.03.2017.
 */

@InjectViewState public class BookingDetailFragmentPresenter
    extends BasePresenter<IBookingDetailFragmentView> implements IBookingDetailFragmentPresenter {
  @Inject BookingEntity mBookingEntity;
  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getBookingComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().setUpViewPager();
    Timber.e(String.valueOf(mDataManager.hashCode()));
    Timber.e(String.valueOf(mBookingEntity.hashCode()));
  }
}
