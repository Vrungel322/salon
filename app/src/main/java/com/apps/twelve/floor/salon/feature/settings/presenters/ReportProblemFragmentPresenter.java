package com.apps.twelve.floor.salon.feature.settings.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.settings.views.IReportProblemFragmentView;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by Alexandra on 05.05.2017.
 */

@InjectViewState public class ReportProblemFragmentPresenter
    extends BasePresenter<IReportProblemFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
  }

  public void sendProblem(String problem) {
    Subscription subscription =
        Observable.just(200).compose(ThreadSchedulers.applySchedulers()).doOnNext(voidResponse -> {
          if (voidResponse == 200) {
            mRxBus.post(new RxBusHelper.UpdateUserInfo());
            getViewState().stopAnimation();
          } else {
            getViewState().showAlert();
          }
        }).delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(response -> {
          if (response == 200) {
            getViewState().closeFragment();
          } else {
            getViewState().revertAnimation();
          }
        }, throwable -> {
          Timber.e(throwable);
          showMessageConnectException(throwable);
        });
    addToUnsubscription(subscription);
  }
}