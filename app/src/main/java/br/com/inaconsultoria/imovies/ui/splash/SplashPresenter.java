
package br.com.inaconsultoria.imovies.ui.splash;

import android.support.annotation.NonNull;

import br.com.inaconsultoria.imovies.ui.base.BasePresenter;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
public class SplashPresenter extends BasePresenter<SplashContractView>
        implements SplashContractPresenter {


    private SplashPresenter(@NonNull SplashContractView view) {
        setView(view);
    }

    public static SplashPresenter getInstance(SplashContractView view) {
        return new SplashPresenter(view);
    }

    @Override
    public void showSnackbar(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showNotConnectedLayout() {

    }
}
