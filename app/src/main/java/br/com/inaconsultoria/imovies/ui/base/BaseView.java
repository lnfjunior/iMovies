package br.com.inaconsultoria.imovies.ui.base;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
public interface BaseView {

	void showSnackbar(String message);
	void showLoading();
	void hideLoading();
	void showNotConnectedLayout();
}
