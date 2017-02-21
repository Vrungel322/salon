package com.apps.twelve.floor.salon.mvp.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.IFragmentMyBonusPresenter;
import com.apps.twelve.floor.salon.mvp.views.IFragmentMyBonusView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 21.02.2017.
 */

@InjectViewState public class FragmentMyBonusPresenter extends BasePresenter<IFragmentMyBonusView>
    implements IFragmentMyBonusPresenter {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
