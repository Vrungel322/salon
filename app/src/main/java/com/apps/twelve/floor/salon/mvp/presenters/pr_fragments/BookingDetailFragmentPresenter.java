package com.apps.twelve.floor.salon.mvp.presenters.pr_fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.data.DataManager;
import com.apps.twelve.floor.salon.mvp.data.model.BookingEntity;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.views.IBookingDetailFragmentView;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;

/**
 * Created by Vrungel on 23.03.2017.
 */

@InjectViewState public class BookingDetailFragmentPresenter
    extends BasePresenter<IBookingDetailFragmentView> {
  @Inject BookingEntity mBookingEntity;
  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getBookingComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().setUpViewPager();
  }
}
