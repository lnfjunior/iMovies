package br.com.inaconsultoria.imovies.ui.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.inaconsultoria.imovies.BuildConfig;
import br.com.inaconsultoria.imovies.R;
import br.com.inaconsultoria.imovies.ui.base.BaseActivity;
import butterknife.BindString;
import butterknife.BindView;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
public class AboutActivity extends BaseActivity {

	@BindView(R.id.tv_app_version)
	TextView mVersionTextView;

	@BindString(R.string.app_version)
	String mTitleVersion;


	@Override
	protected int getActivityLayout() {
		return R.layout.activity_about;
	}

	@Override
	protected void onActivityCreate(@Nullable Bundle savedInstanceState) {
		setUpToolbar();
        setUpVersion();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

	private void setUpToolbar() {
		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setDisplayShowHomeEnabled(true);
			setTitle(R.string.title_item_about);
		}
	}

    private void setUpVersion() {
        String version = String.format("%s  %s", mTitleVersion, BuildConfig.VERSION_NAME);
        mVersionTextView.setText(version);
    }



}