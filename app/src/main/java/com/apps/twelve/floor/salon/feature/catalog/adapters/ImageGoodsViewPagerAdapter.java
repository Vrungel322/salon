package com.apps.twelve.floor.salon.feature.catalog.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.content.res.AppCompatResources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.data.model.GoodsDetailContent;
import com.bumptech.glide.Glide;
import io.realm.RealmList;
import java.util.ArrayList;

/**
 * Created by Vrungel on 18.05.2017.
 */

public class ImageGoodsViewPagerAdapter extends PagerAdapter {
  private Context mContext;
  private LayoutInflater mLayoutInflater;
  private RealmList<GoodsDetailContent> mPhotoStaff;

  public ImageGoodsViewPagerAdapter(Context context, RealmList<GoodsDetailContent> photoStaff) {
    this.mContext = context;
    mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    Glide.with(mContext)
        .load(mPhotoStaff.get(position).getUrlPhoto())
        .placeholder(AppCompatResources.getDrawable(mContext, R.drawable.ic_catalog_placeholder))
        .error(AppCompatResources.getDrawable(mContext, R.drawable.ic_catalog_placeholder))
        .dontAnimate()
        .into(imageView);
    container.addView(itemView);
    return itemView;
  }

  public GoodsDetailContent getEntity(int position) {
    return mPhotoStaff.get(position);
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((RelativeLayout) object);
  }
}
