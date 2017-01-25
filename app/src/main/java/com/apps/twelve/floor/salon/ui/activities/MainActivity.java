package com.apps.twelve.floor.salon.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.presenters.MainActivityPresenter;
import com.apps.twelve.floor.salon.mvp.views.IMainActivityView;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import javax.inject.Inject;

public class MainActivity extends MvpAppCompatActivity implements IMainActivityView {

  @InjectPresenter MainActivityPresenter mainActivityPresenter;

  @Inject Context context;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    App.getsAppComponent().inject(this);

    mainActivityPresenter.showTestText("Toast Test Text");
  }

  @Override public void showText(String string) {
    Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
  }
}
