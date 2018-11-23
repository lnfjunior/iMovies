package br.com.inaconsultoria.imovies.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import br.com.inaconsultoria.imovies.R;
import br.com.inaconsultoria.imovies.data.model.Movies;
import br.com.inaconsultoria.imovies.data.repository.movies.MoviesRepositoryImpl;
import br.com.inaconsultoria.imovies.ui.about.AboutActivity;
import br.com.inaconsultoria.imovies.ui.base.BaseActivity;
import br.com.inaconsultoria.imovies.ui.detail.DetailActivity;
import br.com.inaconsultoria.imovies.ui.renderer.MainRenderer;
import br.com.inaconsultoria.imovies.utils.Constants;
import butterknife.BindString;
import butterknife.BindView;

import static br.com.inaconsultoria.imovies.utils.Constants.FAVORITES;
import static br.com.inaconsultoria.imovies.utils.Constants.NOW_PLAYING;
import static br.com.inaconsultoria.imovies.utils.Constants.POPULAR;
import static br.com.inaconsultoria.imovies.utils.Constants.TOP_RATED;
import static br.com.inaconsultoria.imovies.utils.Constants.UP_COMING;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
public class MainActivity extends BaseActivity<MainContractView>
		implements MainContractView, NavigationView.OnNavigationItemSelectedListener {

	@BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
	@BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
	@BindView(R.id.toolbar_main)
    Toolbar mToolbar;
	@BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
	@BindView(R.id.nav_view)
    NavigationView mNavigationView;

	@BindString(R.string.title_popular)
    String mTitlePopular;
	@BindString(R.string.title_top_rated)
    String mTitleTopRated;
	@BindString(R.string.title_now_playing)
    String mTitleNowPlaying;
	@BindString(R.string.title_up_coming)
    String mTitleUpComing;
	@BindString(R.string.title_favorite)
	String mTitleFavorite;
	@BindString(R.string.app_image_poster)
    String mSharedPoster;

	static final String MOVIES = "movies";
	static final String TITLE = "title";

	private MainPresenter mPresenter;
	private List<Movies> mListMovies = new ArrayList<>();
	private RVRendererAdapter<Movies> mMoviesAdapter;
	private ListAdapteeCollection<Movies> mMoviesAdapterCollection = new ListAdapteeCollection<>();
	private String mLastFilter = POPULAR;
	private String mLastTitle;

	public static Intent getStartIntent(Context context) {
		return new Intent(context, MainActivity.class);
	}

	@Override
	protected int getActivityLayout() {
		return R.layout.activity_main;
	}

	@Override
	protected void onActivityCreate(Bundle savedInstanceState) {
		setUpTollbar();
		setUpDrawer();
		setUpPresenter();
		setUpSwipeRefresh();
		setUpRecycleView();

		verifySavedInstanceState(savedInstanceState);

		setTitle(mLastTitle);
	}

	@Override
	public void setMovies(@NonNull List<Movies> movies) {
		setTitle(mLastTitle);
		this.mListMovies = movies;

		if (movies.size() == 0) {
			mSwipeRefreshLayout.setVisibility(View.GONE);
		} else {
            mSwipeRefreshLayout.setVisibility(View.VISIBLE);
			mRecyclerView.smoothScrollToPosition(0);
			mMoviesAdapter.diffUpdate(this.mListMovies);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		if (mLastFilter.equals(FAVORITES)) {
			refreshMovies();
		}
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		savedInstanceState.putString(TITLE, getTitle().toString());
		savedInstanceState.putParcelable(MOVIES, Parcels.wrap(mListMovies));
		super.onSaveInstanceState(savedInstanceState);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		switch (id) {
			case R.id.filter_populary:
				mLastFilter = POPULAR;
				mLastTitle = mTitlePopular;
				break;
			default:
				mLastTitle = mTitlePopular;
				break;
		}

		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		int id = item.getItemId();

		switch (id) {
			case R.id.filter_populary:
                mLastFilter = POPULAR;
                mLastTitle = mTitlePopular;
				mPresenter.getMovies(mLastFilter);
				break;
			case R.id.filter_vote:
                mLastFilter = TOP_RATED;
                mLastTitle = mTitleTopRated;
                mPresenter.getMovies(mLastFilter);
				break;
			case R.id.filter_now_playing:
                mLastFilter = NOW_PLAYING;
                mLastTitle = mTitleNowPlaying;
                mPresenter.getMovies(mLastFilter);
				break;
			case R.id.filter_up_coming:
                mLastFilter = UP_COMING;
                mLastTitle = mTitleUpComing;
                mPresenter.getMovies(mLastFilter);
				break;
			case R.id.filter_favorite:
				mLastFilter = FAVORITES;
				mLastTitle = mTitleFavorite;
				mPresenter.getMovies(mLastFilter);
				break;
            case R.id.nav_about:
                openAbout();
                break;
		}

		mDrawerLayout.closeDrawer(GravityCompat.START);

		return true;
	}

	@Override
	public void showLoading() {
		if (!mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(true);
		}
	}

	@Override
	public void hideLoading() {
		if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
		}
	}

	@Override
	public void onBackPressed() {
		if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
	public void openDetailActivity(Movies movie, ImageView poster) {
		Intent intent = new Intent(getCurrentContext(), DetailActivity.class);
		intent.putExtra(Constants.SAVED_INSTANCE_STATE_ID_MOVIE, movie.getId());
		intent.putExtra(Constants.SAVED_INSTANCE_STATE_POSTER_MOVIE, movie.getPosterPath());
		intent.putExtra(Constants.SAVED_INSTANCE_STATE_BACKDROP_MOVIE, movie.getBackdropPath());

		ViewCompat.setTransitionName(poster, mSharedPoster);

		ActivityOptionsCompat options = ActivityOptionsCompat.
				makeSceneTransitionAnimation(this, poster, mSharedPoster);
		startActivity(intent, options.toBundle());
	}

	private void setUpTollbar() {
		setSupportActionBar(mToolbar);
	}

	private void setUpDrawer() {
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this,
				mDrawerLayout,
				mToolbar,
				R.string.app_drawer_open,
				R.string.app_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
		toggle.syncState();

		mNavigationView.setNavigationItemSelectedListener(this);
	}

	private void setUpPresenter() {
		mPresenter = MainPresenter.getInstance(
				this,
				MoviesRepositoryImpl.getInstance()
		);
		setActivityPresenter(mPresenter);
	}

	private void setUpRecycleView() {
		int posterWidth = Constants.POSTER_WIDTH;
		GridLayoutManager gridLayoutManager = new GridLayoutManager(this, calculateBestSpanCount(posterWidth));

		mRecyclerView.setLayoutManager(gridLayoutManager);

		RendererBuilder<Movies> rendererBuilder = new RendererBuilder<Movies>()
				.withPrototype(new MainRenderer(this::openDetailActivity));

		mMoviesAdapter = new RVRendererAdapter<>(rendererBuilder, mMoviesAdapterCollection);
		mRecyclerView.setAdapter(mMoviesAdapter);
	}

	private void verifySavedInstanceState(Bundle savedInstanceState) {
		if (savedInstanceState != null) {
            mLastTitle = savedInstanceState.getString(TITLE);
			mListMovies = Parcels.unwrap(savedInstanceState.getParcelable(MOVIES));
			mMoviesAdapter.diffUpdate(mListMovies);
		} else {
			mLastTitle = mTitlePopular;
			refreshMovies();
		}
	}

	private int calculateBestSpanCount(int posterWidth) {
		Display display = getWindowManager().getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics();
		display.getMetrics(outMetrics);
		float screenWidth = outMetrics.widthPixels;
		return Math.round(screenWidth / posterWidth);
	}

	private void setUpSwipeRefresh() {
		mSwipeRefreshLayout.setOnRefreshListener(this::refreshMovies);
	}

	public void refreshMovies() {
		mPresenter.getMovies(mLastFilter);
	}

    public void openAbout() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}