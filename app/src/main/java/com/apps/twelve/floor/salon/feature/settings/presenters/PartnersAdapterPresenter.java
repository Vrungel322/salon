package com.apps.twelve.floor.salon.feature.settings.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.settings.views.IPartnersAdapter;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by alexandersvyatetsky on 16/08/17.
 */

@InjectViewState public class PartnersAdapterPresenter extends BasePresenter<IPartnersAdapter> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
