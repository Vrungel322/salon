package com.apps.twelve.floor.salon.mvp.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.IFragmentMyBookPresenter;
import com.apps.twelve.floor.salon.mvp.views.IFragmentMyBookView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 21.02.2017.
 */

@InjectViewState public class FragmentMyBookPresenter extends BasePresenter<IFragmentMyBookView>
    implements IFragmentMyBookPresenter {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
