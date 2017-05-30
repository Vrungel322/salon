package com.apps.twelve.floor.salon.feature.catalog.adapters;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.data.model.category.GoodsSubCategoryEntity;
import com.thoughtbot.expandablecheckrecyclerview.CheckableChildRecyclerViewAdapter;
import com.thoughtbot.expandablecheckrecyclerview.models.CheckedExpandableGroup;
import com.thoughtbot.expandablecheckrecyclerview.models.SingleCheckExpandableGroup;
import com.thoughtbot.expandablecheckrecyclerview.viewholders.CheckableChildViewHolder;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;
import java.util.List;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
 * Created by vrungel on 30.05.17.
 */

public class CategoryAdapter extends
    CheckableChildRecyclerViewAdapter<CategoryAdapter.CategoryViewHolder, CategoryAdapter.SubCategoryViewHolder> {
  //List<? extends ExpandableGroup> mCategories = new ArrayList<>();

  public CategoryAdapter(List<? extends SingleCheckExpandableGroup> groups) {
    super(groups);
    //mCategories = groups;
  }

  @Override public CategoryViewHolder onCreateGroupViewHolder(ViewGroup viewGroup, int i) {
    return new CategoryAdapter.CategoryViewHolder(LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.item_category, viewGroup, false));
  }

  @Override public SubCategoryViewHolder onCreateCheckChildViewHolder(ViewGroup viewGroup, int i) {
    return new CategoryAdapter.SubCategoryViewHolder(LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.item_sub_category, viewGroup, false));
  }

  @Override public void onBindGroupViewHolder(CategoryViewHolder categoryViewHolder, int i,
      ExpandableGroup expandableGroup) {
    categoryViewHolder.mTextViewCatogoryName.setText(expandableGroup.getTitle());
  }

  @Override public void onBindCheckChildViewHolder(SubCategoryViewHolder subCategoryViewHolder, int i,
      CheckedExpandableGroup expandableGroup, int childIndex) {
    final GoodsSubCategoryEntity subCategoryEntity =
        (GoodsSubCategoryEntity) expandableGroup.getItems().get(childIndex);
    subCategoryViewHolder.mTextViewCSubatogoryName.setText(subCategoryEntity.getTitle());
  }

  public static class CategoryViewHolder extends GroupViewHolder {
    @BindView(R.id.tvCatogoryName) TextView mTextViewCatogoryName;
    @BindView(R.id.list_item_arrow) ImageView mImageViewArrow;

    public CategoryViewHolder(View itemView) {
      super(itemView);

      ButterKnife.bind(this, itemView);
    }

    @Override public void expand() {
      mTextViewCatogoryName.setTextColor(
          ContextCompat.getColor(mTextViewCatogoryName.getContext(), R.color.black));
      animateExpand();
    }

    @Override public void collapse() {
      mTextViewCatogoryName.setTextColor(
          ContextCompat.getColor(mTextViewCatogoryName.getContext(), R.color.colorLightGray));
      animateCollapse();
    }

    private void animateExpand() {
      RotateAnimation rotate =
          new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
      rotate.setDuration(300);
      rotate.setFillAfter(true);
      mImageViewArrow.setAnimation(rotate);
    }

    private void animateCollapse() {
      RotateAnimation rotate =
          new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
      rotate.setDuration(300);
      rotate.setFillAfter(true);
      mImageViewArrow.setAnimation(rotate);
    }
  }

  public static class SubCategoryViewHolder extends CheckableChildViewHolder {
    @BindView(R.id.tvSubCatogoryName) CheckedTextView mTextViewCSubatogoryName;

    public SubCategoryViewHolder(View itemView) {
      super(itemView);

      ButterKnife.bind(this, itemView);
    }

    @Override public Checkable getCheckable() {
      return mTextViewCSubatogoryName;
    }
  }
}
