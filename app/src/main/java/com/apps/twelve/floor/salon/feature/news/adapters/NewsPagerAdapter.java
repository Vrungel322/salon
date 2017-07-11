package com.apps.twelve.floor.salon.feature.news.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.apps.twelve.floor.salon.data.model.NewsEntity;
import com.apps.twelve.floor.salon.feature.news.fragments.NewsDetailFragment;
import java.util.ArrayList;

/**
 * Created by John on 11.07.2017.
 */

public class NewsPagerAdapter extends FragmentStatePagerAdapter {

  private ArrayList<NewsEntity> mNewsEntities = new ArrayList<>();

  public NewsPagerAdapter(FragmentManager fm, ArrayList<NewsEntity> listNews) {
    super(fm);
    mNewsEntities = listNews;
  }

  @Override public Fragment getItem(int position) {
    return (NewsDetailFragment.newInstance(mNewsEntities.get(position)));
  }

  @Override public int getCount() {
    return mNewsEntities.size();
  }
}
