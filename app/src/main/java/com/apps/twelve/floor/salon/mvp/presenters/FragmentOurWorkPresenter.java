package com.apps.twelve.floor.salon.mvp.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.IFragmentOurWorkPresenter;
import com.apps.twelve.floor.salon.mvp.views.IFragmentOurWorkView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 21.02.2017.
 */

@InjectViewState public class FragmentOurWorkPresenter extends BasePresenter<IFragmentOurWorkView>
    implements IFragmentOurWorkPresenter {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
