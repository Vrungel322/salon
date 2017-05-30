package com.apps.twelve.floor.salon.data.model.category;

import com.thoughtbot.expandablecheckrecyclerview.models.SingleCheckExpandableGroup;
import java.util.List;

/**
 * Created by vrungel on 30.05.17.
 */

public class Genre extends SingleCheckExpandableGroup{
  private Integer id;
  private String title;
  private String text;

  public Genre(String title, List<GoodsSubCategoryEntity> items) {
    super(title, items);
  }
}
