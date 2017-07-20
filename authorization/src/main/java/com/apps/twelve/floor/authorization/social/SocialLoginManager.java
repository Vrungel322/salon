package com.apps.twelve.floor.authorization.social;
/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import com.facebook.FacebookSdk;
import rx.Observable;
import rx.subjects.PublishSubject;

import static com.apps.twelve.floor.authorization.social.SocialLoginManager.SocialPlatform.FACEBOOK;
import static com.apps.twelve.floor.authorization.social.SocialLoginManager.SocialPlatform.GOOGLE;

public class SocialLoginManager {

  private static final String ERROR = "You must choose a social platform.";

  @SuppressLint("StaticFieldLeak") private static SocialLoginManager instance;
  private PublishSubject<SocialUser> userEmitter;
  private Context appContext;
  private boolean withProfile = true;
  private SocialPlatform socialPlatform;
  private String clientId;

  private SocialLoginManager(Context context) {
    appContext = context.getApplicationContext();
  }

  public static synchronized SocialLoginManager getInstance(Context context) {
    if (instance == null) {
      instance = new SocialLoginManager(context);
    }
    return instance;
  }

  public static void init(Application application) {
    FacebookSdk.sdkInitialize(application.getApplicationContext());
  }

  @Deprecated public SocialLoginManager withProfile() {
    this.withProfile = true;
    return this;
  }

  public SocialLoginManager withProfile(boolean withProfile) {
    this.withProfile = withProfile;
    return this;
  }

  public SocialLoginManager facebook() {
    this.socialPlatform = FACEBOOK;
    return this;
  }

  public SocialLoginManager google(String clientId) {
    this.clientId = clientId;
    this.socialPlatform = GOOGLE;
    return this;
  }

  public Observable<SocialUser> login() {
    userEmitter = PublishSubject.create();
    appContext.startActivity(getIntent());
    return userEmitter;
  }

  public Intent getIntent() {
    if (socialPlatform == FACEBOOK) {
      Intent intent = new Intent(appContext, FbLoginHiddenActivity.class);
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      return intent;
    } else if (socialPlatform == GOOGLE) {
      Intent intent = new Intent(appContext, GoogleLoginHiddenActivity.class);
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      intent.putExtra(GoogleLoginHiddenActivity.EXTRA_CLIENT_ID, clientId);
      return intent;
    } else {
      throw new IllegalStateException(ERROR);
    }
  }

  boolean isWithProfile() {
    return this.withProfile;
  }

  void onLoginSuccess(SocialUser socialUser) {
    if (userEmitter != null) {
      SocialUser copy = new SocialUser(socialUser);
      userEmitter.onNext(copy);
      userEmitter.onCompleted();
    }
  }

  void onLoginError(Throwable throwable) {
    if (userEmitter != null) {
      Throwable copy = new Throwable(throwable);
      userEmitter.onError(copy);
    }
  }

  void onLoginCancel() {
    if (userEmitter != null) {
      userEmitter.onCompleted();
    }
  }

  enum SocialPlatform {
    FACEBOOK, GOOGLE
  }
}
