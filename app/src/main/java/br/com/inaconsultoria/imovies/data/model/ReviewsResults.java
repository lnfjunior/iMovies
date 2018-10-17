package br.com.inaconsultoria.imovies.data.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
@Parcel
public class ReviewsResults {

	@SerializedName("id")
    String id;

	@SerializedName("author")
    String author;

	@SerializedName("content")
    String content;

	@SerializedName("url")
    String url;

	ReviewsResults() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
