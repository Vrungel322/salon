package com.apps.twelve.floor.salon.feature.settings.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.local.LocaleHelper;
import com.apps.twelve.floor.salon.feature.settings.views.ISettingsFragmentView;
import com.arellomobile.mvp.InjectViewState;

import static com.apps.twelve.floor.salon.utils.Constants.Language.RU;
import static com.apps.twelve.floor.salon.utils.Constants.Language.RUSSIAN;
import static com.apps.twelve.floor.salon.utils.Constants.Language.UK;
import static com.apps.twelve.floor.salon.utils.Constants.Language.UKRAINIAN;

/**
 * Created by Alexandra on 18.04.2017.
 */

@InjectViewState public class SettingsFragmentPresenter
    extends BasePresenter<ISettingsFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    if (mAuthorizationManager.isAuthorized()) {
      getViewState().openUserProfileFragment();
    }
  }

  public int getLanguagePosition() {
    String language = mDataManager.getSelectedLanguage();
    switch (language) {
      case RUSSIAN:
        return 0;
      case UKRAINIAN:
        return 1;
      default:
        return 0;
    }
  }

  public void saveLanguage(String mSelectedLanguage) {
    String languageCode;
    switch (mSelectedLanguage) {
      case RUSSIAN:
        languageCode = RU;
        break;
      case UKRAINIAN:
        languageCode = UK;
        break;
      default:
        languageCode = UK;
        break;
    }
    LocaleHelper.setLocale(mContext, languageCode);
    mDataManager.setSelectedLanguage(mSelectedLanguage);
  }

  public void closeChangeLanguageDialog() {
    getViewState().closeChangeLanguageDialog();
  }
}
