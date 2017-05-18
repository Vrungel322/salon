package com.apps.twelve.floor.salon.feature.catalog.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.data.model.StaffDetailContent;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

/**
 * Created by Vrungel on 18.05.2017.
 */

public class ImageStaffViewPagerAdapter extends PagerAdapter {
  private Activity mActivity;
  private LayoutInflater mLayoutInflater;
  private ArrayList<StaffDetailContent> mPhotoStaff;

  public ImageStaffViewPagerAdapter(Activity activity, ArrayList<StaffDetailContent> photoStaff) {
    this.mActivity = activity;
    mLayoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.mPhotoStaff = photoStaff;
  }

  @Override public int getCount() {
    return mPhotoStaff.size();
  }

  @Override public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  @Override public Object instantiateItem(ViewGroup container, int position) {
    View itemView = mLayoutInflater.inflate(R.layout.item_page, container, false);
    final ImageView imageView = (ImageView) itemView.findViewById(R.id.iv);
    Glide.with(mActivity).load(mPhotoStaff.get(position).getUrlPhoto()).into(imageView);
    container.addView(itemView);
    return itemView;
  }

  public StaffDetailContent getEntity(int position) {
    return mPhotoStaff.get(position);
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((RelativeLayout) object);
  }
}
