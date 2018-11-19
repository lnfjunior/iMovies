package br.com.inaconsultoria.imovies.data.repository.movies;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.inaconsultoria.imovies.BuildConfig;
import br.com.inaconsultoria.imovies.R;
import br.com.inaconsultoria.imovies.data.api.ApiConnection;
import br.com.inaconsultoria.imovies.data.db.MoviesDbContract;
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

	@Override
	public void getMovieFromSqLite(Context context, RequestCallback<List<Movies>> callback) {
		Cursor mCursor;

		try {
			mCursor = context.getContentResolver()
					.query(MoviesDbContract.MoviesEntry.CONTENT_URI,
							null,
							null,
							null,
							MoviesDbContract.MoviesEntry.COLUMN_TITLE);

			List<Movies> moviesList = new ArrayList<>();
			if (mCursor != null && mCursor.moveToFirst()) {
				while (!mCursor.isAfterLast()) {

					moviesList.add(new Movies(
							mCursor.getInt(mCursor.getColumnIndex(MoviesDbContract.MoviesEntry.COLUMN_ID)),
							mCursor.getString(mCursor.getColumnIndex(MoviesDbContract.MoviesEntry.COLUMN_TITLE)),
							mCursor.getString(mCursor.getColumnIndex(MoviesDbContract.MoviesEntry.COLUMN_RELEASE_DATE)),
							mCursor.getDouble(mCursor.getColumnIndex(MoviesDbContract.MoviesEntry.COLUMN_VOTE_AVERAGE)),
							mCursor.getString(mCursor.getColumnIndex(MoviesDbContract.MoviesEntry.COLUMN_POSTER_PATH)),
							mCursor.getString(mCursor.getColumnIndex(MoviesDbContract.MoviesEntry.COLUMN_BACKDROP_PATH))
					));
					mCursor.moveToNext();
				}
				mCursor.close();
			}

			// Carregado com sucesso
			callback.onRequestResponse(moviesList);

		} catch (Exception e) {
			e.printStackTrace();
			callback.onRequestError(context.getResources().getString(R.string.error_favorites));
		}
	}
}
