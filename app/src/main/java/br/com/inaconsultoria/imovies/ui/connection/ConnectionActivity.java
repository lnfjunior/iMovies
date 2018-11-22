package br.com.inaconsultoria.imovies.ui.connection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import br.com.inaconsultoria.imovies.App;
import br.com.inaconsultoria.imovies.R;
import br.com.inaconsultoria.imovies.ui.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
public class ConnectionActivity extends BaseActivity {

    @BindView(R.id.refresh_button)
    Button mRefreshButton;

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_connection;
    }

    @Override
    protected void onActivityCreate(@Nullable Bundle savedInstanceState) {
        mRefreshButton.setOnClickListener(view -> onRefreshNetwork());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (App.getInstance().isOnline()) {
            this.finishActivity(getActivityLayout());
        } else {
            String mMessageNoConnectionSnackbar = getResources().getString(R.string.app_no_connection_snackbar);
            showSnackbar(mMessageNoConnectionSnackbar);
        }
    }

    @OnClick(R.id.refresh_button)
    public void onRefreshNetwork() {
        if (App.getInstance().isOnline()) {
            finishActivity(getActivityLayout());
        } else {
            String mMessageNoConnectionSnackbar = getResources().getString(R.string.app_no_connection_snackbar);
            showSnackbar(mMessageNoConnectionSnackbar);
        }
    }

}