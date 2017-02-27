package com.apps.twelve.floor.salon.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.AppBarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.R;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.tapadoo.alerter.Alerter;
import javax.inject.Inject;

/**
 * Created by John on 27.01.2017.
 */

public abstract class BaseFragment extends MvpAppCompatFragment {

  @Inject protected Context mContext;
  @Inject protected Navigator mNavigator;

  private final int mLayoutId;

  public BaseFragment(int mLayoutId) {
    this.mLayoutId = mLayoutId;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    App.getAppComponent().inject(this);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    final View fragmentView = inflater.inflate(mLayoutId, container, false);
    ButterKnife.bind(this, fragmentView);
    AppBarLayout appBarLayout = (AppBarLayout) getActivity().findViewById(R.id.appBar);
    appBarLayout.setExpanded(true, false);
    return fragmentView;
  }

  protected void showAlertMessage(String title, String message) {
    //Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    Alerter.create(getActivity())
        .setTitle(title)
        .setText(message)
        .setBackgroundColor(R.color.colorAccent)
        .setOnClickListener(view -> {
        })
        .show();
  }

  protected void showToastMessage(String message) {
    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
  }

  protected void showToastMessage(@StringRes int id) {
    Toast.makeText(mContext, id, Toast.LENGTH_SHORT).show();
  }
}
