package br.com.inaconsultoria.imovies.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * iMovies
 * Created by Luiz Nogueira on 16/10/2018
 * All rights reserved 2018.
 */
@SuppressLint("SimpleDateFormat")
public class DateUtil {
    public static String convert(String releaseDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-mm");
        SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
        Date date;

        try {
            date = sdf.parse(releaseDate);
            return formatter.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
