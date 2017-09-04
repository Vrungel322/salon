package com.apps.twelve.floor.salon.feature.booking.mode.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.data.model.DataServiceEntity;
import com.apps.twelve.floor.salon.utils.Converters;
import java.util.List;

/**
 * Created by Vrungel on 27.03.2017.
 */

public class DatesInMonthViewPagerAdapter extends PagerAdapter {
  private final Context mContext;
  private LayoutInflater mLayoutInflater;
  private List<DataServiceEntity> daysInMonth;

  public DatesInMonthViewPagerAdapter(Context context, List<DataServiceEntity> daysInMonth) {
    mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.daysInMonth = daysInMonth;
    this.mContext = context;
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
    textView.setText(Converters.detailDayFromSeconds(mContext,
        daysInMonth.get(position).getStartTime().toString()));
    container.addView(itemView);
    return itemView;
  }

  public String getEntity(int position) {
    return Converters.detailDayFromSeconds(mContext,
        daysInMonth.get(position).getStartTime().toString());
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((RelativeLayout) object);
  }
}
