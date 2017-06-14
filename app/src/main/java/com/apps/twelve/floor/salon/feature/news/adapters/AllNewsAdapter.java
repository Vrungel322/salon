package com.apps.twelve.floor.salon.feature.news.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.data.model.NewsEntity;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

import static com.apps.twelve.floor.salon.utils.Converters.dateFromSeconds;

/**
 * Created by Vrungel on 24.02.2017.
 */

public class AllNewsAdapter extends RecyclerView.Adapter<AllNewsAdapter.AllNewsViewHolder> {

  public static final int LAST_NEWS = 1;
  public static final int DEFAULT_NEWS = 0;
  private ArrayList<NewsEntity> mNewsEntities = new ArrayList<>();

  public void addListNewsEntity(List<NewsEntity> newsEntities) {
    mNewsEntities.addAll(newsEntities);
    notifyDataSetChanged();
  }

  @Override public AllNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v;
    switch (viewType) {
      case DEFAULT_NEWS: {
        v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_news_in_all_news, parent, false);
        return new AllNewsViewHolder(v);
      }
      case LAST_NEWS: {
        v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_last_news_in_all_news, parent, false);
        return new AllNewsViewHolder(v);
      }
    }
    return null;
  }

  @Override public void onBindViewHolder(AllNewsViewHolder holder, int position) {
    Glide.with(holder.mImageViewThumbNews.getContext())
        .load(mNewsEntities.get(position).getImg())
        .placeholder(R.drawable.ic_news_placeholder_130dp).dontAnimate()
        .into(holder.mImageViewThumbNews);

    holder.mTextViewItemNewsShortDescription.setText(mNewsEntities.get(position).getTitle());

    holder.mTextViewItemNewsData.setText(
        dateFromSeconds(mNewsEntities.get(position).getCreatedAt()));
  }

  @Override public int getItemCount() {
    return mNewsEntities.size();
  }

  @Override public int getItemViewType(int position) {
    return position == 0 ? LAST_NEWS : DEFAULT_NEWS;
  }

  public NewsEntity getItem(int position) {
    return mNewsEntities.get(position);
  }

  static class AllNewsViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ivThumbNews) ImageView mImageViewThumbNews;
    @BindView(R.id.tvItemNewsShortDescription) TextView mTextViewItemNewsShortDescription;
    @BindView(R.id.tvItemNewsData) TextView mTextViewItemNewsData;

    AllNewsViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
