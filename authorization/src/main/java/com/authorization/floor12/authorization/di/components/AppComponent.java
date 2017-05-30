package com.authorization.floor12.authorization.di.components;

import com.authorization.floor12.authorization.AuthorizationManager;
import com.authorization.floor12.authorization.base.BaseActivity;
import com.authorization.floor12.authorization.base.BaseFragment;
import com.authorization.floor12.authorization.di.modules.AppModule;
import com.authorization.floor12.authorization.di.scopes.AppScope;
import com.authorization.floor12.authorization.logic.authorization.activities.ModuleSignInActivity;
import com.authorization.floor12.authorization.logic.authorization.presenters.SignInActivityPresenter;
import com.authorization.floor12.authorization.logic.recoverypassword.activities.ModuleRecoveryPasswordActivity;
import com.authorization.floor12.authorization.logic.recoverypassword.presenters.RecoveryPasswordPresenter;
import com.authorization.floor12.authorization.logic.registration.activities.ModuleRegistrationActivity;
import com.authorization.floor12.authorization.logic.registration.presenters.RegistrationActivityPresenter;
import com.authorization.floor12.authorization.logic.userdetail.activities.UserProfileActivity;
import com.authorization.floor12.authorization.logic.userdetail.presenters.ChangePasswordPresenter;
import com.authorization.floor12.authorization.logic.userdetail.presenters.ChangeUserInfoFragmentPresenter;
import com.authorization.floor12.authorization.logic.userdetail.presenters.DeleteAccountFragmentPresenter;
import com.authorization.floor12.authorization.logic.userdetail.presenters.UserProfileActivityPresenter;
import com.authorization.floor12.authorization.logic.userdetail.presenters.UserProfileFragmentPresenter;
import dagger.Component;

/**
 * Created by Vrungel on 25.01.2017.
 */
@AppScope @Component(modules = AppModule.class) public interface AppComponent {

  //presenters
  void inject(SignInActivityPresenter presenter);

  void inject(RegistrationActivityPresenter presenter);

  void inject(UserProfileActivityPresenter presenter);

  void inject(RecoveryPasswordPresenter presenter);

  void inject(UserProfileFragmentPresenter presenter);

  void inject(BaseFragment presenter);

  void inject(ChangeUserInfoFragmentPresenter presenter);

  void inject(ChangePasswordPresenter presenter);

  void inject(DeleteAccountFragmentPresenter presenter);

  // activities
  void inject(BaseActivity activity);

  void inject(ModuleSignInActivity activity);

  void inject(ModuleRegistrationActivity activity);

  void inject(UserProfileActivity activity);

  void inject(ModuleRecoveryPasswordActivity activity);

  void inject(AuthorizationManager manager);
}
