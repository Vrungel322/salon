package com.apps.twelve.floor.salon.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import com.apps.twelve.floor.salon.R;

/**
 * Created by John on 26.01.2017.
 */

public final class DialogFactory {

  public static Dialog createSimpleOkErrorDialog(Context context, String title, String message) {
    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context).setTitle(title)
        .setMessage(message)
        .setNeutralButton(R.string.dialog_action_ok, null);
    return alertDialog.create();
  }

  public static Dialog createSimpleOkErrorDialog(Context context, @StringRes int titleResource,
      @StringRes int messageResource) {

    return createSimpleOkErrorDialog(context, context.getString(titleResource),
        context.getString(messageResource));
  }

  public static Dialog createGenericErrorDialog(Context context, String message) {
    AlertDialog.Builder alertDialog =
        new AlertDialog.Builder(context).setTitle(context.getString(R.string.dialog_error_title))
            .setMessage(message);
    return alertDialog.create();
  }

  public static AlertDialog.Builder createAlertDialogBuilder(Context context,
      @StringRes int titleResource, @StringRes int messageResource,
      @DrawableRes int drawableResource) {
    return new AlertDialog.Builder(context).setTitle(titleResource)
        .setMessage(messageResource)
        .setIcon(drawableResource);
  }

  public static Dialog createGenericErrorDialog(Context context, @StringRes int messageResource) {
    return createGenericErrorDialog(context, context.getString(messageResource));
  }

  public static ProgressDialog createProgressDialog(Context context, String message) {
    ProgressDialog progressDialog = new ProgressDialog(context);
    progressDialog.setMessage(message);
    return progressDialog;
  }

  public static ProgressDialog createProgressDialog(Context context,
      @StringRes int messageResource) {
    return createProgressDialog(context, context.getString(messageResource));
  }

  public static AlertDialog.Builder createAlertDialogBuilder(Context context, String title) {
    return new AlertDialog.Builder(context).setTitle(title);
  }

  public static AlertDialog.Builder createAuthorizationDialogBuilder(Context context) {
    return new AlertDialog.Builder(context).setTitle(context.getString(R.string.dialog_auth_title))
        .setMessage(context.getString(R.string.dialog_auth_message));
  }
}
