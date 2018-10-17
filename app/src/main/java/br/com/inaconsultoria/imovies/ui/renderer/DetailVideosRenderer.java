package br.com.inaconsultoria.imovies.ui.renderer;

import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pedrogomez.renderers.Renderer;
import com.squareup.picasso.Picasso;

import br.com.inaconsultoria.imovies.R;
import br.com.inaconsultoria.imovies.data.model.VideosResults;
import butterknife.BindView;
import butterknife.ButterKnife;

import static br.com.inaconsultoria.imovies.utils.Constants.URL_THUMBNAIL_TRAILER_YOUTUBE;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
public class DetailVideosRenderer extends Renderer<VideosResults> {

	@BindView(R.id.card_video)
    CardView cardVideo;
	@BindView(R.id.image_video)
    ImageView imageVideo;

	public interface OnVideoClickListener {
		void openTrailerInYoutube(String key);
	}

	private OnVideoClickListener listener;

	public DetailVideosRenderer(OnVideoClickListener listener) {
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
		View inflatedView = inflater.inflate(R.layout.list_videos_item, parent, false);
		ButterKnife.bind(this, inflatedView);
		return inflatedView;
	}

	@Override
	public void render() {
		renderImage(getContent());
		listenClick();
	}

	private void listenClick() {
		cardVideo.setOnClickListener(view -> {
			VideosResults video = getContent();
			listener.openTrailerInYoutube(video.getKey());
		});
	}

	private void renderImage(VideosResults video) {
		if (video != null && video.getSite().equals("YouTube")) {
			Picasso.with(getContext())
					.load(String.format(URL_THUMBNAIL_TRAILER_YOUTUBE, video.getKey()))
					.placeholder(R.drawable.poster_backgroud)
					.into(imageVideo);
		}
	}

}
