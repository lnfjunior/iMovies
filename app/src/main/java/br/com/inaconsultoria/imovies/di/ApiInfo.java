
package br.com.inaconsultoria.imovies.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiInfo {
}
