package com.application.amrudesh.moviedb_udacity.Util;

import com.application.amrudesh.moviedb_udacity.BuildConfig;

public class Constants {

    public static final String URL_LEFT = "https://api.themoviedb.org/3/search/movie?api_key=";
    //Enter The API KEY HERE
    public static final String API_KEY = BuildConfig.ApiKey;
    public static final String URL_RIGHT = "&query=";
    public static final String COMPLETE_URL =URL_LEFT + API_KEY + URL_RIGHT;
    public static final String image_url = "https://image.tmdb.org/t/p/w500";
    public static final String search_base_id_left = "http://api.themoviedb.org/3/movie/";
    public static final String getSearch_base_id_right ="?api_key="+API_KEY;
    public static final String top_rated_url="https://api.themoviedb.org/3/movie/popular?api_key="+BuildConfig.ApiKey;
    public static final String popular_url="https://api.themoviedb.org/3/movie/top_rated?api_key="+BuildConfig.ApiKey;

}
