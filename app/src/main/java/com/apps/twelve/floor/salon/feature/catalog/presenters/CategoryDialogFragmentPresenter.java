package com.apps.twelve.floor.salon.feature.catalog.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.catalog.views.ICategoryDialogFragmentView;

/**
 * Created by Vrungel on 29.05.2017.
 */

public class CategoryDialogFragmentPresenter extends BasePresenter<ICategoryDialogFragmentView> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
