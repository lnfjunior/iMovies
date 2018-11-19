package br.com.inaconsultoria.imovies.data.db;

import android.net.Uri;
import android.provider.BaseColumns;

public class MoviesDbContract {

	public static final String AUTHORITY = "br.com.inaconsultoria.imovies";
	public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
	public static final String PATH_MOVIES = "movies";

	public static final class MoviesEntry implements BaseColumns {

		public static final Uri CONTENT_URI =
				BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();
		public static final String TABLE_NAME = "im_favorites";
		public static final String COLUMN_ID = "id";
		public static final String COLUMN_TITLE = "title";
		public static final String COLUMN_RELEASE_DATE = "releaseDate";
		public static final String COLUMN_VOTE_AVERAGE = "voteAverage";
		public static final String COLUMN_POSTER_PATH = "posterPath";
		public static final String COLUMN_BACKDROP_PATH = "backdropPath";
		public static final String COLUMN_TIMESTAMP = "timestamp";
	}
}
