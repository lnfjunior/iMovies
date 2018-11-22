
package br.com.inaconsultoria.imovies.ui.detail;

import br.com.inaconsultoria.imovies.data.model.Movies;
import br.com.inaconsultoria.imovies.ui.base.BaseView;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
public interface DetailContractView extends BaseView {

    void setMovie(Movies movies);
    void updateIconFavorite(boolean status);
    void showMessageFavorite(boolean status);


}
