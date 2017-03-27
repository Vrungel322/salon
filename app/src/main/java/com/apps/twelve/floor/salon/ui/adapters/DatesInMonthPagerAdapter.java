package com.apps.twelve.floor.salon.ui.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.apps.twelve.floor.salon.R;
import java.util.ArrayList;

/**
 * Created by Vrungel on 27.03.2017.
 */

public class DatesInMonthPagerAdapter extends PagerAdapter {
  private LayoutInflater mLayoutInflater;
  private ArrayList<String> daysInMonth;

  public DatesInMonthPagerAdapter(Context context, ArrayList<String> daysInMonth) {
    mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.daysInMonth = daysInMonth;
  }

  @Override public int getCount() {
    return daysInMonth.size();
  }

  @Override public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  @Override public Object instantiateItem(ViewGroup container, int position) {
    View itemView = mLayoutInflater.inflate(R.layout.item_day_in_month, container, false);
    final TextView textView = (TextView) itemView.findViewById(R.id.tvDayInMonth);
    textView.setText(daysInMonth.get(position));
    container.addView(itemView);
    return itemView;
  }

  public String getEntity(int position) {
    return daysInMonth.get(position);
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((RelativeLayout) object);
  }
}
