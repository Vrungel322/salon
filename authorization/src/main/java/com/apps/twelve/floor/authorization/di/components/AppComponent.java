package com.apps.twelve.floor.authorization.di.components;

import com.apps.twelve.floor.authorization.AuthorizationManager;
import com.apps.twelve.floor.authorization.base.BaseActivity;
import com.apps.twelve.floor.authorization.base.BaseFragment;
import com.apps.twelve.floor.authorization.di.modules.AppModule;
import com.apps.twelve.floor.authorization.di.scopes.AppScope;
import com.apps.twelve.floor.authorization.logic.authorization.activities.ModuleSignInActivity;
import com.apps.twelve.floor.authorization.logic.authorization.presenters.ModuleSignInPresenter;
import com.apps.twelve.floor.authorization.logic.recoverypassword.activities.RecoveryPasswordActivity;
import com.apps.twelve.floor.authorization.logic.recoverypassword.presenters.ModuleRecoveryPasswordPresenter;
import com.apps.twelve.floor.authorization.logic.recoverypassword.presenters.RecoveryChangePasswordPresenter;
import com.apps.twelve.floor.authorization.logic.recoverypassword.presenters.RecoveryPasswordActivityPresenter;
import com.apps.twelve.floor.authorization.logic.registration.activities.ModuleRegistrationActivity;
import com.apps.twelve.floor.authorization.logic.registration.presenters.ModuleRegistrationPresenter;
import com.apps.twelve.floor.authorization.logic.userdetail.presenters.ActivityHistoryPresenter;
import com.apps.twelve.floor.authorization.logic.userdetail.presenters.ChangePasswordPresenter;
import com.apps.twelve.floor.authorization.logic.userdetail.presenters.ChangeUserInfoFragmentPresenter;
import com.apps.twelve.floor.authorization.logic.userdetail.presenters.DeleteAccountFragmentPresenter;
import com.apps.twelve.floor.authorization.logic.userdetail.presenters.UserProfileActivityPresenter;
import com.apps.twelve.floor.authorization.logic.userdetail.presenters.UserProfileFragmentPresenter;
import dagger.Component;

/**
 * Created by Vrungel on 25.01.2017.
 */
@AppScope @Component(modules = AppModule.class) public interface AppComponent {

  //presenters
  void inject(ModuleSignInPresenter presenter);

  void inject(ModuleRegistrationPresenter presenter);

  void inject(ModuleRecoveryPasswordPresenter presenter);

  void inject(UserProfileFragmentPresenter presenter);

  void inject(BaseFragment presenter);

  void inject(ChangeUserInfoFragmentPresenter presenter);

  void inject(ChangePasswordPresenter presenter);

  void inject(DeleteAccountFragmentPresenter presenter);

  void inject(ActivityHistoryPresenter presenter);

  void inject(RecoveryPasswordActivityPresenter presenter);

  void inject(RecoveryChangePasswordPresenter presenter);

  void inject(UserProfileActivityPresenter presenter);

  // activities
  void inject(BaseActivity activity);

  void inject(ModuleSignInActivity activity);

  void inject(ModuleRegistrationActivity activity);

  void inject(RecoveryPasswordActivity activity);

  void inject(AuthorizationManager manager);
}
