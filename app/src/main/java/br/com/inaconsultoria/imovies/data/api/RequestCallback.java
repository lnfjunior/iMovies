package br.com.inaconsultoria.imovies.data.api;

import android.support.annotation.NonNull;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
public abstract class RequestCallback<T> implements Callback<T> {

	private static final String LOG_TAG = RequestCallback.class.getName();

	public abstract void onRequestResponse(T responseObject);

	public abstract void onRequestError(String error);

	@SuppressWarnings("unchecked")
	@Override
	public void onResponse(@NonNull Call call, @NonNull Response response) {
		if (response.isSuccessful()) {
			onRequestResponse((T) response.body());
		} else {

			Log.e(LOG_TAG, String.valueOf(response.code()));
			onFailure(call, new Exception(response.message()));
		}
	}

	@Override
	public void onFailure(@NonNull Call call, @NonNull Throwable throwable) {
		onRequestError(throwable.getMessage());
	}
}
