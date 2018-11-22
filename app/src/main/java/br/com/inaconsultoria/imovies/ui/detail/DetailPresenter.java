package br.com.inaconsultoria.imovies.ui.detail;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import br.com.inaconsultoria.imovies.data.db.MoviesDbContract;
import br.com.inaconsultoria.imovies.data.model.Movies;
import br.com.inaconsultoria.imovies.data.repository.movies.MoviesRepository;
import br.com.inaconsultoria.imovies.ui.base.BasePresenter;
import br.com.inaconsultoria.imovies.data.api.RequestCallback;

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

	@SuppressLint("Recycle")
	@Override
	public boolean getMovieFromLocal(Integer id) {
		Cursor mCursor;
		mCursor = mView.getCurrentContext().getContentResolver()
				.query(MoviesDbContract.MoviesEntry.CONTENT_URI,
						null,
						String.format("%s=?", MoviesDbContract.MoviesEntry.COLUMN_ID),
						new String[]{String.valueOf(id)},
						null);

		return (mCursor != null ? mCursor.getCount() : 0) != 0;
	}

	@SuppressLint("Recycle")
	@Override
	public void saveOrRemoveFavorite(Movies movie) {
		Cursor mCursor;
		mCursor = mView.getCurrentContext().getContentResolver()
				.query(MoviesDbContract.MoviesEntry.CONTENT_URI,
						null,
						String.format("%s=?", MoviesDbContract.MoviesEntry.COLUMN_ID),
						new String[]{String.valueOf(movie.getId())},
						null);

		if ((mCursor != null ? mCursor.getCount() : 0) == 0) {

			ContentValues contentValues = new ContentValues();
			contentValues.put(MoviesDbContract.MoviesEntry.COLUMN_ID, movie.getId());
			contentValues.put(MoviesDbContract.MoviesEntry.COLUMN_TITLE, movie.getTitle());
			contentValues.put(MoviesDbContract.MoviesEntry.COLUMN_RELEASE_DATE, movie.getReleaseDate());
			contentValues.put(MoviesDbContract.MoviesEntry.COLUMN_VOTE_AVERAGE, movie.getVoteAverage());
			contentValues.put(MoviesDbContract.MoviesEntry.COLUMN_POSTER_PATH, movie.getPosterPath());
			contentValues.put(MoviesDbContract.MoviesEntry.COLUMN_BACKDROP_PATH, movie.getBackdropPath());

			mView.getCurrentContext().getContentResolver().insert(
					MoviesDbContract.MoviesEntry.CONTENT_URI,
					contentValues);
			mView.updateIconFavorite(true);
			mView.showMessageFavorite(true);

		} else {

			Uri uri = MoviesDbContract.MoviesEntry.CONTENT_URI;
			uri = uri.buildUpon().appendPath(String.valueOf(movie.getId())).build();
			mView.getCurrentContext().getContentResolver().delete(uri, null, null);
			mView.updateIconFavorite(false);
			mView.showMessageFavorite(false);

		}
	}
}
