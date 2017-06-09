package com.apps.twelve.floor.salon.feature.my_booking.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.MvpBaseRecyclerAdapter;
import com.apps.twelve.floor.salon.base.Navigator;
import com.apps.twelve.floor.salon.data.model.LastBookingEntity;
import com.apps.twelve.floor.salon.feature.my_booking.fragments.PostponeFragment;
import com.apps.twelve.floor.salon.feature.my_booking.presenters.MyBookingAdapterPresenter;
import com.apps.twelve.floor.salon.feature.my_booking.views.IMyBookingAdapterView;
import com.apps.twelve.floor.salon.feature.start_point.activities.StartActivity;
import com.apps.twelve.floor.salon.utils.Converters;
import com.apps.twelve.floor.salon.utils.DialogFactory;
import com.arellomobile.mvp.MvpDelegate;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Vrungel on 28.02.2017.
 */

public class MyBookingAdapter extends MvpBaseRecyclerAdapter<MyBookingAdapter.MyBookingViewHolder>
    implements IMyBookingAdapterView {

  private final Context mContext;
  private final Activity mActivity;
  private final Navigator mNavigator;

  @InjectPresenter MyBookingAdapterPresenter mMyBookingAdapterPresenter;

  private ArrayList<LastBookingEntity> mBookingEntities = new ArrayList<>();
  private AlertDialog mRemoveBookingDialog;

  public MyBookingAdapter(MvpDelegate<?> parentDelegate, Context context, Activity activity,
      Navigator navigator) {
    super(parentDelegate, "MyBookingAdapterPresenter");
    this.mContext = context;
    this.mActivity = activity;
    this.mNavigator = navigator;
  }

  public void addListBookingEntity(List<LastBookingEntity> bookingEntities) {
    mBookingEntities.clear();
    mBookingEntities.addAll(bookingEntities);
    notifyDataSetChanged();
  }

  @Override public MyBookingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new MyBookingViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_booking, parent, false));
  }

  @Override public void onBindViewHolder(MyBookingViewHolder holder, int position) {
    if (position == 0) {
      TypedValue value = new TypedValue();
      mContext.getTheme().resolveAttribute(R.attr.colorAccent, value, true);
      holder.mConstraintLayoutBooking.setBackgroundColor(
          ContextCompat.getColor(holder.mConstraintLayoutBooking.getContext(), value.resourceId));
      holder.mConstraintLayoutBooking.getBackground().setAlpha(30);
      holder.view.setVisibility(View.VISIBLE);
    } else {
      int[] attrs = new int[] { R.attr.selectableItemBackground };
      TypedArray ta = mContext.obtainStyledAttributes(attrs);
      Drawable drawableFromTheme = ta.getDrawable(0);
      ta.recycle();
      holder.mConstraintLayoutBooking.setBackground(drawableFromTheme);
      holder.view.setVisibility(View.INVISIBLE);
    }
    Glide.with(holder.mImageViewServicePhoto.getContext())
        .load(mBookingEntities.get(position).getImageUri())
        .into(holder.mImageViewServicePhoto);

    holder.mTextViewServiceName.setText(mBookingEntities.get(position).getServiceName());
    holder.mTextViewServiceTime.setText(Converters.fullDateWithTimeFromSeconds(
        String.valueOf(mBookingEntities.get(position).getServiceTime())));

    //for DownTimer
    if (holder.mDownTimer != null) {
      holder.mDownTimer.cancel();
    }
    holder.mDownTimer = new CountDownTimer(
        mBookingEntities.get(holder.getAdapterPosition()).getServiceTime() * 1000L
            - System.currentTimeMillis(), 1000) {

      public void onTick(long millisUntilFinished) {
        holder.mTextViewRemainTime.setText(String.format(Locale.getDefault(), "%02d:%02d",
            ((millisUntilFinished / (1000 * 60 * 60))),
            ((millisUntilFinished / (1000 * 60)) % 60)));
      }

      public void onFinish() {
        holder.mTextViewRemainTime.setText(R.string.time_is_up);
        holder.mButtonPostpone.setVisibility(View.GONE);
        holder.mButtonCancel.setVisibility(View.GONE);
      }
    }.start();

    holder.mButtonPostpone.setOnClickListener(
        v -> mMyBookingAdapterPresenter.openPostponeFragment(position));
    holder.mButtonCancel.setOnClickListener(
        v -> mMyBookingAdapterPresenter.showConfirmationDialog(position));
  }

  @Override public int getItemCount() {
    return mBookingEntities.size();
  }

  @Override public void openPostponeFragment(int position) {
    mNavigator.addFragmentBackStack((StartActivity) mActivity, R.id.container_main,
        PostponeFragment.newInstance(mBookingEntities.get(position).getServiceName(),
            mBookingEntities.get(position).getMasterName(),
            mBookingEntities.get(position).getMasterId(),
            /* entity_id */ mBookingEntities.get(position).getId()));
  }

  @Override public void showConfirmationDialog(int position) {
    mRemoveBookingDialog = DialogFactory.createAlertDialogBuilder(mContext, R.string.cancel_booking,
        R.string.confirm_cancel_booking, R.drawable.ic_report_problem)
        .setPositiveButton(R.string.confirm, (dialog, which) -> {
          mMyBookingAdapterPresenter.cancelOrder(mBookingEntities.get(position).getId());
          mMyBookingAdapterPresenter.cancelAlertDialog();
        })
        .setNegativeButton(R.string.cancel,
            (dialog, which) -> mMyBookingAdapterPresenter.cancelAlertDialog())
        .create();
    mRemoveBookingDialog.show();
    mRemoveBookingDialog.setOnCancelListener(
        dialog -> mMyBookingAdapterPresenter.cancelAlertDialog());
  }

  @Override public void cancelAlertDialog() {
    if (mRemoveBookingDialog != null) {
      mRemoveBookingDialog.dismiss();
    }
  }

  public static class MyBookingViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.view) TextView view;
    @BindView(R.id.clLastBooking) ConstraintLayout mConstraintLayoutBooking;
    @BindView(R.id.ivServicePhoto) ImageView mImageViewServicePhoto;
    @BindView(R.id.tvServiceName) TextView mTextViewServiceName;
    @BindView(R.id.tvServiceTime) TextView mTextViewServiceTime;
    @BindView(R.id.tvRemainTime) TextView mTextViewRemainTime;
    @BindView(R.id.bPostpone) Button mButtonPostpone;
    @BindView(R.id.bCancel) Button mButtonCancel;

    private CountDownTimer mDownTimer;

    MyBookingViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
