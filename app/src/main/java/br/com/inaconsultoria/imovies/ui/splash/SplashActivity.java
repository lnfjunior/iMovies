
package br.com.inaconsultoria.imovies.ui.splash;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.race604.drawable.wave.WaveDrawable;

import br.com.inaconsultoria.imovies.R;
import br.com.inaconsultoria.imovies.ui.base.BaseActivity;
import br.com.inaconsultoria.imovies.ui.main.MainActivity;
import butterknife.BindView;


/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
public class SplashActivity extends BaseActivity<SplashContractView>
        implements SplashContractView {

    @BindView(R.id.iv_logo_splash)
    ImageView mLogoSplash;

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setUp();
    }

    @Override
    public void openMainActivity() {
        startActivity(MainActivity.getStartIntent(SplashActivity.this));
    }

    @Override
    public void setUpLogo() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            WaveDrawable mWaveDrawable  = new WaveDrawable(getDrawable(R.drawable.imovies_app));
            mWaveDrawable.setIndeterminate(true);
            mLogoSplash.setImageDrawable(mWaveDrawable);
        }else {
            mLogoSplash.setImageResource(R.drawable.imovies_app);
        }
        Handler handler = new Handler();
        handler.postDelayed(this::openMainActivity, 4000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void setUp() {
        setUpLogo();
        setUpPresenter();

    }

    private void setUpPresenter() {
        SplashPresenter mPresenter = SplashPresenter.getInstance(this);
        setActivityPresenter(mPresenter);
    }


}
