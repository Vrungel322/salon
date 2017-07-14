package com.apps.twelve.floor.salon.feature.my_booking.fragments;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.data.model.LastBookingEntity;
import com.apps.twelve.floor.salon.feature.my_booking.presenters.BookDetailsFragmentPresenter;
import com.apps.twelve.floor.salon.feature.my_booking.views.IBookDetailsFragmentView;
import com.apps.twelve.floor.salon.feature.start_point.activities.StartActivity;
import com.apps.twelve.floor.salon.utils.Constants;
import com.apps.twelve.floor.salon.utils.Converters;
import com.apps.twelve.floor.salon.utils.DialogFactory;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static com.apps.twelve.floor.salon.utils.Constants.Other.SERVER_ANSWER_EMPTY_STRING;

/**
 * Created by Alexandra on 13.07.2017.
 */

public class BookDetailsFragment extends BaseFragment implements IBookDetailsFragmentView {
  @InjectPresenter BookDetailsFragmentPresenter mBookDetailsFragmentPresenter;

  @BindView(R.id.tvServiceName) TextView mTextViewServiceName;
  @BindView(R.id.ivServiceImg) CircleImageView mImageViewService;
  @BindView(R.id.tvAboutService) TextView mTextViewServiceDetails;
  @BindView(R.id.tvOldPrice) TextView mTextViewOldPrice;
  @BindView(R.id.tvServicePrice) TextView mTextViewServicePrice;
  @BindView(R.id.tvPriceBonus) TextView mTextViewBonusPrice;
  @BindView(R.id.ivBonusPrice) ImageView mImageViewBonusPrice;
  @BindView(R.id.tvServiceTime) TextView mTextViewServiceTime;
  @BindView(R.id.tvDuration) TextView mTextViewDuration;
  @BindView(R.id.tvRemainTime) TextView mTextViewRemainTime;
  @BindView(R.id.bPostpone) Button mButtonPostpone;
  @BindView(R.id.bCancel) Button mButtonCancel;
  @BindView(R.id.tvMasterName) TextView mTextViewMasterName;
  @BindView(R.id.tvAboutMaster) TextView mTextViewAboutMaster;
  @BindView(R.id.ivMasterImg) CircleImageView mImageViewMaster;
  @BindView(R.id.pbCancelBooking) ProgressBar mProgressBar;

  private LastBookingEntity mBookingEntity;
  private CountDownTimer mDownTimer;
  private AlertDialog mRemoveBookingDialog;

  public BookDetailsFragment() {
    super(R.layout.fragment_book_details);
  }

  public static BookDetailsFragment newInstance(LastBookingEntity entity) {
    Bundle args = new Bundle();
    args.putParcelable(Constants.FragmentsArgumentKeys.LAST_BOOKING_ENTITY_KEY, entity);
    BookDetailsFragment fragment = new BookDetailsFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mBookingEntity =
        getArguments().getParcelable(Constants.FragmentsArgumentKeys.LAST_BOOKING_ENTITY_KEY);

    setUpServiceBlock();
    setUpTimeBlock();
    setUpMasterBlock();

    TypedValue value = new TypedValue();
    getActivity().getTheme().resolveAttribute(R.attr.colorAccent, value, true);
  }

  private void setUpServiceBlock() {
    mTextViewServiceName.setText(mBookingEntity.getServiceName());
    Glide.with(getContext()).load(mBookingEntity.getServiceImage()).into(mImageViewService);
    mTextViewServiceDetails.setText(mBookingEntity.getServiceDescription());
    if (!mBookingEntity.getServiceBonusPrice().equals(SERVER_ANSWER_EMPTY_STRING)) {
      mTextViewBonusPrice.setText(mBookingEntity.getServiceBonusPrice());
      mImageViewBonusPrice.setVisibility(View.VISIBLE);
    } else {
      mTextViewBonusPrice.setVisibility(View.GONE);
      mImageViewBonusPrice.setVisibility(View.GONE);
    }

    if (!mBookingEntity.getServiceNewPrice().equals(SERVER_ANSWER_EMPTY_STRING)) {
      mTextViewOldPrice.setText(mBookingEntity.getServicePrice());
      mTextViewOldPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
      mTextViewOldPrice.setVisibility(View.VISIBLE);
      mTextViewServicePrice.setText(mBookingEntity.getServiceNewPrice());
      mTextViewServicePrice.setTypeface(null, Typeface.BOLD_ITALIC);
    } else {
      mTextViewOldPrice.setVisibility(View.GONE);
      mTextViewServicePrice.setText(mBookingEntity.getServicePrice());
      mTextViewServicePrice.setTypeface(null, Typeface.NORMAL);
    }
  }

  private void setUpTimeBlock() {
    mTextViewServiceTime.setText(getContext().getString(R.string.booking_date_and_time,
        Converters.dateFromSeconds(String.valueOf(mBookingEntity.getServiceTime())),
        Converters.timeFromSeconds(String.valueOf(mBookingEntity.getServiceTime()))));
    mTextViewDuration.setText(mBookingEntity.getServiceDuration());

    if (mDownTimer != null) {
      mDownTimer.cancel();
    }
    mDownTimer =
        new CountDownTimer(mBookingEntity.getServiceTime() * 1000L - System.currentTimeMillis(),
            1000) {
          public void onTick(long millisUntilFinished) {
            if (TimeUnit.MILLISECONDS.toDays(millisUntilFinished) < 1) {
              mTextViewRemainTime.setText(String.format(Locale.getDefault(), "%02d:%02d",
                  ((millisUntilFinished / (1000 * 60 * 60))),
                  ((millisUntilFinished / (1000 * 60)) % 60)));
            } else {
              mTextViewRemainTime.setText(mContext.getString(R.string.booking_days_left,
                  TimeUnit.MILLISECONDS.toDays(millisUntilFinished)));
            }
          }

          public void onFinish() {
            mTextViewRemainTime.setText(R.string.time_is_up);
          }
        }.start();
    mButtonPostpone.setOnClickListener(v -> {
      mBookDetailsFragmentPresenter.openPostponeFragment();
    });
    mButtonCancel.setOnClickListener(v -> {
      mBookDetailsFragmentPresenter.showConfirmationDialog();
    });
  }

  private void setUpMasterBlock() {
    mTextViewMasterName.setText(mBookingEntity.getMasterName());
    mTextViewAboutMaster.setText(mBookingEntity.getMasterDescription());
    Glide.with(getContext()).load(mBookingEntity.getMasterPhoto()).into(mImageViewMaster);
  }

  @Override public void openPostponeFragment() {
    mNavigator.addFragmentBackStack((StartActivity) getContext(), R.id.container_main,
        PostponeFragment.newInstance(mBookingEntity.getServiceName(),
            mBookingEntity.getMasterName(), mBookingEntity.getMasterId(), mBookingEntity.getId()));
  }

  @Override public void updateTimeInfo(Integer time) {
    mBookingEntity.setServiceTime(time);
    setUpTimeBlock();
  }

  @Override public void showConfirmationDialog() {
    mRemoveBookingDialog =
        DialogFactory.createAlertDialogBuilder(getContext(), R.string.cancel_booking,
            R.string.confirm_cancel_booking, R.drawable.ic_report_problem)
            .setPositiveButton(R.string.confirm, (dialog, which) -> {
              mBookDetailsFragmentPresenter.cancelOrder(mBookingEntity.getId());
              mBookDetailsFragmentPresenter.cancelAlertDialog();
            })
            .setNegativeButton(R.string.cancel,
                (dialog, which) -> mBookDetailsFragmentPresenter.cancelAlertDialog())
            .create();
    mRemoveBookingDialog.show();
    mRemoveBookingDialog.setOnCancelListener(
        dialog -> mBookDetailsFragmentPresenter.cancelAlertDialog());
  }

  @Override public void cancelAlertDialog() {
    if (mRemoveBookingDialog != null) {
      mRemoveBookingDialog.dismiss();
    }
  }

  @Override public void showProgressBar() {
    mProgressBar.setVisibility(View.VISIBLE);
  }

  @Override public void hideProgressBar() {
    mProgressBar.setVisibility(View.GONE);
  }

  @Override public void closeFragment() {
    getActivity().onBackPressed();
  }

  @Override public void onDestroyView() {
    mDownTimer.cancel();
    super.onDestroyView();
  }
}