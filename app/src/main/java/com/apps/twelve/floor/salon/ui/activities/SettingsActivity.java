package com.apps.twelve.floor.salon.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import butterknife.BindView;
import butterknife.OnClick;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.ui.base.BaseActivity;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import de.hdodenhof.circleimageview.CircleImageView;
import timber.log.Timber;

public class SettingsActivity extends BaseActivity {

  @BindView(R.id.ivProfile) CircleImageView mProfileImage;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_settings);
    super.onCreate(savedInstanceState);
  }

  @OnClick(R.id.btnNewImage) void getNewImage() {
    CropImage.activity(null)
        .setFixAspectRatio(true)
        .setCropShape(CropImageView.CropShape.OVAL)
        .setShowCropOverlay(true)
        //.setMultiTouchEnabled(true)
        //.setAspectRatio(1, 1)
        .start(this);
  }

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
      CropImage.ActivityResult result = CropImage.getActivityResult(data);
      if (resultCode == RESULT_OK) {
        Uri cropImageUri = result.getUri();
        Picasso.with(this).load(cropImageUri).into(mProfileImage);
      } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
        Timber.e(result.getError());
      }
    }
  }

  @Override public void onBackPressed() {
    super.onBackPressed();
    finish();
  }
}
