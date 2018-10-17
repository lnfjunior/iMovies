package br.com.inaconsultoria.imovies.ui.detail;

import android.support.annotation.NonNull;

import br.com.inaconsultoria.imovies.data.model.Movies;
import br.com.inaconsultoria.imovies.data.repository.movies.MoviesRepository;
import br.com.inaconsultoria.imovies.ui.base.BasePresenter;
import br.com.inaconsultoria.imovies.utils.RequestCallback;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
public class DetailPresenter extends BasePresenter<DetailContractView>
		implements DetailContractPresenter {

	private final MoviesRepository mRepository;

	private DetailPresenter(@NonNull DetailContractView mainListView,
	                      @NonNull MoviesRepository moviesRepository) {
		setView(mainListView);
		this.mRepository = moviesRepository;
	}

	public static DetailPresenter getInstance(@NonNull DetailContractView mainListView,
                                            @NonNull MoviesRepository moviesRepository) {
		return new DetailPresenter(mainListView, moviesRepository);
	}

	@Override
	public void getMovie(int id) {
		doApiCall(() -> mRepository.getMovieById(id, new RequestCallback<Movies>() {

			@Override
			public void onRequestResponse(Movies responseObject) {
				doOnApiCallSuccess(() -> mView.setMovie(responseObject));
			}

			@Override
			public void onRequestError(String error) {
				doOnApiCallFailure(() -> mView.showSnackbar(error));
			}

		}));
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
