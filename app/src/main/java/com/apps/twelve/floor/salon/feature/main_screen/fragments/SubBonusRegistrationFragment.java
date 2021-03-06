package com.apps.twelve.floor.salon.feature.main_screen.fragments;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.feature.main_screen.presenters.SubBonusRegistrationFragmentPresenter;
import com.apps.twelve.floor.salon.feature.main_screen.views.ISubBonusRegestrationFragmentView;
import com.apps.twelve.floor.salon.feature.my_bonus.fragments.MyBonusFragment;
import com.apps.twelve.floor.salon.utils.Constants;
import com.apps.twelve.floor.salon.utils.ThemeUtils;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Vrungel on 27.02.2017.
 */

public class SubBonusRegistrationFragment extends BaseFragment
    implements ISubBonusRegestrationFragmentView {

  @InjectPresenter SubBonusRegistrationFragmentPresenter mSubBonusRegistrationFragmentPresenter;

  @BindView(R.id.ivGift) ImageView mImageViewUserAvatar;
  @BindView(R.id.ivInfo) ImageView mImageViewInfo;
  @BindView(R.id.bRegistration) TextView mButtonRegistration;
  @BindView(R.id.tvBonusRegistration) TextView mTextViewBonusRegistration;

  @BindView(R.id.cvBonusRegistration) CardView mCardViewBonusRegistration;

  public SubBonusRegistrationFragment() {
    super(R.layout.fragment_sub_bonus_registration);
  }

  public static SubBonusRegistrationFragment newInstance() {
    Bundle args = new Bundle();
    SubBonusRegistrationFragment fragment = new SubBonusRegistrationFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mButtonRegistration.setPaintFlags(
        mButtonRegistration.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
  }

  @OnClick(R.id.ivInfo) public void ivInfoClicked() {
    showAlertMessage("Info", "Some useful info");
  }

  @Override public void onResume() {
    super.onResume();
    mSubBonusRegistrationFragmentPresenter.showCardBonusOrRegistration();
  }

  @Override public void setBonusCount(String bonusCount) {
    mTextViewBonusRegistration.setTextSize(32);
    mTextViewBonusRegistration.setText(getString(R.string.bonus_card));
    mButtonRegistration.setText(getString(R.string.bonus_measure, bonusCount));
  }

  @Override public void showCardRegistration() {
    mTextViewBonusRegistration.setTextSize(14);
    mTextViewBonusRegistration.setText(getString(R.string.bonus_registration_text));
    mButtonRegistration.setText(getString(R.string.registration));
  }

  @OnClick(R.id.cvBonusRegistration) public void cvBonusRegistrationClicked() {
    if (!mAuthorizationManager.isAuthorized()) {
      mAuthorizationManager.startSignInActivity((AppCompatActivity) getActivity(),
          ThemeUtils.getThemeActionBar(getContext()));
    } else {
      mNavigator.addFragmentTagClearBackStackNotCopy((AppCompatActivity) getActivity(),
          R.id.container_main, MyBonusFragment.newInstance(),
          Constants.FragmentTag.MY_BONUS_FRAGMENT);
    }
  }
}
