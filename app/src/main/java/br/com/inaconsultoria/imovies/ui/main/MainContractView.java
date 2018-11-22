
package br.com.inaconsultoria.imovies.ui.main;

import java.util.List;

import br.com.inaconsultoria.imovies.data.model.Movies;
import br.com.inaconsultoria.imovies.ui.base.BaseView;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
public interface MainContractView extends BaseView {

    void setMovies(List<Movies> movies);

}
