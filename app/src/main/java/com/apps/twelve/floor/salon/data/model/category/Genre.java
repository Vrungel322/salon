package com.apps.twelve.floor.salon.data.model.category;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import java.util.List;

/**
 * Created by vrungel on 30.05.17.
 */

public class Genre extends ExpandableGroup<GoodsSubCategoryEntity> {
  private Integer id;
  private String title;
  private String text;

  public Genre(String title, List<GoodsSubCategoryEntity> items) {
    super(title, items);
  }
}
