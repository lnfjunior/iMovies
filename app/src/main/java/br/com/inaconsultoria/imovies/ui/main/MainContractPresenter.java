
package br.com.inaconsultoria.imovies.ui.main;


import br.com.inaconsultoria.imovies.di.PerActivity;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
@PerActivity
public interface MainContractPresenter {

    void getMovies(String filter);
}
