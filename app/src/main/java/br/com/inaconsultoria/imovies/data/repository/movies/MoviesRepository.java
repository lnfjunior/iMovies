package br.com.inaconsultoria.imovies.data.repository.movies;

import android.content.Context;

import java.util.List;

import br.com.inaconsultoria.imovies.data.model.Movies;
import br.com.inaconsultoria.imovies.data.model.ResponseMoviesList;
import br.com.inaconsultoria.imovies.utils.RequestCallback;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
public interface MoviesRepository {

	void getMoviesPopular(RequestCallback<ResponseMoviesList> callback);

	void getMoviesTopRated(RequestCallback<ResponseMoviesList> callback);

	void getMoviesNowPlaying(RequestCallback<ResponseMoviesList> callback);

	void getMoviesUpComing(RequestCallback<ResponseMoviesList> callback);

	void getMovieById(Integer id, RequestCallback<Movies> callback);

	void getMovieFromSqLite(Context contect, RequestCallback<List<Movies>> callback);

}
