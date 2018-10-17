
package br.com.inaconsultoria.imovies.di.module;

import android.app.Application;
import android.content.Context;

import br.com.inaconsultoria.imovies.BuildConfig;
import br.com.inaconsultoria.imovies.di.ApiInfo;
import br.com.inaconsultoria.imovies.di.ApplicationContext;
import dagger.Module;
import dagger.Provides;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY_THEMOVIE_DB;
    }

}
