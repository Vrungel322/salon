package com.apps.twelve.floor.salon.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.apps.twelve.floor.salon.R;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by John on 07.03.2017.
 */

public class ImageWorkViewPagerAdapter extends PagerAdapter {

  private Activity mActivity;
  private LayoutInflater mLayoutInflater;
  private ArrayList<String> mImages;
  private PhotoViewAttacher mPhotoViewAttacher;

  public ImageWorkViewPagerAdapter(Activity activity, ArrayList<String> images) {
    this.mActivity = activity;
    mLayoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.mImages = images;
  }

  @Override public int getCount() {
    return mImages.size();
  }

  @Override public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  @Override public Object instantiateItem(ViewGroup container, int position) {
    View itemView = mLayoutInflater.inflate(R.layout.item_page, container, false);

    final ImageView imageView = (ImageView) itemView.findViewById(R.id.iv);
    Glide.with(mActivity).load(mImages.get(position))
        /*.listener(new RequestListener<String, GlideDrawable>() {
          @Override
          public boolean onException(Exception e, String model, Target<GlideDrawable> target,
              boolean isFirstResource) {
            return false;
          }

          @Override public boolean onResourceReady(GlideDrawable resource, String model,
              Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            mPhotoViewAttacher = new PhotoViewAttacher(imageView);

            return false;
          }
        })*/.into(imageView);

    container.addView(itemView);

    return itemView;
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((RelativeLayout) object);
  }
}
