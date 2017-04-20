package com.apps.twelve.floor.salon.feature.main_screen.adapters;

import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
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
import com.apps.twelve.floor.salon.data.model.LastBookingEntity;
import com.apps.twelve.floor.salon.feature.main_screen.presenters.MyLastBookingAdapterPresenter;
import com.apps.twelve.floor.salon.feature.main_screen.views.IMyLastBookingAdapterView;
import com.apps.twelve.floor.salon.utils.Converters;
import com.arellomobile.mvp.MvpDelegate;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vrungel on 28.02.2017.
 */

public class MyLastBookingAdapter
    extends MvpBaseRecyclerAdapter<MyLastBookingAdapter.MyLastBookingViewHolder>
    implements IMyLastBookingAdapterView {

  @InjectPresenter MyLastBookingAdapterPresenter mMyLastBookingAdapterPresenter;

  private ArrayList<LastBookingEntity> mLastBookingEntities = new ArrayList<>();

  public MyLastBookingAdapter(MvpDelegate<?> parentDelegate) {
    super(parentDelegate, "MyLastBookingAdapterPresenter");
  }

  public void addListLastBookingEntity(List<LastBookingEntity> lastBookingEntities) {
    mLastBookingEntities.clear();
    mLastBookingEntities.addAll(lastBookingEntities);
    notifyDataSetChanged();
  }

  @Override public MyLastBookingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new MyLastBookingAdapter.MyLastBookingViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_my_last_booking, parent, false));
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
    holder.mTextViewServiceTime.setText(Converters.dateFromSeconds(
        String.valueOf(mLastBookingEntities.get(position).getServiceTime())));

    holder.mTextViewRemainTime.setText(Converters.timeFromMilliseconds(
        mLastBookingEntities.get(position).getServiceTime() * 1000L - System.currentTimeMillis()));

    // TODO: 28.02.2017 create postpone feature
    holder.mButtonPostpone.setOnClickListener(v -> {
      // TODO: 28.02.2017 send to server postpone query
      mMyLastBookingAdapterPresenter.postponeOrder(position);
    });
    holder.mButtonCancel.setOnClickListener(v -> {
      // TODO: 28.02.2017 send to server cancel query
      mMyLastBookingAdapterPresenter.cancelOrder(position,
          mLastBookingEntities.get(position).getId());
    });
  }

  @Override public int getItemCount() {
    return mLastBookingEntities.size();
  }

  @Override public void removeBookedServiceFromList(int position) {
    mLastBookingEntities.remove(position);
    notifyDataSetChanged();
  }

  static class MyLastBookingViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.view) TextView view;
    @BindView(R.id.clLastBooking) ConstraintLayout mConstraintLayoutLastBooking;
    @BindView(R.id.ivServicePhoto) ImageView mImageViewServicePhoto;
    @BindView(R.id.tvServiceName) TextView mTextViewServiceName;
    @BindView(R.id.tvServiceTime) TextView mTextViewServiceTime;
    @BindView(R.id.tvRemainTime) TextView mTextViewRemainTime;
    @BindView(R.id.bPostpone) Button mButtonPostpone;
    @BindView(R.id.bCancel) Button mButtonCancel;

    MyLastBookingViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
