package com.digel.dhanie_sub3.Rest.Response;

import com.digel.dhanie_sub3.BuildConfig;
import com.digel.dhanie_sub3.Rest.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    private static Retrofit retrofit = null;
    private static Client client;

    private ApiService api;

    private Client(ApiService api) {
        this.api = api;
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            client = new Client(retrofit.create(ApiService.class));
        }
        return retrofit;
    }
}
