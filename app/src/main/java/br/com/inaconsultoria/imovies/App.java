package br.com.inaconsultoria.imovies;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
public class App extends Application {

    private static App mInstance;

    private boolean mLoadData = true;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static App getInstance() {
        return mInstance;
    }

    public boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return (activeNetwork != null && activeNetwork.isConnected());
    }

    public boolean isLoadData() {
        return mLoadData;
    }

    public void setLoadData(boolean mLoadData) {
        this.mLoadData = mLoadData;
    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

}
