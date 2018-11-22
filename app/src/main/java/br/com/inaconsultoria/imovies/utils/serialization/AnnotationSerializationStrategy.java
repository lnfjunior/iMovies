package br.com.inaconsultoria.imovies.utils.serialization;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
public class AnnotationSerializationStrategy implements ExclusionStrategy {

	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		return f.getAnnotation(JsonSerializationExclude.class) != null;
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		return false;
	}
}