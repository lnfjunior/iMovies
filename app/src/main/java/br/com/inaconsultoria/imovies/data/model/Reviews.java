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
public class Reviews {

	@SerializedName("page")
    Integer page;

	@SerializedName("total_pages")
    Integer totalPages;

	@SerializedName("total_results")
    Integer totalResults;

	@SerializedName("results")
    List<ReviewsResults> results;

	Reviews() {
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}

	public List<ReviewsResults> getResults() {
		return results;
	}

	public void setResults(List<ReviewsResults> results) {
		this.results = results;
	}
}
