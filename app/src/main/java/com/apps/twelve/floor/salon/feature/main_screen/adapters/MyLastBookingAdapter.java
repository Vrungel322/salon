package com.apps.twelve.floor.salon.feature.main_screen.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
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
import com.apps.twelve.floor.salon.feature.main_screen.presenters.MyLastBookingAdapterPresenter;
import com.apps.twelve.floor.salon.feature.main_screen.views.IMyLastBookingAdapterView;
import com.apps.twelve.floor.salon.feature.my_booking.fragments.PostponeFragment;
import com.apps.twelve.floor.salon.feature.start_point.activities.StartActivity;
import com.apps.twelve.floor.salon.utils.Converters;
import com.apps.twelve.floor.salon.utils.DialogFactory;
import com.arellomobile.mvp.MvpDelegate;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Vrungel on 28.02.2017.
 */

public class MyLastBookingAdapter
    extends MvpBaseRecyclerAdapter<MyLastBookingAdapter.MyLastBookingViewHolder>
    implements IMyLastBookingAdapterView {

  private final Context mContext;
  private final Activity mActivity;
  private final Navigator mNavigator;

  @InjectPresenter MyLastBookingAdapterPresenter mMyLastBookingAdapterPresenter;

  private ArrayList<LastBookingEntity> mLastBookingEntities = new ArrayList<>();
  private AlertDialog mRemoveBookingDialog;

  public MyLastBookingAdapter(MvpDelegate<?> parentDelegate, Context context, Activity activity,
      Navigator navigator) {
    super(parentDelegate, "MyLastBookingAdapterPresenter");
    this.mContext = context;
    this.mActivity = activity;
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
      holder.mConstraintLayoutLastBooking.setBackgroundColor(
          ContextCompat.getColor(holder.mConstraintLayoutLastBooking.getContext(),
              R.color.colorMainScreenBookingPressed));
      holder.view.setVisibility(View.VISIBLE);
    }
    Picasso.with(holder.mImageViewServicePhoto.getContext())
        .load(mLastBookingEntities.get(position).getImageUri())
        .into(holder.mImageViewServicePhoto);

    holder.mTextViewServiceName.setText(mLastBookingEntities.get(position).getServiceName());
    holder.mTextViewServiceTime.setText(Converters.fullDateWithTimeFromSeconds(
        String.valueOf(mLastBookingEntities.get(position).getServiceTime())));

    //for DownTimer
    if (holder.mDownTimer != null) {
      holder.mDownTimer.cancel();
    }
    holder.mDownTimer = new CountDownTimer(
        mLastBookingEntities.get(holder.getAdapterPosition()).getServiceTime() * 1000L
            - System.currentTimeMillis(), 1000) {

      public void onTick(long millisUntilFinished) {
        holder.mTextViewRemainTime.setText(String.format(Locale.getDefault(), "%02d:%02d:%02d",
            ((millisUntilFinished / (1000 * 60 * 60))), ((millisUntilFinished / (1000 * 60)) % 60),
            (millisUntilFinished / 1000) % 60));
      }

      public void onFinish() {
        holder.mTextViewRemainTime.setText(R.string.time_is_up);
        holder.mButtonPostpone.setVisibility(View.GONE);
        holder.mButtonCancel.setVisibility(View.GONE);
      }
    }.start();

    holder.mButtonPostpone.setOnClickListener(v -> {
      mNavigator.addFragmentBackStack((StartActivity) mActivity, R.id.container_main,
          PostponeFragment.newInstance(mLastBookingEntities.get(position).getServiceName(),
              mLastBookingEntities.get(position).getMasterName(),
              mLastBookingEntities.get(position).getServiceId(),
              /* entity_id */ mLastBookingEntities.get(position).getId()));
    });
    holder.mButtonCancel.setOnClickListener(
        v -> mMyLastBookingAdapterPresenter.showConfirmationDialog(position));
  }

  @Override public int getItemCount() {
    return mLastBookingEntities.size();
  }

  @Override public void removeBookedServiceFromList(int position) {
    mLastBookingEntities.remove(position);
    notifyItemRemoved(position);
    notifyItemRangeChanged(position, mLastBookingEntities.size());
  }

  @Override public void showConfirmationDialog(int position) {
    mRemoveBookingDialog = DialogFactory.createAlertDialogBuilder(mContext, R.string.cancel_booking,
        R.string.confirm_cancel_booking, R.drawable.ic_report_problem)
        .setPositiveButton(R.string.confirm, (dialog, which) -> {
          mMyLastBookingAdapterPresenter.cancelOrder(position,
              mLastBookingEntities.get(position).getId());
          mMyLastBookingAdapterPresenter.cancelAlertDialog();
        })
        .setNegativeButton(R.string.cancel,
            (dialog, which) -> mMyLastBookingAdapterPresenter.cancelAlertDialog())
        .create();
    mRemoveBookingDialog.show();
  }

  @Override public void cancelAlertDialog() {
    if (mRemoveBookingDialog != null) {
      mRemoveBookingDialog.dismiss();
    }
  }

  public static class MyLastBookingViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.view) TextView view;
    @BindView(R.id.clLastBooking) ConstraintLayout mConstraintLayoutLastBooking;
    @BindView(R.id.ivServicePhoto) ImageView mImageViewServicePhoto;
    @BindView(R.id.tvServiceName) TextView mTextViewServiceName;
    @BindView(R.id.tvServiceTime) TextView mTextViewServiceTime;
    @BindView(R.id.tvRemainTime) TextView mTextViewRemainTime;
    @BindView(R.id.bPostpone) Button mButtonPostpone;
    @BindView(R.id.bCancel) Button mButtonCancel;

    private CountDownTimer mDownTimer;

    MyLastBookingViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
