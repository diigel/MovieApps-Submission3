package com.digel.dhanie_sub3.Rest;

import com.digel.dhanie_sub3.BuildConfig;
import com.digel.dhanie_sub3.Rest.Response.MovieResponse;
import com.digel.dhanie_sub3.Rest.Response.TvResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("3/movie/now_playing")
    Call<MovieResponse> getMovie(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page);

    @GET("3/tv/popular")
    Call<TvResponse> getTv(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page);
}
