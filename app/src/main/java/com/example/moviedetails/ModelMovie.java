package com.example.moviedetails;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ModelMovie implements Parcelable {

    private String original_title;
    private String overview;
    private String release_date;
    private String poster_path;
    private String backdrop_path;
    private String adult;

    private String vote_average;

    private String vote_count;





    protected ModelMovie(Parcel in) {
        original_title = in.readString();
        overview = in.readString();
        release_date = in.readString();
        poster_path = in.readString();
        backdrop_path = in.readString();
        popularity = in.readString();
        adult = in.readString();
        vote_average = in.readString();
        vote_count = in.readString();
    }

    public ModelMovie(){}

    public static final Creator<ModelMovie> CREATOR = new Creator<ModelMovie>() {
        @Override
        public ModelMovie createFromParcel(Parcel in) {
            return new ModelMovie(in);
        }

        @Override
        public ModelMovie[] newArray(int size) {
            return new ModelMovie[size];
        }
    };

    public String getVote_count() {return vote_count; }
    public void  setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getVote_average() {return vote_average; }
    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }



    public String getAdult(){ return adult; }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    private String popularity;


    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }






    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = "https://image.tmdb.org/t/p/w500" +poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(original_title);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeString(poster_path);
        dest.writeString(backdrop_path);
        dest.writeString(popularity);
        dest.writeString(adult);
        dest.writeString(vote_average);
        dest.writeString(vote_count);
    }
}