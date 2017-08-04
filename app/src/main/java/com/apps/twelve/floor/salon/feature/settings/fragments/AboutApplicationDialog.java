package com.apps.twelve.floor.salon.feature.settings.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.BuildConfig;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.feature.settings.presenters.AboutApplicationDialogPresenter;
import com.apps.twelve.floor.salon.feature.settings.views.IAboutApplicationDialog;
import com.arellomobile.mvp.MvpDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by John on 29.06.2017.
 */

public class AboutApplicationDialog extends MvpDialogFragment implements IAboutApplicationDialog {

  @InjectPresenter AboutApplicationDialogPresenter mAboutApplicationDialogPresenter;
  @BindView(R.id.tvAppVersion) TextView mTvAppVersion;
  @BindView(R.id.tvDevelopBy) TextView mTvDevelopBy;

  private Unbinder mUnbinder;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    App.getAppComponent().inject(this);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      Bundle savedInstanceState) {
    final View fragmentView =
        inflater.inflate(R.layout.fragment_about_app_dialog, container, false);
    mUnbinder = ButterKnife.bind(this, fragmentView);
    return fragmentView;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mTvAppVersion.setText(getString(R.string.version, BuildConfig.VERSION_NAME));
    mTvDevelopBy.setMovementMethod(LinkMovementMethod.getInstance());
  }

  @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
    return new Dialog(getActivity(), R.style.ThemeDialog);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    mUnbinder.unbind();
  }

  @OnClick(R.id.textViewOk) public void onViewClickedOk() {
    dismiss();
  }
}
