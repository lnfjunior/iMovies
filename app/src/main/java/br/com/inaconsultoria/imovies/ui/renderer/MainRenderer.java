package br.com.inaconsultoria.imovies.ui.renderer;

import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;

import com.pedrogomez.renderers.Renderer;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import br.com.inaconsultoria.imovies.R;
import br.com.inaconsultoria.imovies.data.model.Movies;
import br.com.inaconsultoria.imovies.utils.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
public class MainRenderer extends Renderer<Movies> {

    @BindView(R.id.card_poster)
    CardView cardPoster;
    @BindView(R.id.image_poster)
    ImageView imagePoster;
    @BindView(R.id.ratingBar)
    RatingBar mRatingBar;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    public interface OnMovieClickListener {
        void onOpenDetailAcitivity(Movies movie, ImageView poster);
    }

    private OnMovieClickListener listener;

    public MainRenderer(OnMovieClickListener listener) {
        this.listener = listener;
    }

    @Override
    protected void setUpView(View rootView) {
    }

    @Override
    protected void hookListeners(View rootView) {
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View inflatedView = inflater.inflate(R.layout.list_movies_item, parent, false);
        ButterKnife.bind(this, inflatedView);
        return inflatedView;
    }

    @Override
    public void render() {
        Movies movie = getContent();
        mProgressBar.setVisibility(View.VISIBLE);
        renderPosterImage(Constants.URL_POSTER_CLEAR + movie.getPosterPath());
        renderVoteAverage(String.valueOf(movie.getVoteAverage() / 2));
        listenClick();
    }

    private void renderPosterImage(String brandLogoUrl) {
        Picasso.with(getContext()).load(brandLogoUrl).placeholder(R.drawable.poster_backgroud).into(imagePoster, new Callback() {
            @Override
            public void onSuccess() {
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

    private void renderVoteAverage(String vote) {
        mRatingBar.setRating(Float.valueOf(vote));
    }

    private void listenClick() {
        cardPoster.setOnClickListener(view -> {
            Movies movie = getContent();
            listener.onOpenDetailAcitivity(movie, imagePoster);
        });
    }
}