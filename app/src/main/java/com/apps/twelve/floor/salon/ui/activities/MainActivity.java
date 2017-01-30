package com.apps.twelve.floor.salon.ui.activities;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
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
  @BindView(R.id.tvTest) TextView tvTest;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_main);
    super.onCreate(savedInstanceState);

    mainActivityPresenter.showTestText("Toast Test Text");
  }

  @Override protected void inject() {
    App.getsAppComponent().inject(this);
  }

  @Override public void showText(String string) {
    if (mNavigator != null) {
      tvTest.setText(string);
      this.showToastMessage(string);
    }
  }
}
