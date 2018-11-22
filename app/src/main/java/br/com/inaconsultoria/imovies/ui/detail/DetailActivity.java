package br.com.inaconsultoria.imovies.ui.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import br.com.inaconsultoria.imovies.R;
import br.com.inaconsultoria.imovies.data.model.Genres;
import br.com.inaconsultoria.imovies.data.model.Movies;
import br.com.inaconsultoria.imovies.data.model.ReviewsResults;
import br.com.inaconsultoria.imovies.data.model.VideosResults;
import br.com.inaconsultoria.imovies.data.repository.movies.MoviesRepositoryImpl;
import br.com.inaconsultoria.imovies.ui.base.BaseActivity;
import br.com.inaconsultoria.imovies.ui.renderer.DetailReviewsRenderer;
import br.com.inaconsultoria.imovies.ui.renderer.DetailVideosRenderer;
import br.com.inaconsultoria.imovies.utils.Constants;
import br.com.inaconsultoria.imovies.utils.DateUtil;
import butterknife.BindString;
import butterknife.BindView;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
public class DetailActivity extends BaseActivity<DetailContractView> implements DetailContractView {

	@BindView(R.id.toolbar_detail)
	Toolbar mToolbar;
	@BindView(R.id.image_backdrop_path)
	ImageView mImageBackdropPath;
	@BindView(R.id.image_poster)
	ImageView mImagePoster;
	@BindView(R.id.text_content_title)
	TextView mTextTitle;
	@BindView(R.id.text_content_vote_average)
	TextView mTextVoteAverage;
	@BindView(R.id.text_content_genres)
	TextView mTextGenres;
	@BindView(R.id.text_content_runtime)
	TextView mTextRuntime;
	@BindView(R.id.text_content_vote_count)
	TextView mTextVoteCount;
	@BindView(R.id.text_content_release_date)
	TextView mTextReleaseDate;
	@BindView(R.id.text_content_overview)
	TextView mTextOverview;
	@BindView(R.id.recycler_view_videos)
	RecyclerView mRecyclerViewTrailer;
	@BindView(R.id.recycler_view_reviews)
	RecyclerView mRecyclerViewReviews;
	@BindView(R.id.layout_infos_movie)
	LinearLayout mLayoutInfosMovie;
	@BindView(R.id.progressBar)
	ProgressBar mProgressBar;
	@BindView(R.id.text_label_videos)
	TextView mTextLabelTrailers;
	@BindView(R.id.text_label_reviews)
	TextView mTextLabelReviews;

	@BindString(R.string.app_no_register)
	String mNoRegister;

	private boolean mStatusFromLocal;
    private int idMovie;
	private Movies mMovie;
	private DetailPresenter mPresenter;
	private MenuItem mMenuItemFavorite;
	private RVRendererAdapter<VideosResults> mTrailersAdapter;
	private ListAdapteeCollection<VideosResults> mTrailersAdapteeColletion = new ListAdapteeCollection<>();
	private RVRendererAdapter<ReviewsResults> mReviewsAdapter;
	private ListAdapteeCollection<ReviewsResults> mReviewsAdapteeColletion = new ListAdapteeCollection<>();

	@Override
	protected int getActivityLayout() {
		return R.layout.activity_detail;
	}

	@Override
	protected void onActivityCreate(Bundle savedInstanceState) {
        initAnimations();
        configTollbar();
        configPresenter();
        configRecycleView();

        if (savedInstanceState != null) {
            idMovie = savedInstanceState.getInt(Constants.SAVED_INSTANCE_STATE_ID_MOVIE);
            setMovie(Parcels.unwrap(savedInstanceState.getParcelable(Constants.INSTANCE_STATE_MOVIE)));
        } else {
            idMovie = getIntent().getIntExtra(Constants.SAVED_INSTANCE_STATE_ID_MOVIE, 0);
            refreshMovie();
        }

	}

	@Override
	public void setMovie(Movies movie) {
		this.mMovie = movie;

		renderGenres();
		renderReleaseDate();
		renderTitle();
		renderVoteAverage();
		renderRuntime();
		renderVoteCount();
		renderOverview();
		renderTrailers();
		renderReviews();

		hideLoading();

		try {
            boolean status = mPresenter.getMovieFromLocal(mMovie.getId());
            updateIconFavorite(status);
        } catch (Exception e) {
		    e.printStackTrace();
        }


	}

	@Override
	public void updateIconFavorite(boolean status) {
		if (mMenuItemFavorite != null) {
			if (!status) {
				mMenuItemFavorite.setIcon(R.drawable.ic_favorite_border_white_24dp);
			} else {
				mMenuItemFavorite.setIcon(R.drawable.ic_favorite_white_24dp);
			}
		}
	}

	@Override
	public void showMessageFavorite(boolean status) {
		if (!status) {
			showSnackbar(getResources().getString(R.string.app_message_removed_favorite));
		} else {
			showSnackbar(getResources().getString(R.string.app_message_added_favorite));
		}
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(Constants.SAVED_INSTANCE_STATE_ID_MOVIE, idMovie);
		savedInstanceState.putBoolean(Constants.INSTANCE_STATUS_FAVORITE_FROM_LOCAL, mStatusFromLocal);
		savedInstanceState.putParcelable(Constants.INSTANCE_STATE_MOVIE, Parcels.wrap(mMovie));
		super.onSaveInstanceState(savedInstanceState);
	}

	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		 mStatusFromLocal = savedInstanceState.getBoolean(Constants.INSTANCE_STATUS_FAVORITE_FROM_LOCAL);
		 setMovie(Parcels.unwrap(savedInstanceState.getParcelable(Constants.INSTANCE_STATE_MOVIE)));
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail, menu);
        mMenuItemFavorite = menu.getItem(0);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean aux = true;

        if (id == R.id.detail_favorite) {
            if (mMovie != null)
                favoriteMovie();
        } else if (id == R.id.detail_share) {
            shareMovie();
        } else {
            aux = super.onOptionsItemSelected(item);
        }

        return aux;
    }

	@Override
	public void showLoading() {
		mLayoutInfosMovie.setVisibility(View.GONE);
		mProgressBar.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideLoading() {
		mProgressBar.setVisibility(View.GONE);
		mLayoutInfosMovie.setVisibility(View.VISIBLE);
	}

	private void initAnimations() {
		Picasso.with(getCurrentContext())
				.load(String.format("%s%s", Constants.URL_POSTER_CLEAR, getIntent().getStringExtra(Constants.SAVED_INSTANCE_STATE_POSTER_MOVIE)))
				.into(mImagePoster);
		Picasso.with(getCurrentContext())
				.load(String.format("%s%s", Constants.URL_POSTER_CLEAR, getIntent().getStringExtra(Constants.SAVED_INSTANCE_STATE_BACKDROP_MOVIE)))
				.into(mImageBackdropPath);
	}

	private void configRecycleView() {

		LinearLayoutManager linearLayoutManagerTrailers = new LinearLayoutManager(this);

		linearLayoutManagerTrailers.setOrientation(LinearLayoutManager.HORIZONTAL);
		mRecyclerViewTrailer.setLayoutManager(linearLayoutManagerTrailers);

		RendererBuilder<VideosResults> rendererBuilderTrailers = new RendererBuilder<VideosResults>()
				.withPrototype(new DetailVideosRenderer(this::openTrailerInYoutube));

		mTrailersAdapter = new RVRendererAdapter<>(rendererBuilderTrailers, mTrailersAdapteeColletion);
		mRecyclerViewTrailer.setAdapter(mTrailersAdapter);

		LinearLayoutManager linearLayoutManagerReviews = new LinearLayoutManager(this);
		linearLayoutManagerReviews.setOrientation(LinearLayoutManager.VERTICAL);
		mRecyclerViewReviews.setLayoutManager(linearLayoutManagerReviews);

		RendererBuilder<ReviewsResults> rendererBuilderReviews = new RendererBuilder<ReviewsResults>()
				.withPrototype(new DetailReviewsRenderer());

		mReviewsAdapter = new RVRendererAdapter<>(rendererBuilderReviews, mReviewsAdapteeColletion);
		mRecyclerViewReviews.setAdapter(mReviewsAdapter);
	}

	public void refreshMovie() {
        mPresenter.getMovie(idMovie);
	}

	private void openTrailerInYoutube(String key) {
		Intent intent = new Intent(Intent.ACTION_VIEW,
				Uri.parse(String.format(Constants.URL_TRAILER_YOUTUBE, key)));
		startActivity(intent);
	}

	private void favoriteMovie() {
		mPresenter.saveOrRemoveFavorite(mMovie);
	}

	private void configTollbar() {
		setSupportActionBar(mToolbar);
		setTitle("");

		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setDisplayShowHomeEnabled(true);
			mToolbar.setNavigationOnClickListener(v -> supportFinishAfterTransition());
		}
	}

	private void configPresenter() {
		mPresenter = DetailPresenter.getInstance(
				this,
				MoviesRepositoryImpl.getInstance()
		);
		setActivityPresenter(mPresenter);
		initAnimations();
		configTollbar();
		configRecycleView();
	}

	private void shareMovie() {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.putExtra(
				Intent.EXTRA_TEXT,
				String.format(Constants.URL_THE_MOVIE_DB_SHARE, mMovie.getId())
		);
		intent.setType("text/plain");

		if (intent.resolveActivity(getPackageManager()) != null) {
			startActivity(intent);
		}
	}
	public String getAllGenres(List<Genres> genres) {
		StringBuilder names = new StringBuilder();

		for (Genres genre : genres) {
			names.append(genre.getName()).append(", ");
		}

		return names.toString().substring(0, names.length() -2);
	}

	private void renderGenres() {
		try {
			if (mMovie.getGenres() != null) {
				mTextGenres.setText(String.format("%s", getAllGenres(mMovie.getGenres())));
			} else {
				mTextGenres.setText(mNoRegister);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void renderReleaseDate() {
		try {
			if (mMovie.getReleaseDate() != null && !mMovie.getReleaseDate().equals("")) {
				mTextReleaseDate.setText(String.format("%s", DateUtil.convert(mMovie.getReleaseDate())));
			} else {
				mTextReleaseDate.setText(mNoRegister);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void renderTitle() {
		try {
			if (mMovie.getTitle() != null && !mMovie.getTitle().equals("")) {
				mTextTitle.setText(String.format("%s", mMovie.getTitle()));
			} else {
				mTextTitle.setText(mNoRegister);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void renderVoteAverage() {
		try {
			if (mMovie.getVoteAverage() != null) {
				mTextVoteAverage.setText(String.format("%s", String.valueOf((mMovie.getVoteAverage()) / 2 )));
			} else {
				mTextVoteAverage.setText(mNoRegister);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void renderRuntime() {
		try {
			if (mMovie.getRuntime() != null) {
				mTextRuntime.setText(String.format("%s %s", mMovie.getRuntime(), getResources().getString(R.string.app_runtime_scale)));
			} else {
				mTextRuntime.setText(mNoRegister);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void renderVoteCount() {
		try {
			if (mMovie.getVoteCount() != null) {
				mTextVoteCount.setText(String.format("%s", mMovie.getVoteCount()));
			} else {
				mTextVoteCount.setText(mNoRegister);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void renderOverview() {
		try {
			if (mMovie.getOverview() != null && !mMovie.getOverview().equals("")) {
				mTextOverview.setText(String.format("%s", mMovie.getOverview()));
			} else {
				mTextOverview.setText(mNoRegister);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void renderTrailers() {
		try {
			if (mMovie.getVideos().getResults().size() != 0) {
				mTrailersAdapter.diffUpdate(mMovie.getVideos().getResults());
			} else {
				mTextLabelTrailers.setVisibility(View.GONE);
				mRecyclerViewTrailer.setVisibility(View.GONE);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void renderReviews() {
		try {
			if (mMovie.getReviews().getResults().size() != 0) {
				mReviewsAdapter.diffUpdate(mMovie.getReviews().getResults());
			} else {
				mTextLabelReviews.setVisibility(View.GONE);
				mRecyclerViewReviews.setVisibility(View.GONE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}