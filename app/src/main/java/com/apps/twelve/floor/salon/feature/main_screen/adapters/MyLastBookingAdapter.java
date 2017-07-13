package com.apps.twelve.floor.salon.feature.main_screen.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.MvpBaseRecyclerAdapter;
import com.apps.twelve.floor.salon.base.Navigator;
import com.apps.twelve.floor.salon.data.model.LastBookingEntity;
import com.apps.twelve.floor.salon.feature.main_screen.presenters.MyLastBookingAdapterPresenter;
import com.apps.twelve.floor.salon.feature.main_screen.views.IMyLastBookingAdapterView;
import com.apps.twelve.floor.salon.feature.my_booking.fragments.PostponeFragment;
import com.apps.twelve.floor.salon.feature.start_point.activities.StartActivity;
import com.apps.twelve.floor.salon.utils.Converters;
import com.apps.twelve.floor.salon.utils.DialogFactory;
import com.arellomobile.mvp.MvpDelegate;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vrungel on 28.02.2017.
 */

public class MyLastBookingAdapter
    extends MvpBaseRecyclerAdapter<MyLastBookingAdapter.MyLastBookingViewHolder>
    implements IMyLastBookingAdapterView {

  private final Context mContext;
  private final Navigator mNavigator;

  @InjectPresenter MyLastBookingAdapterPresenter mMyLastBookingAdapterPresenter;

  private ArrayList<LastBookingEntity> mLastBookingEntities = new ArrayList<>();
  private AlertDialog mRemoveBookingDialog;
  private ProgressBar mProgressBarCancel;

  public MyLastBookingAdapter(MvpDelegate<?> parentDelegate, Context context, Navigator navigator) {
    super(parentDelegate, "MyLastBookingAdapterPresenter");
    this.mContext = context;
    this.mNavigator = navigator;
  }

  public void addListLastBookingEntity(List<LastBookingEntity> lastBookingEntities) {
    mLastBookingEntities.clear();
    mLastBookingEntities.addAll(lastBookingEntities);
    notifyDataSetChanged();
  }

  @Override public MyLastBookingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new MyLastBookingAdapter.MyLastBookingViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_booking, parent, false));
  }

  @Override public void onBindViewHolder(MyLastBookingViewHolder holder, int position) {
    if (position == 0) {
      TypedValue value = new TypedValue();
      mContext.getTheme().resolveAttribute(R.attr.colorAccent, value, true);
      holder.mConstraintLayoutLastBooking.setBackgroundColor(
          ContextCompat.getColor(holder.mConstraintLayoutLastBooking.getContext(),
              value.resourceId));
      holder.mConstraintLayoutLastBooking.getBackground().setAlpha(30);
      holder.view.setVisibility(View.VISIBLE);
    } else {
      int[] attrs = new int[] { R.attr.selectableItemBackground };
      TypedArray ta = mContext.obtainStyledAttributes(attrs);
      Drawable drawableFromTheme = ta.getDrawable(0);
      ta.recycle();
      holder.mConstraintLayoutLastBooking.setBackground(drawableFromTheme);
      holder.view.setVisibility(View.INVISIBLE);
    }
    Glide.with(holder.mImageViewServicePhoto.getContext())
        .load(mLastBookingEntities.get(position).getImageUri())
        .centerCrop()
        .placeholder(
            AppCompatResources.getDrawable(mContext, R.drawable.ic_service_placeholder_24dp))
        .error(AppCompatResources.getDrawable(mContext, R.drawable.ic_service_placeholder_24dp))
        .dontAnimate()
        .into(holder.mImageViewServicePhoto);

    holder.mTextViewServiceName.setText(mLastBookingEntities.get(position).getServiceName());
    holder.mTextViewServiceTime.setText(Converters.fullDateWithTimeFromSeconds(
        String.valueOf(mLastBookingEntities.get(position).getServiceTime())));

    //for DownTimer
    if (holder.mDownTimer != null) {
      holder.mDownTimer.cancel();
      holder.mButtonPostpone.setVisibility(View.VISIBLE);
      holder.mButtonCancel.setVisibility(View.VISIBLE);
    }
    holder.mDownTimer = new CountDownTimer(
        mLastBookingEntities.get(holder.getAdapterPosition()).getServiceTime() * 1000L
            - System.currentTimeMillis(), 1000) {

      public void onTick(long millisUntilFinished) {
        if (TimeUnit.MILLISECONDS.toDays(millisUntilFinished) < 1) {
          holder.mTextViewRemainTime.setText(String.format(Locale.getDefault(), "%02d:%02d",
              ((millisUntilFinished / (1000 * 60 * 60))),
              ((millisUntilFinished / (1000 * 60)) % 60)));
        } else {
          holder.mTextViewRemainTime.setText(mContext.getString(R.string.booking_days_left,
              TimeUnit.MILLISECONDS.toDays(millisUntilFinished)));
        }
      }

      public void onFinish() {
        holder.mTextViewRemainTime.setText(R.string.time_is_up);
        holder.mButtonPostpone.setVisibility(View.GONE);
        holder.mButtonCancel.setVisibility(View.GONE);
      }
    }.start();

    holder.mButtonPostpone.setOnClickListener(
        v -> mMyLastBookingAdapterPresenter.openPostponeFragment(position));
    holder.mButtonCancel.setOnClickListener(v -> {
      mMyLastBookingAdapterPresenter.showConfirmationDialog(position);
      mProgressBarCancel = holder.mProgressBarCancel;
    });
  }

  @Override public int getItemCount() {
    return mLastBookingEntities.size();
  }

  @Override public void openPostponeFragment(int position) {
    mNavigator.addFragmentBackStack((StartActivity) mContext, R.id.container_main,
        PostponeFragment.newInstance(mLastBookingEntities.get(position).getServiceName(),
            mLastBookingEntities.get(position).getMasterName(),
            mLastBookingEntities.get(position).getServiceId(),
            /* entity_id */ mLastBookingEntities.get(position).getId()));
  }

  @Override public void showConfirmationDialog(int position) {
    mRemoveBookingDialog = DialogFactory.createAlertDialogBuilder(mContext, R.string.cancel_booking,
        R.string.confirm_cancel_booking, R.drawable.ic_report_problem)
        .setPositiveButton(R.string.confirm, (dialog, which) -> {
          mMyLastBookingAdapterPresenter.cancelOrder(mLastBookingEntities.get(position).getId());
          mMyLastBookingAdapterPresenter.cancelAlertDialog();
        })
        .setNegativeButton(R.string.cancel,
            (dialog, which) -> mMyLastBookingAdapterPresenter.cancelAlertDialog())
        .create();
    mRemoveBookingDialog.show();
    mRemoveBookingDialog.setOnCancelListener(
        dialog -> mMyLastBookingAdapterPresenter.cancelAlertDialog());
  }

  @Override public void cancelAlertDialog() {
    if (mRemoveBookingDialog != null) {
      mRemoveBookingDialog.dismiss();
    }
  }

  @Override public void showProgressBarCancel() {
    mProgressBarCancel.setVisibility(View.VISIBLE);
  }

  @Override public void hideProgressBarCancel() {
    mProgressBarCancel.setVisibility(View.GONE);
  }

  public static class MyLastBookingViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.view) TextView view;
    @BindView(R.id.clLastBooking) ConstraintLayout mConstraintLayoutLastBooking;
    @BindView(R.id.ivServicePhoto) CircleImageView mImageViewServicePhoto;
    @BindView(R.id.tvServiceName) TextView mTextViewServiceName;
    @BindView(R.id.tvServiceTime) TextView mTextViewServiceTime;
    @BindView(R.id.tvRemainTime) TextView mTextViewRemainTime;
    @BindView(R.id.bPostpone) Button mButtonPostpone;
    @BindView(R.id.bCancel) Button mButtonCancel;
    @BindView(R.id.pbCancelBooking) ProgressBar mProgressBarCancel;

    private CountDownTimer mDownTimer;

    MyLastBookingViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
