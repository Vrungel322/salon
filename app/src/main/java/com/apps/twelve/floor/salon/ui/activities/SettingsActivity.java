package com.apps.twelve.floor.salon.ui.activities;

import android.os.Bundle;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.ui.base.BaseActivity;
import shortbread.Shortcut;

@Shortcut(id = "settings", icon = R.drawable.ic_action_settings, shortLabel = "Settings")
public class SettingsActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_settings);
    super.onCreate(savedInstanceState);
  }

  @Override public void onBackPressed() {
    super.onBackPressed();
    finish();
  }
}
