package com.apps.twelve.floor.salon.ui.activities;

import android.os.Bundle;
import butterknife.ButterKnife;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.presenters.MainActivityPresenter;
import com.apps.twelve.floor.salon.mvp.views.IMainActivityView;
import com.apps.twelve.floor.salon.ui.base.BaseActivity;
import com.apps.twelve.floor.salon.ui.base.Navigator;
import com.arellomobile.mvp.presenter.InjectPresenter;
import javax.inject.Inject;

public class MainActivity extends BaseActivity implements IMainActivityView {

  @InjectPresenter MainActivityPresenter mainActivityPresenter;

  @Inject Navigator mNavigator;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    App.getsAppComponent().inject(this);

    mainActivityPresenter.showTestText("Toast Test Text");
  }

  @Override public void showText(String string) {
    if (mNavigator != null) {
      this.showToastMessage(string);
    }
  }
}
