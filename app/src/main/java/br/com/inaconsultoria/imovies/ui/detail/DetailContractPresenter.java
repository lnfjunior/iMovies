
package br.com.inaconsultoria.imovies.ui.detail;


import br.com.inaconsultoria.imovies.di.PerActivity;
import br.com.inaconsultoria.imovies.ui.base.BaseView;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
@PerActivity
public interface DetailContractPresenter extends BaseView {

    void getMovie(int id);
}
