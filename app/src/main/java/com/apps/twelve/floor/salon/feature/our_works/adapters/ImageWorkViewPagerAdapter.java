package com.apps.twelve.floor.salon.feature.our_works.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.content.res.AppCompatResources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.data.model.PhotoWorksEntity;
import com.bumptech.glide.Glide;
import java.util.List;

/**
 * Created by John on 07.03.2017.
 */

public class ImageWorkViewPagerAdapter extends PagerAdapter {

  private Activity mActivity;
  private LayoutInflater mLayoutInflater;
  private List<PhotoWorksEntity> mPhotoWorks;

  public ImageWorkViewPagerAdapter(Activity activity, List<PhotoWorksEntity> photoWorks) {
    this.mActivity = activity;
    mLayoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.mPhotoWorks = photoWorks;
  }

  @Override public int getCount() {
    return mPhotoWorks.size();
  }

  @Override public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  @Override public Object instantiateItem(ViewGroup container, int position) {
    View itemView = mLayoutInflater.inflate(R.layout.item_page, container, false);
    final ImageView imageView = (ImageView) itemView.findViewById(R.id.iv);
    Glide.with(mActivity)
        .load(mPhotoWorks.get(position).getUrlPhoto())
        .placeholder(
            AppCompatResources.getDrawable(mActivity, R.drawable.ic_our_work_placeholder_130dp))
        .dontAnimate()
        .into(imageView);
    container.addView(itemView);
    return itemView;
  }

  public PhotoWorksEntity getEntity(int position) {
    return mPhotoWorks.get(position);
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((RelativeLayout) object);
  }
}
