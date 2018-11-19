package br.com.inaconsultoria.imovies.ui.base;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import br.com.inaconsultoria.imovies.R;
import br.com.inaconsultoria.imovies.ui.component.ProgressDialog;
import br.com.inaconsultoria.imovies.ui.connection.ConnectionActivity;
import butterknife.ButterKnife;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
@SuppressWarnings({"deprecation", "unchecked"})
public abstract class BaseActivity<T extends BaseView> extends AppCompatActivity {

    private RelativeLayout notConnectedLayout;

    private View snackbarLayout;
	private Dialog loadingDialog;
	private BasePresenter<T> basePresenter;

	protected abstract int getActivityLayout();

	protected abstract void onActivityCreate(@Nullable Bundle savedInstanceState);

    @Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base_layout);

		configureLayout();
		configureNotConnectedLayout();
		configureSnackbarLayout();
		createLoadingDialog();
		onActivityCreate(savedInstanceState);
	}

	protected void setActivityPresenter(BasePresenter<T> presenter) {
		this.basePresenter = presenter;
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (this.basePresenter != null) {
			this.basePresenter.attachView((T) this);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (this.basePresenter != null) {
			this.basePresenter.detachView();
		}
	}

	private void configureLayout() {
		RelativeLayout contentContainer = findViewById(R.id.content_container);

		LayoutInflater inflater = LayoutInflater.from(this);
		View inflatedLayout = inflater.inflate(getActivityLayout(), contentContainer, true);

		ButterKnife.bind(this, inflatedLayout);
	}

    private void configureNotConnectedLayout() {
        notConnectedLayout = findViewById(R.id.not_connected_layout);
    }

    private void configureSnackbarLayout() {
		this.snackbarLayout = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
	}

	private void createLoadingDialog() {
		this.loadingDialog = ProgressDialog.getLoadingDialog(this);
	}

	public void showSnackbar(String message) {
		final Snackbar snackbar = Snackbar.make(this.snackbarLayout, message, Snackbar.LENGTH_LONG);
		View snackView = snackbar.getView();
		snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorAccent));
		TextView tv = snackView.findViewById(android.support.design.R.id.snackbar_text);
		tv.setTextColor(getResources().getColor(R.color.colorPrimary));
		snackbar.show();
	}

	public void showToastMessage(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	public void showLoading() {
		this.loadingDialog.show();
	}

	public void hideLoading() {
		this.loadingDialog.dismiss();
	}

	public void showNotConnectedLayout() {
	    startActivity(new Intent(this, ConnectionActivity.class));
	}

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void hideNotConnectedLayout() {
        notConnectedLayout.setVisibility(View.GONE);
    }

    public Context getCurrentContext() {
        return this;
    }
}