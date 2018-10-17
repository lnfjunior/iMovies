package br.com.inaconsultoria.imovies.data.repository.movies;

import android.support.annotation.NonNull;

import br.com.inaconsultoria.imovies.BuildConfig;
import br.com.inaconsultoria.imovies.data.api.ApiConnection;
import br.com.inaconsultoria.imovies.data.model.Movies;
import br.com.inaconsultoria.imovies.data.model.ResponseMoviesList;
import br.com.inaconsultoria.imovies.utils.Constants;
import br.com.inaconsultoria.imovies.utils.RequestCallback;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
public class MoviesRepositoryImpl implements MoviesRepository {

	private final ApiConnection apiConnection;

	private MoviesRepositoryImpl(@NonNull ApiConnection apiConnection) {
		this.apiConnection = apiConnection;
	}

	public static MoviesRepositoryImpl getInstance() {
		return new MoviesRepositoryImpl(ApiConnection.getInstance());
	}

	@Override
	public void getMoviesPopular(RequestCallback<ResponseMoviesList> callback) {
		apiConnection
				.build(MoviesAPI.class)
				.getMoviesPopular(
						BuildConfig.API_KEY_THEMOVIE_DB,
						Constants.LOCALE)
				.enqueue(callback);
	}

	@Override
	public void getMoviesTopRated(RequestCallback<ResponseMoviesList> callback) {
		apiConnection
				.build(MoviesAPI.class)
				.getMoviesTopRated(
						BuildConfig.API_KEY_THEMOVIE_DB,
                        Constants.LOCALE)
				.enqueue(callback);
	}

	@Override
	public void getMoviesNowPlaying(RequestCallback<ResponseMoviesList> callback) {
		apiConnection
				.build(MoviesAPI.class)
				.getMoviesNowPlaying(
						BuildConfig.API_KEY_THEMOVIE_DB,
						Constants.LOCALE)
				.enqueue(callback);
	}

	@Override
	public void getMoviesUpComing(RequestCallback<ResponseMoviesList> callback) {
		apiConnection
				.build(MoviesAPI.class)
				.getMoviesUpComing(
						BuildConfig.API_KEY_THEMOVIE_DB,
						Constants.LOCALE)
				.enqueue(callback);
	}

	@Override
	public void getMovieById(Integer id, RequestCallback<Movies> callback) {
		apiConnection
				.build(MoviesAPI.class)
				.getMovieById(
						id,
						BuildConfig.API_KEY_THEMOVIE_DB,
						Constants.LOCALE,
						Constants.APPEND_VIEDOS_AND_REVIEWS)
				.enqueue(callback);
	}

}
