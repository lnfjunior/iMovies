package br.com.inaconsultoria.imovies.data.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
@Parcel
public class Movies {

	@SerializedName("id")
    Integer id;

	@SerializedName("vote_count")
    Integer voteCount;

	@SerializedName("vote_average")
    Double voteAverage;

	@SerializedName("title")
    String title;

	@SerializedName("popularity")
    Double popularity;

	@SerializedName("poster_path")
    String posterPath;

	@SerializedName("backdrop_path")
    String backdropPath;

	@SerializedName("overview")
    String overview;

	@SerializedName("release_date")
    String releaseDate;

	@SerializedName("genres")
    List<Genres> genres;

	@SerializedName("production_companies")
    List<ProductionCompanies> productionCompanies;

	@SerializedName("videos")
	Videos videos;

	@SerializedName("runtime")
    Integer runtime;

	@SerializedName("reviews")
	Reviews reviews;

	public Movies() {
	}

	public Movies(int id, String title, String releaseDate, double voteAverage, String posterPath) {
		this.id = id;
		this.title = title;
		this.releaseDate = releaseDate;
		this.voteAverage = voteAverage;
		this.posterPath = posterPath;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}

	public Double getVoteAverage() {
		return voteAverage;
	}

	public void setVoteAverage(Double voteAverage) {
		this.voteAverage = voteAverage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPopularity() {
		return popularity;
	}

	public void setPopularity(Double popularity) {
		this.popularity = popularity;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	public String getBackdropPath() {
		return backdropPath;
	}

	public void setBackdropPath(String backdropPath) {
		this.backdropPath = backdropPath;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public List<Genres> getGenres() {
		return genres;
	}

	public void setGenres(List<Genres> genres) {
		this.genres = genres;
	}

	public List<ProductionCompanies> getProductionCompanies() {
		return productionCompanies;
	}

	public void setProductionCompanies(List<ProductionCompanies> productionCompanies) {
		this.productionCompanies = productionCompanies;
	}

	public Videos getVideos() {
		return videos;
	}

	public void setVideos(Videos videos) {
		this.videos = videos;
	}

	public Integer getRuntime() {
		return runtime;
	}

	public void setRuntime(Integer runtime) {
		this.runtime = runtime;
	}

	public Reviews getReviews() {
		return reviews;
	}

	public void setReviews(Reviews reviews) {
		this.reviews = reviews;
	}
}
