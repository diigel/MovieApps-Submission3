package com.digel.dhanie_sub3.Rest.Response;

import com.digel.dhanie_sub3.TvShow.DataTvShow;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvResponse {
    @SerializedName("results")
    @Expose
    private List<DataTvShow> tv;

    public TvResponse(List<DataTvShow> tv) {
        this.tv = tv;
    }

    public List<DataTvShow> getTv() {
        return tv;
    }
}
