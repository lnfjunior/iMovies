package br.com.inaconsultoria.imovies.data.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
@Parcel
public class VideosResults {

	@SerializedName("id")
    String id;

	@SerializedName("name")
    String name;

	@SerializedName("site")
    String site;

	@SerializedName("type")
    String type;

	@SerializedName("key")
    String key;

	VideosResults() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
