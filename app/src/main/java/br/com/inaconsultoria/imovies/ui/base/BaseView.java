package br.com.inaconsultoria.imovies.ui.base;

import android.content.Context;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
public interface BaseView {

	void showToastMessage(String message);
	void showSnackbar(String message);
	void showLoading();
	void hideLoading();
	void showNotConnectedLayout();
	void hideNotConnectedLayout();

	Context getCurrentContext();
}
