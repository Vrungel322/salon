package com.apps.twelve.floor.salon.feature.main_screen.fragments;

import android.content.Intent;
import android.net.Uri;
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
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.authorization.floor12.authorization.logic.authorization.activities.ModuleSignInActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by Vrungel on 27.02.2017.
 */

public class SubBonusRegistrationFragment extends BaseFragment
    implements ISubBonusRegestrationFragmentView {
  @BindView(R.id.ivUserAvatar) ImageView mImageViewUserAvatar;
  @BindView(R.id.ivInfo) ImageView mImageViewInfo;
  @BindView(R.id.bRegistration) TextView mButtonRegistration;
  @BindView(R.id.tvBonusRegistration) TextView mTextViewBonusRegistration;
  @BindView(R.id.cvBonusRegistration) CardView mCardViewBonusRegistration;

  @InjectPresenter SubBonusRegistrationFragmentPresenter mSubBonusRegistrationFragmentPresenter;

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
  }

  @OnClick(R.id.ivInfo) public void ivInfoClicked() {
    showAlertMessage("Info", "Some useful info");
  }

  @Override public void onResume() {
    super.onResume();
    if (!mAuthManager.isAuthorized()) {
      mTextViewBonusRegistration.setText(getString(R.string.bonus_registration_text));
      mButtonRegistration.setText(getString(R.string.registration));
    } else {
      mSubBonusRegistrationFragmentPresenter.fetchBonusCount();
      mSubBonusRegistrationFragmentPresenter.fetchUserPhoto();
    }
  }

  @Override public void setBonusCount(String bonusCount) {
    mTextViewBonusRegistration.setTextSize(32);
    mTextViewBonusRegistration.setText(getString(R.string.bonus_card));
    mButtonRegistration.setText(getString(R.string.bonus_measure, bonusCount));
  }

  @Override public void setUserPhoto(String photoUri) {
    Picasso.with(getContext()).load(Uri.parse(photoUri)).into(mImageViewUserAvatar);
  }

  @OnClick(R.id.cvBonusRegistration) public void cvBonusRegistrationClicked() {
    if (!mAuthManager.isAuthorized()) {
      mNavigator.startActivity((AppCompatActivity) getActivity(),
          new Intent(getActivity(), ModuleSignInActivity.class));
    } else {
      mNavigator.addFragmentTagClearBackStackNotCopy((AppCompatActivity) getActivity(),
          R.id.container_main, MyBonusFragment.newInstance(),
          Constants.FragmentTag.MY_BONUS_FRAGMENT);
    }
  }
}
