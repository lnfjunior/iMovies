package br.com.inaconsultoria.imovies.ui.component;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import br.com.inaconsultoria.imovies.R;

public class ProgressDialog {

	public static Dialog getLoadingDialog(Context context) {

		Dialog progressDialog = new Dialog(context, R.style.ProgressBarDialog);
		if (progressDialog.getWindow() != null) {
			progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		}
		progressDialog.setContentView(R.layout.dialog_progress_layout);
		progressDialog.setCancelable(false);
		progressDialog.setCanceledOnTouchOutside(false);
		return progressDialog;
	}
}
