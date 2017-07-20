package com.apps.twelve.floor.authorization.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import com.apps.twelve.floor.authorization.R;

/**
 * Created by alexander on 15.05.17.
 */

public class DialogFactory {

  public static Dialog createSimpleOkDialog(Context context, String title, String message,
      DialogInterface.OnClickListener listener) {
    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context).setTitle(title)
        .setMessage(message)
        .setNeutralButton(R.string.btn_ok, listener);
    return alertDialog.create();
  }

  public static Dialog createSimpleOkDialog(Context context, @StringRes int titleResource,
      @StringRes int messageResource, DialogInterface.OnClickListener listener) {

    return createSimpleOkDialog(context, context.getString(titleResource),
        context.getString(messageResource), listener);
  }

  public static ProgressDialog createProgressDialog(Context context, String message) {
    ProgressDialog progressDialog = new ProgressDialog(context);
    progressDialog.setMessage(message);
    return progressDialog;
  }

  public static ProgressDialog createProgressDialog(Context context,
      @StringRes int messageResource) {
    return createProgressDialog(context, messageResource);
  }

  public static AlertDialog.Builder createAlertDialogBuilder(Context context, String title,
      String message) {
    return new AlertDialog.Builder(context).setTitle(title).setMessage(message);
  }

  public static AlertDialog.Builder createAlertDialogBuilder(Context context, String title) {
    return createAlertDialogBuilder(context, title, null);
  }
}
