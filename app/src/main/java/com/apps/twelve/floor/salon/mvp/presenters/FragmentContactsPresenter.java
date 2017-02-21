package com.apps.twelve.floor.salon.mvp.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.IFragmentContactsPresenter;
import com.apps.twelve.floor.salon.mvp.views.IFragmentContactsView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 21.02.2017.
 */

@InjectViewState public class FragmentContactsPresenter extends BasePresenter<IFragmentContactsView>
    implements IFragmentContactsPresenter {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
