package com.digel.dhanie_sub3.Movie;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DataMovie implements Parcelable {

    @SerializedName("title")
    private String title;
    @SerializedName("overview")
    private String overview;
    @SerializedName("poster_path")
    private String poster_path;
    @SerializedName("vote_average")
    private String vote_average;
    @SerializedName("release_date")
    private String release_date;
    @SerializedName("id")
    private Integer id;
    @SerializedName("backdrop_path")
    private String backdrop_path;

    public String getVote_average() {
        return vote_average;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return "https://image.tmdb.org/t/p/w500/" + poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public Integer getId() {
        return id;
    }


    public String getBackdrop_path() {
        return "https://image.tmdb.org/t/p/w500/" + backdrop_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.poster_path);
        dest.writeString(this.vote_average);
        dest.writeString(this.release_date);
        dest.writeValue(this.id);
        dest.writeString(this.backdrop_path);
    }

    private DataMovie(Parcel in) {
        this.title = in.readString();
        this.overview = in.readString();
        this.poster_path = in.readString();
        this.vote_average = in.readString();
        this.release_date = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.backdrop_path = in.readString();
    }

    public static final Creator<DataMovie> CREATOR = new Creator<DataMovie>() {
        @Override
        public DataMovie createFromParcel(Parcel source) {
            return new DataMovie(source);
        }

        @Override
        public DataMovie[] newArray(int size) {
            return new DataMovie[size];
        }
    };
}
