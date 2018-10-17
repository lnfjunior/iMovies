package br.com.inaconsultoria.imovies.data.repository.movies;

import br.com.inaconsultoria.imovies.data.model.Movies;
import br.com.inaconsultoria.imovies.data.model.ResponseMoviesList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
public interface MoviesAPI {

	@GET("popular")
	Call<ResponseMoviesList> getMoviesPopular(
            @Query("api_key") String apiKey,
            @Query("language") String language);

	@GET("top_rated")
	Call<ResponseMoviesList> getMoviesTopRated(
            @Query("api_key") String apiKey,
            @Query("language") String language);

	@GET("now_playing")
	Call<ResponseMoviesList> getMoviesNowPlaying(
            @Query("api_key") String apiKey,
            @Query("language") String language);

	@GET("upcoming")
	Call<ResponseMoviesList> getMoviesUpComing(
            @Query("api_key") String apiKey,
            @Query("language") String language);

	@GET("{id}")
	Call<Movies> getMovieById(
            @Path("id") Integer movieId,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("append_to_response") String append);

}
