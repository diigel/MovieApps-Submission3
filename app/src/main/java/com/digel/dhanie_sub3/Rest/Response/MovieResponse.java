package com.digel.dhanie_sub3.Rest.Response;

import com.digel.dhanie_sub3.Movie.DataMovie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    @Expose
    @SerializedName("results")
    private List<DataMovie> movies;

    public MovieResponse(List<DataMovie> movies) {
        this.movies = movies;
    }

    public List<DataMovie> getMovies() {
        return movies;
    }

}
