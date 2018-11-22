package br.com.inaconsultoria.imovies.utils;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
public interface Constants {

    String APPEND_VIEDOS_AND_REVIEWS = "videos,reviews";
    String LOCALE = "pt-BR";
    String POPULAR = "POPULAR";
    String TOP_RATED = "TOP_RATED";
    String NOW_PLAYING = "NOW_PLAYING";
    String UP_COMING = "UP_COMING";
    String FAVORITES = "FAVORITES";

    String URL_THE_MOVIE_DB_CLEAN = "http://api.themoviedb.org/3/movie/";
    String URL_POSTER_CLEAR = "https://image.tmdb.org/t/p/w500";
    String URL_THE_MOVIE_DB_SHARE = "https://www.themoviedb.org/movie/%s";
    String URL_TRAILER_YOUTUBE = "https://www.youtube.com/watch?v=%s";
    String URL_THUMBNAIL_TRAILER_YOUTUBE = "https://img.youtube.com/vi/%s/default.jpg";

    String SAVED_INSTANCE_STATE_ID_MOVIE = "id";
    String SAVED_INSTANCE_STATE_POSTER_MOVIE = "poster";
    String SAVED_INSTANCE_STATE_BACKDROP_MOVIE = "backdrop";
    String INSTANCE_STATE_MOVIE = "movie";
    String INSTANCE_STATUS_FAVORITE_FROM_LOCAL = "FAVORITE_FROM_LOCAL";

    int POSTER_WIDTH = 500;


}
