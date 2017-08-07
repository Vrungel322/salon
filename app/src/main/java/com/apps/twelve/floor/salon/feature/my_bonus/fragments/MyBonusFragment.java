package com.apps.twelve.floor.salon.feature.my_bonus.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.feature.booking.activities.BookingActivity;
import com.apps.twelve.floor.salon.feature.my_bonus.presenters.MyBonusFragmentPresenter;
import com.apps.twelve.floor.salon.feature.my_bonus.views.IMyBonusFragmentView;
import com.apps.twelve.floor.salon.feature.start_point.activities.StartActivity;
import com.apps.twelve.floor.salon.utils.DialogFactory;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Vrungel on 21.02.2017.
 */

public class MyBonusFragment extends BaseFragment implements IMyBonusFragmentView {

  @InjectPresenter MyBonusFragmentPresenter mMyBonusFragmentPresenter;

  @BindView(R.id.tvCountBonus) TextView mTvCountBonus;
  @BindView(R.id.btnHowBonusWorks) Button mButtonHowBonusWorks;
  @BindView(R.id.btnSendCode) Button mButtonSendCode;
  @BindView(R.id.bSendFriendsCode) Button mButtonSendFriendsCode;
  @BindView(R.id.tvYour) TextView mTextViewYourCode;
  @BindView(R.id.etFriendsCode) EditText mEditTextFriendsCode;
  @BindView(R.id.srlRefreshLayout) SwipeRefreshLayout mSwipeRefreshLayout;

  public MyBonusFragment() {
    super(R.layout.fragment_my_bonus);
  }

  public static MyBonusFragment newInstance() {
    Bundle args = new Bundle();
    MyBonusFragment fragment = new MyBonusFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    if (getActivity() instanceof StartActivity) {
      ((StartActivity) getActivity()).setTitleAppBar(R.string.bonus);
    }

    if (getActivity() instanceof BookingActivity) {
      ((BookingActivity) getActivity()).setTitleAppBar(R.string.bonus);
    }

    mButtonHowBonusWorks.setOnClickListener(c -> {
      if (getActivity() instanceof StartActivity) {
        mNavigator.addFragmentBackStack((StartActivity) getActivity(), R.id.container_main,
            BonusHowFragment.newInstance());
      } else {
        mNavigator.addFragmentBackStack((BookingActivity) getActivity(), R.id.container_booking,
            BonusHowFragment.newInstance());
      }
    });

    mMyBonusFragmentPresenter.setUserId();
    mButtonSendCode.setOnClickListener(c -> {
      if (mAuthorizationManager.isAuthorized()) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
            getResources().getString(R.string.bonus_share_msg_text,
                mTextViewYourCode.getText().toString()));
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
      } else {
        if (getActivity() instanceof StartActivity) {
          mMyBonusFragmentPresenter.showAuthDialogBooking();
        } else {
          mMyBonusFragmentPresenter.showAuthDialog();
        }
      }
    });

    TypedValue value = new TypedValue();
    getActivity().getTheme().resolveAttribute(R.attr.colorAccent, value, true);
    mSwipeRefreshLayout.setColorSchemeResources(value.resourceId);
    mSwipeRefreshLayout.setOnRefreshListener(() -> mMyBonusFragmentPresenter.getBonusCount());
  }

  @Override public void onDestroyView() {
    if (getActivity() instanceof StartActivity) {
      ((StartActivity) getActivity()).setTitleAppBar(R.string.title_activity_start);
    }
    if (getActivity() instanceof BookingActivity) {
      ((BookingActivity) getActivity()).setTitleAppBar(R.string.book_create);
    }
    super.onDestroyView();
  }

  @Override public void setBonusCount(Integer count) {
    mTvCountBonus.setText(String.valueOf(count));
  }

  @OnClick(R.id.tvHistory) void openHistory() {
    if (getActivity() instanceof StartActivity) {
      if (mAuthorizationManager.isAuthorized()) {
        mNavigator.addFragmentBackStack((StartActivity) getActivity(), R.id.container_main,
            BonusHistoryFragment.newInstance());
      } else {
        mMyBonusFragmentPresenter.showAuthDialog();
      }
    } else {
      if (mAuthorizationManager.isAuthorized()) {
        mNavigator.addFragmentBackStack((BookingActivity) getActivity(), R.id.container_booking,
            BonusHistoryFragment.newInstance());
      } else {
        mMyBonusFragmentPresenter.showAuthDialogBooking();
      }
    }
  }

  @OnClick(R.id.bSendFriendsCode) void bSendFriendsCodeClicked() {
    if (mTextViewYourCode.getText().toString().equals(mEditTextFriendsCode.getText().toString())) {
      showToastMessage(getString(R.string.cannon_invite_yourself));
    } else {
      mMyBonusFragmentPresenter.sendFriendsCode(mEditTextFriendsCode.getText().toString());
    }
  }

  @Override public void startRefreshingView() {
    if (!mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(true);
  }

  @Override public void stopRefreshingView() {
    if (mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(false);
  }

  @Override public void showThankYouDialog() {
    DialogFactory.createAlertDialogBuilder(getContext(), getString(R.string.thank_you))
        .setMessage(getString(R.string.friend_invited))
        .setPositiveButton(getString(R.string.btn_ok),
            (dialogInterface, i) -> dialogInterface.cancel())
        .create()
        .show();
  }

  @Override public void showErrorToast(String msg) {
    showToastMessage(msg);
  }

  @Override public void setTvCodeToShare(String userId) {
    mTextViewYourCode.setText(userId);
  }
}