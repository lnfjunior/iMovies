
package br.com.inaconsultoria.imovies.di.component;

import br.com.inaconsultoria.imovies.di.PerActivity;
import br.com.inaconsultoria.imovies.di.module.ActivityModule;
import br.com.inaconsultoria.imovies.ui.main.MainActivity;
import br.com.inaconsultoria.imovies.ui.splash.SplashActivity;
import dagger.Component;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);
    void inject(SplashActivity activity);
}
