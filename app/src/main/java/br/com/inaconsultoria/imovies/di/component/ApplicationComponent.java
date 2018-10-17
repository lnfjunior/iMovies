
package br.com.inaconsultoria.imovies.di.component;

import android.content.Context;

import javax.inject.Singleton;

import br.com.inaconsultoria.imovies.App;
import br.com.inaconsultoria.imovies.di.ApplicationContext;
import br.com.inaconsultoria.imovies.di.module.ApplicationModule;
import dagger.Component;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(App app);

    @ApplicationContext
    Context context();

}