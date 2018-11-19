package br.com.inaconsultoria.imovies.ui.main;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.inaconsultoria.imovies.data.model.Movies;
import br.com.inaconsultoria.imovies.data.model.ResponseMoviesList;
import br.com.inaconsultoria.imovies.data.repository.movies.MoviesRepository;
import br.com.inaconsultoria.imovies.ui.base.BasePresenter;
import br.com.inaconsultoria.imovies.utils.RequestCallback;

import static br.com.inaconsultoria.imovies.utils.Constants.FAVORITES;
import static br.com.inaconsultoria.imovies.utils.Constants.NOW_PLAYING;
import static br.com.inaconsultoria.imovies.utils.Constants.POPULAR;
import static br.com.inaconsultoria.imovies.utils.Constants.TOP_RATED;
import static br.com.inaconsultoria.imovies.utils.Constants.UP_COMING;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
public class MainPresenter extends BasePresenter<MainContractView>
		implements MainContractPresenter {

	private final MoviesRepository mRepository;

	private MainPresenter(@NonNull MainContractView mainListView,
	                      @NonNull MoviesRepository moviesRepository) {
		setView(mainListView);
		this.mRepository = moviesRepository;
	}

	public static MainPresenter getInstance(@NonNull MainContractView mainListView,
	                                        @NonNull MoviesRepository moviesRepository) {
		return new MainPresenter(mainListView, moviesRepository);
	}

	@Override
	public void getMovies(String filter) {

		switch (filter) {
			case POPULAR:

				doApiCall(() -> mRepository.getMoviesPopular(
						new RequestCallback<ResponseMoviesList>() {

							@Override
							public void onRequestResponse(ResponseMoviesList responseObject) {
								doOnApiCallSuccess(() -> mView.setMovies(responseObject.getResults()));
							}

							@Override
							public void onRequestError(String error) {
								doOnApiCallFailure(() -> mView.showSnackbar(error));
							}

						}));
				break;
			case TOP_RATED:

				doApiCall(() -> mRepository.getMoviesTopRated(
						new RequestCallback<ResponseMoviesList>() {

							@Override
							public void onRequestResponse(ResponseMoviesList responseObject) {
								doOnApiCallSuccess(() -> mView.setMovies(responseObject.getResults()));
							}

							@Override
							public void onRequestError(String error) {
								doOnApiCallFailure(() -> mView.showSnackbar(error));
							}

						}));

				break;
			case NOW_PLAYING:

				doApiCall(() -> mRepository.getMoviesNowPlaying(
						new RequestCallback<ResponseMoviesList>() {

							@Override
							public void onRequestResponse(ResponseMoviesList responseObject) {
								doOnApiCallSuccess(() -> mView.setMovies(responseObject.getResults()));
							}

							@Override
							public void onRequestError(String error) {
								doOnApiCallFailure(() -> mView.showSnackbar(error));
							}

						}));

				break;
			case UP_COMING:

				doApiCall(() -> mRepository.getMoviesUpComing(
						new RequestCallback<ResponseMoviesList>() {

							@Override
							public void onRequestResponse(ResponseMoviesList responseObject) {
								doOnApiCallSuccess(() -> mView.setMovies(responseObject.getResults()));
							}

							@Override
							public void onRequestError(String error) {
								doOnApiCallFailure(() -> mView.showSnackbar(error));
							}

						}));
				break;
			case FAVORITES:
				doApiCall(() -> mRepository.getMovieFromSqLite(
						mView.getCurrentContext(),
						new RequestCallback<List<Movies>>() {

							@Override
							public void onRequestResponse(List<Movies> responseObject) {
								doOnApiCallSuccess(() -> mView.setMovies(responseObject));

							}

							@Override
							public void onRequestError(String error) {
								doOnApiCallFailure(() -> mView.showSnackbar(error));
							}

						}));
		}

	}

}
