package com.apps.twelve.floor.salon.feature.settings.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.feature.settings.views.IReportProblemFragmentView;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by Alexandra on 05.05.2017.
 */

@InjectViewState public class ReportProblemFragmentPresenter
    extends BasePresenter<IReportProblemFragmentView> {
  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
  }

  public void sendProblem(String problem) {
    Subscription subscription = mDataManager.sendReportProblem(problem)
        .compose(ThreadSchedulers.applySchedulers())
        .doOnNext(reportProblemResponseEntityResponse -> {
          if (reportProblemResponseEntityResponse.code() == 200) {
            getViewState().stopAnimation();
            getViewState().openSnackBarOK();
          } else {
            getViewState().showAlert();
          }
        })
        .delay(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
        .subscribe(reportProblemResponseEntityResponse -> {
          if (reportProblemResponseEntityResponse.code() == 200) {
            getViewState().closeFragment();
          } else {
            getViewState().revertAnimation();
          }
        }, throwable -> {
          Timber.e(throwable);
          showMessageException(throwable);
        });
    addToUnsubscription(subscription);
  }
}