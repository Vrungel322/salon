package com.apps.twelve.floor.salon.ui.fragments;

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
import com.apps.twelve.floor.salon.mvp.presenters.fragments.SubBonusRegistrationPresenter;
import com.apps.twelve.floor.salon.mvp.views.ISubBonusRegestrationFragmentView;
import com.apps.twelve.floor.salon.ui.activities.MainActivity;
import com.apps.twelve.floor.salon.ui.activities.RegistrationActivity;
import com.apps.twelve.floor.salon.ui.activities.StartActivity;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.apps.twelve.floor.salon.utils.Constants;
import com.arellomobile.mvp.presenter.InjectPresenter;
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

  @InjectPresenter SubBonusRegistrationPresenter mSubBonusRegistrationPresenter;

  public static SubBonusRegistrationFragment newInstance(String mode) {
    Bundle args = new Bundle();
    args.putString(Constants.FragmentsArgumentKeys.BONUS_REGISTRATION_KEY, mode);
    SubBonusRegistrationFragment fragment = new SubBonusRegistrationFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public SubBonusRegistrationFragment() {
    super(R.layout.fragment_sub_bonus_registration);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    Picasso.with(getContext())
        .load(Uri.parse(
            "http://i0.kym-cdn.com/photos/images/newsfeed/000/096/044/trollface.jpg?1296494117"))
        .into(mImageViewUserAvatar);
    if (getArguments().getString(Constants.FragmentsArgumentKeys.BONUS_REGISTRATION_KEY)
        .equals(Constants.FragmentToShow.REGISTRATION)) {
      mTextViewBonusRegistration.setText(
          Html.fromHtml(getString(R.string.bonus_registration_text)));
      mButtonRegistration.setText(getString(R.string.registration));
    } else {
      mTextViewBonusRegistration.setTextSize(32);
      mTextViewBonusRegistration.setText(getString(R.string.bonus_card));
      mButtonRegistration.setText("100 баллов");
    }
  }

  @OnClick(R.id.ivInfo) public void ivInfoClicked() {
    showAlertMessage("Info", "Some useful info");
  }

  @OnClick(R.id.cvBonusRegistration) public void cvBonusRegistrationClicked() {
    if (getArguments().getString(Constants.FragmentsArgumentKeys.BONUS_REGISTRATION_KEY)
        .equals(Constants.FragmentToShow.BONUS)) {
      mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(),
          R.id.container_main, MyBonusFragment.newInstance());
    } else {
      mNavigator.startActivity(
          (AppCompatActivity) getActivity(), new Intent(getActivity(), RegistrationActivity.class));
    }
  }
}
