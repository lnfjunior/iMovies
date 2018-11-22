package br.com.inaconsultoria.imovies.ui.base;

import android.support.annotation.NonNull;

import br.com.inaconsultoria.imovies.App;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
public class BasePresenter<V extends BaseView> {

	protected V mView;

	protected void setView(V view) {
		this.mView = view;
	}

	final void attachView(@NonNull V view) {
		this.mView = view;
	}

	final void detachView() {
		mView = null;
	}

	private boolean isViewAttached() {
		return this.mView == null;
	}

	protected void doApiCall(OnDoApiCallAction apiCallAction) {
		if (isViewAttached()) return;
		if (App.getInstance().isOnline()) {
			this.mView.showLoading();
			apiCallAction.doAction();
		} else {
			this.mView.showNotConnectedLayout();
		}
	}

	protected void doOnApiCallSuccess(OnApiCallSuccess onApiCallSuccess) {
		if (isViewAttached()) return;
		this.mView.hideLoading();
		onApiCallSuccess.doOnSuccess();
	}

	protected void doOnApiCallFailure(OnApiCallFailure onApiCallFailure) {
		if (isViewAttached()) return;
		this.mView.hideLoading();
        onApiCallFailure.doOnFailure();
	}

	protected interface OnDoApiCallAction {
		void doAction();
	}

	protected interface OnApiCallSuccess {
		void doOnSuccess();
	}

	protected interface OnApiCallFailure {
		void doOnFailure();
	}
}
