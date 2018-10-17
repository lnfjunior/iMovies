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
public class ResponseMoviesList {

	@SerializedName("page")
    Integer page;

	@SerializedName("total_results")
    Integer totalResults;

	@SerializedName("total_pages")
    Integer totalPages;

	@SerializedName("results")
    List<Movies> results;

	ResponseMoviesList() {
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public List<Movies> getResults() {
		return results;
	}

	public void setResults(List<Movies> results) {
		this.results = results;
	}
}
