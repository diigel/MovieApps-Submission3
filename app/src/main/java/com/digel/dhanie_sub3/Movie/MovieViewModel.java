package com.digel.dhanie_sub3.Movie;

import android.content.Context;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.digel.dhanie_sub3.BuildConfig;
import com.digel.dhanie_sub3.R;
import com.digel.dhanie_sub3.Rest.ApiService;
import com.digel.dhanie_sub3.Rest.Response.Client;
import com.digel.dhanie_sub3.Rest.Response.MovieResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("ALL")
public class MovieViewModel extends ViewModel {

    private MutableLiveData<MovieResponse> movieLive;
    private Call<MovieResponse> callMovie;
    public Context context;

    @Nullable
    public final MutableLiveData<MovieResponse> getMovieLive() {
        if (this.movieLive == null) {
            this.movieLive = new MutableLiveData<>();
            this.getMovie();
        }

        return this.movieLive;
    }

    private void getMovie() {
        try {
            ApiService apiService = Client.getClient().create(ApiService.class);
            callMovie = apiService.getMovie(BuildConfig.API_KEY, String.valueOf(R.string.language), 5);
            callMovie.enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    movieLive.postValue(response.body());
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    void Cancel (){
        callMovie.cancel();
    }
}
