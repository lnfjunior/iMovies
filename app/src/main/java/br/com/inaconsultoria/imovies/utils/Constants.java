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
    int TWO_COLUMNS = 2;
    int THREE_COLUMNS = 3;

    String URL_THE_MOVIE_DB_CLEAN = "http://api.themoviedb.org/3/movie/";
    String URL_POSTER_CLEAR = "https://image.tmdb.org/t/p/w500";
    String URL_THE_MOVIE_DB = "https://www.themoviedb.org/search?query=%s";
    String URL_THE_MOVIE_DB_SHARE = "https://www.themoviedb.org/movie/%s";
    String URL_TRAILER_YOUTUBE = "https://www.youtube.com/watch?v=%s";
    String URL_THUMBNAIL_TRAILER_YOUTUBE = "https://img.youtube.com/vi/%s/default.jpg";

    String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";
    String DD_MM_YYYY = "dd/MM/yyyy";
    String YYYY_MM_DD = "yyyy-MM-dd";

    String INSTANCE_STATE_ID_MOVIE = "id";
    String INSTANCE_STATE_POSTER_MOVIE = "poster";
    String INSTANCE_STATE_BACKDROP_MOVIE = "backdrop";
    String INSTANCE_STATE_MOVIE = "movie";
    String INSTANCE_RELOAD_DATA = "RELOAD_DATA";


}
