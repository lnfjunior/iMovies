package br.com.inaconsultoria.imovies.data.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;


public class MoviesContentProvider extends ContentProvider {

	public static final int MOVIES = 100;
	public static final int MOVIES_WITH_ID = 101;

	private static final UriMatcher sUriMatcher = buildUriMatcher();

	public static UriMatcher buildUriMatcher() {
		UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(MoviesDbContract.AUTHORITY, MoviesDbContract.PATH_MOVIES, MOVIES);
		uriMatcher.addURI(MoviesDbContract.AUTHORITY, MoviesDbContract.PATH_MOVIES + "/#", MOVIES_WITH_ID);

		return uriMatcher;
	}

	private MoviesDbHelper mMoviesDbHelper;

	@Override
	public boolean onCreate() {
		Context context = getContext();
		mMoviesDbHelper = new MoviesDbHelper(context);
		return true;
	}

	@Override
	public Uri insert(@NonNull Uri uri, ContentValues values) {
		final SQLiteDatabase db = mMoviesDbHelper.getWritableDatabase();

		int match = sUriMatcher.match(uri);
		Uri returnUri;

		switch (match) {
			case MOVIES:
				long id = db.insert(MoviesDbContract.MoviesEntry.TABLE_NAME, null, values);
				if (id > 0) {
					returnUri = ContentUris.withAppendedId(MoviesDbContract.MoviesEntry.CONTENT_URI, id);
				} else {
					throw new android.database.SQLException("Failed to insert row into " + uri);
				}
				break;
			default:
				throw new UnsupportedOperationException("Unknown uri: " + uri);
		}

		getContext().getContentResolver().notifyChange(uri, null);

		return returnUri;
	}


	@Override
	public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

		final SQLiteDatabase db = mMoviesDbHelper.getReadableDatabase();

		int match = sUriMatcher.match(uri);
		Cursor retCursor;

		switch (match) {
			case MOVIES:
				retCursor = db.query(MoviesDbContract.MoviesEntry.TABLE_NAME,
						projection,
						selection,
						selectionArgs,
						null,
						null,
						sortOrder);
				break;
			default:
				throw new UnsupportedOperationException("Unknown uri: " + uri);
		}

		retCursor.setNotificationUri(getContext().getContentResolver(), uri);

		return retCursor;
	}


	@Override
	public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {

		final SQLiteDatabase db = mMoviesDbHelper.getWritableDatabase();

		int match = sUriMatcher.match(uri);
		int tasksDeleted;

		switch (match) {
			case MOVIES_WITH_ID:
				String id = uri.getPathSegments().get(1);
				tasksDeleted = db.delete(MoviesDbContract.MoviesEntry.TABLE_NAME, String.format("%s=?", MoviesDbContract.MoviesEntry.COLUMN_ID), new String[]{id});
				break;
			default:
				throw new UnsupportedOperationException("Unknown uri: " + uri);
		}

		if (tasksDeleted != 0) {
			getContext().getContentResolver().notifyChange(uri, null);
		}

		return tasksDeleted;
	}


	@Override
	public int update(@NonNull Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

		throw new UnsupportedOperationException("Not yet implemented");
	}


	@Override
	public String getType(@NonNull Uri uri) {

		throw new UnsupportedOperationException("Not yet implemented");
	}

}
