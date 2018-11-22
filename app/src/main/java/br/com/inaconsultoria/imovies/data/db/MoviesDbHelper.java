package br.com.inaconsultoria.imovies.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MoviesDbHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "imovies.db";
	private static final int DATABASE_VERSION = 1;

	MoviesDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {

		final String SQL_CREATE_MOVIES_FAVORITES_TABLE = "CREATE TABLE " + MoviesDbContract.MoviesEntry.TABLE_NAME + " (" +
				MoviesDbContract.MoviesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
				MoviesDbContract.MoviesEntry.COLUMN_ID + " INTEGER NOT NULL, " +
				MoviesDbContract.MoviesEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
				MoviesDbContract.MoviesEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL, " +
				MoviesDbContract.MoviesEntry.COLUMN_VOTE_AVERAGE + " NUMERIC NOT NULL, " +
				MoviesDbContract.MoviesEntry.COLUMN_POSTER_PATH + " TEXT NOT NULL, " +
				MoviesDbContract.MoviesEntry.COLUMN_BACKDROP_PATH + " TEXT, " +
				MoviesDbContract.MoviesEntry.COLUMN_TIMESTAMP + "TIME TIMESTAMP DEFAULT (strftime('%s', 'now'))" +
				"); ";

		sqLiteDatabase.execSQL(SQL_CREATE_MOVIES_FAVORITES_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
		sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MoviesDbContract.MoviesEntry.TABLE_NAME);
		onCreate(sqLiteDatabase);
	}
}