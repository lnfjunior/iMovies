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
public class Videos {

	@SerializedName("results")
    List<VideosResults> results;

	Videos() {
	}

	public List<VideosResults> getResults() {
		return results;
	}

	public void setResults(List<VideosResults> results) {
		this.results = results;
	}
}
