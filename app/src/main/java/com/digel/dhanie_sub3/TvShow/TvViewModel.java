package com.digel.dhanie_sub3.TvShow;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.digel.dhanie_sub3.BuildConfig;
import com.digel.dhanie_sub3.R;
import com.digel.dhanie_sub3.Rest.ApiService;
import com.digel.dhanie_sub3.Rest.Response.Client;
import com.digel.dhanie_sub3.Rest.Response.TvResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("ALL")
public class TvViewModel extends ViewModel {

    private MutableLiveData<TvResponse> tvLive;
    private Call<TvResponse> callTv;
    public Context context;

    @Nullable
    public final MutableLiveData<TvResponse> getTvLive() {
        if (this.tvLive == null) {
            this.tvLive = new MutableLiveData<>();
            this.geTv();
        }

        return this.tvLive;
    }

    private void geTv() {
        try {
            ApiService apiService = Client.getClient().create(ApiService.class);
            Call<TvResponse> call = apiService.getTv(BuildConfig.API_KEY, String.valueOf(R.string.language), 5);
            call.enqueue(new Callback<TvResponse>() {
                @Override
                public void onResponse(Call<TvResponse> call, Response<TvResponse> response) {
                    ApiService apiService = Client.getClient().create(ApiService.class);
                    callTv = apiService.getTv(BuildConfig.API_KEY, String.valueOf(R.string.language), 5);
                    callTv.enqueue(new Callback<TvResponse>() {
                        @Override
                        public void onResponse(Call<TvResponse> call, Response<TvResponse> response) {
                            List<DataTvShow> tv = null;
                            if (response.body() != null) {
                                tv = response.body().getTv();
                                tvLive.setValue(response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call<TvResponse> call, Throwable t) {
                            Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onFailure(Call<TvResponse> call, Throwable t) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    void Cancel() {
        callTv.cancel();
    }
}
