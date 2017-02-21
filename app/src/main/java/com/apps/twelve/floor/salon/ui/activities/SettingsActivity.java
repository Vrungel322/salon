package com.apps.twelve.floor.salon.ui.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.ui.base.BaseActivity;

public class SettingsActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_settings);
    super.onCreate(savedInstanceState);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_settings:
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override public void onBackPressed() {
    super.onBackPressed();
    finish();
  }
}
