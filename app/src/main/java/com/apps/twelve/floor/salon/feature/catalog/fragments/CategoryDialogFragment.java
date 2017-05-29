package com.apps.twelve.floor.salon.feature.catalog.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.feature.catalog.presenters.CategoryDialogFragmentPresenter;
import com.apps.twelve.floor.salon.feature.catalog.views.ICategoryDialogFragmentView;
import com.apps.twelve.floor.salon.feature.start_point.activities.StartActivity;
import com.arellomobile.mvp.MvpDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Vrungel on 29.05.2017.
 */

public class CategoryDialogFragment extends MvpDialogFragment implements ICategoryDialogFragmentView {

  @InjectPresenter CategoryDialogFragmentPresenter mCategoryDialogFragmentPresenter;
  private Unbinder mUnbinder;
  //
  //@Override public void onCreate(Bundle savedInstanceState) {
  //  super.onCreate(savedInstanceState);
  //  App.getAppComponent().inject(this);
  //}

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    final View fragmentView = inflater.inflate(R.layout.fragment_category_dialog, container, false);
    mUnbinder = ButterKnife.bind(this, fragmentView);
    return fragmentView;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    mUnbinder.unbind();
  }
}
