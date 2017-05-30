package com.apps.twelve.floor.salon.feature.main_screen.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Html;
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
import com.authorization.floor12.authorization.AuthorizationManager;
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

  AuthorizationManager mManager = AuthorizationManager.getInstance();

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

  @OnClick(R.id.bRegistration) void registration() {
    mNavigator.startActivity((AppCompatActivity) getActivity(),
        new Intent(getActivity(), ModuleSignInActivity.class));
  }

  @Override public void onResume() {
    super.onResume();
    if (!mManager.isAuthorized()) {
      mTextViewBonusRegistration.setText(
          Html.fromHtml(getString(R.string.bonus_registration_text)));
      mButtonRegistration.setText(getString(R.string.registration));
    } else {
      Picasso.with(getContext())
          .load(Uri.parse(
              "http://i0.kym-cdn.com/photos/images/newsfeed/000/096/044/trollface.jpg?1296494117"))
          .into(mImageViewUserAvatar);
      mTextViewBonusRegistration.setTextSize(32);
      mTextViewBonusRegistration.setText(getString(R.string.bonus_card));
      mButtonRegistration.setText("100 баллов");
    }
  }

  @OnClick(R.id.cvBonusRegistration) public void cvBonusRegistrationClicked() {
    if (getArguments().getString(Constants.FragmentsArgumentKeys.BONUS_REGISTRATION_KEY)
        .equals(Constants.FragmentToShow.BONUS)) {
      mNavigator.addFragmentTagClearBackStackNotCopy((AppCompatActivity) getActivity(),
          R.id.container_main, MyBonusFragment.newInstance(),
          Constants.FragmentTag.MY_BONUS_FRAGMENT);
    } else {
      //mNavigator.startActivity((AppCompatActivity) getActivity(),
      //    new Intent(getActivity(), RegistrationActivity.class));
    }
  }
}
