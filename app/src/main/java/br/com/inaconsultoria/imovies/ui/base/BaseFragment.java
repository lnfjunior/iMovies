package br.com.inaconsultoria.imovies.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
public abstract class BaseFragment extends Fragment implements BaseView {

	public BaseFragment() {
	}

	protected abstract int getFragmentLayout();

	protected abstract void onFragmentCreate(View view);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		final View fragmentView = inflater.inflate(getFragmentLayout(), container, false);
		ButterKnife.bind(this, fragmentView);
		onFragmentCreate(fragmentView);
		return fragmentView;
	}

	@Override
	public void showToastMessage(String message) {
		if (getActivity() != null) {
			((BaseActivity) getActivity()).showToastMessage(message);
		}
	}

	@Override
	public void showSnackbar(String message) {
		if (getActivity() != null) {
			((BaseActivity) getActivity()).showSnackbar(message);
		}
	}

	@Override
	public void showLoading() {
		if (getActivity() != null) {
			((BaseActivity) getActivity()).showLoading();
		}
	}

	@Override
	public void hideLoading() {
		if (getActivity() != null) {
			((BaseActivity) getActivity()).hideLoading();
		}
	}

	@Override
	public void showNotConnectedLayout() {
		if (getActivity() != null) {
			((BaseActivity) getActivity()).showNotConnectedLayout();
		}
	}

}
