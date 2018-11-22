package br.com.inaconsultoria.imovies.ui.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pedrogomez.renderers.Renderer;

import br.com.inaconsultoria.imovies.R;
import br.com.inaconsultoria.imovies.data.model.ReviewsResults;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
public class DetailReviewsRenderer extends Renderer<ReviewsResults> {

	@BindView(R.id.text_name)
    TextView textName;
	@BindView(R.id.text_review)
    TextView textReview;

	@Override
	protected void setUpView(View rootView) {

	}

	@Override
	protected void hookListeners(View rootView) {

	}

	@Override
	protected View inflate(LayoutInflater inflater, ViewGroup parent) {
		View inflatedView = inflater.inflate(R.layout.list_review_item, parent, false);
		ButterKnife.bind(this, inflatedView);
		return inflatedView;
	}

	@Override
	public void render() {
		renderName(getContent());
		renderReview(getContent());
	}

	private void renderName(ReviewsResults review) {
		if (review != null && !review.getAuthor().equals("")) {
			textName.setText(review.getAuthor());
		}
	}

	private void renderReview(ReviewsResults review) {
		if (review != null && !review.getContent().equals("")) {
			textReview.setText(review.getContent());
		}
	}

}
