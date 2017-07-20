package com.apps.twelve.floor.authorization.utils;
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

import android.content.Context;
import android.content.SharedPreferences;
import com.apps.twelve.floor.authorization.R;

import static com.apps.twelve.floor.authorization.data.local.PreferencesHelper.PREF_FILE_NAME;
import static com.apps.twelve.floor.authorization.data.local.PreferencesHelper.PREF_STYLE_RES_ID;

public class ThemeUtils {

  public static int getCurrentTheme(Context context) {
    SharedPreferences sharedPreferences =
        context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    return sharedPreferences.getInt(PREF_STYLE_RES_ID, R.style.ModuleTheme);
  }
}
