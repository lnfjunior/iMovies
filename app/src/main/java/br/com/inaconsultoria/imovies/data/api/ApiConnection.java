package br.com.inaconsultoria.imovies.data.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import br.com.inaconsultoria.imovies.utils.Constants;
import br.com.inaconsultoria.imovies.utils.serialization.AnnotationDeserializationStrategy;
import br.com.inaconsultoria.imovies.utils.serialization.AnnotationSerializationStrategy;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
public class ApiConnection {

	private static ApiConnection instance;
	private Retrofit retrofit;

	public synchronized static ApiConnection getInstance() {

		if (instance == null) {
			instance = new ApiConnection();
		}

		return instance;
	}

	private ApiConnection() {
	}

	public <T> T build(final Class<T> service) {
		if (retrofit == null) {
			OkHttpClient client = getHttpClient();

			retrofit = new Retrofit.Builder()
					.addConverterFactory(getGsonConverter())
					.baseUrl(Constants.URL_THE_MOVIE_DB_CLEAN)
					.client(client)
					.build();
		}

		return this.retrofit.create(service);
	}

	private GsonConverterFactory getGsonConverter() {
		Gson gson = new GsonBuilder()
				.addSerializationExclusionStrategy(new AnnotationSerializationStrategy())
				.addDeserializationExclusionStrategy(new AnnotationDeserializationStrategy())
				.create();

		return GsonConverterFactory.create(gson);
	}

	private OkHttpClient getHttpClient() {
		OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
		builder.readTimeout(10, TimeUnit.SECONDS);
		builder.connectTimeout(5, TimeUnit.SECONDS);

		builder.addInterceptor(chain -> {
			Request request = chain.request()
					.newBuilder()
					.build();
			return chain.proceed(request);
		});

		return builder.build();
	}
}
