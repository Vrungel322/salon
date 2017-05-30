package com.apps.twelve.floor.salon.feature.catalog.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.data.model.category.GoodsSubCategoryEntity;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;
import java.util.List;
import timber.log.Timber;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
 * Created by vrungel on 30.05.17.
 */

public class CategoryAdapter extends
    ExpandableRecyclerViewAdapter<CategoryAdapter.CategoryViewHolder, CategoryAdapter.SubCategoryViewHolder> {
  //List<? extends ExpandableGroup> mCategories = new ArrayList<>();

  public CategoryAdapter(List<? extends ExpandableGroup>  groups) {
    super(groups);
    //mCategories = groups;
  }

  @Override public CategoryViewHolder onCreateGroupViewHolder(ViewGroup viewGroup, int i) {
    return new CategoryAdapter.CategoryViewHolder(LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.item_category, viewGroup, false));
  }

  @Override public SubCategoryViewHolder onCreateChildViewHolder(ViewGroup viewGroup, int i) {
    return new CategoryAdapter.SubCategoryViewHolder(LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.item_sub_category, viewGroup, false));
  }

  @Override public void onBindGroupViewHolder(CategoryViewHolder categoryViewHolder, int i,
      ExpandableGroup expandableGroup) {
    categoryViewHolder.mTextViewCatogoryName.setText(expandableGroup.getTitle());
  }

  @Override public void onBindChildViewHolder(SubCategoryViewHolder subCategoryViewHolder, int i,
      ExpandableGroup expandableGroup, int childIndex) {
    final GoodsSubCategoryEntity subCategoryEntity =
        (GoodsSubCategoryEntity) expandableGroup.getItems().get(childIndex);
    Timber.e("" + subCategoryEntity.getTitle());
    subCategoryViewHolder.mTextViewCSubatogoryName.setText(subCategoryEntity.getTitle());
  }

  public static class CategoryViewHolder extends GroupViewHolder {
    @BindView(R.id.tvCatogoryName) TextView mTextViewCatogoryName;

    public CategoryViewHolder(View itemView) {
      super(itemView);

      ButterKnife.bind(this, itemView);
    }

    @Override public void expand() {
      animateExpand();
    }

    @Override public void collapse() {
      animateCollapse();
    }

    private void animateExpand() {
      RotateAnimation rotate =
          new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
      rotate.setDuration(300);
      rotate.setFillAfter(true);
      mTextViewCatogoryName.setAnimation(rotate);
    }

    private void animateCollapse() {
      RotateAnimation rotate =
          new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
      rotate.setDuration(300);
      rotate.setFillAfter(true);
      mTextViewCatogoryName.setAnimation(rotate);
    }
  }

  public static class SubCategoryViewHolder extends ChildViewHolder {
    @BindView(R.id.tvSubCatogoryName) TextView mTextViewCSubatogoryName;

    public SubCategoryViewHolder(View itemView) {
      super(itemView);

      ButterKnife.bind(this, itemView);
    }
  }
}
