package com.application.amrudesh.moviedb_udacity.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;


@Entity(tableName = "movieDB")
public class Movie implements Serializable {


    private static final long id =1L;
    @PrimaryKey
    @NonNull
    private String movieID;
    private String title;
    private String image;
    private String plot;
    private String rating;
    private String releaseDate;



    private Boolean favBtn;
    public Movie(@NonNull String movieID, String title, String plot,String image, String rating, String releaseDate,boolean favBtn) {
        this.movieID = movieID;
        this.title = title;
        this.plot = plot;
        this.rating = rating;
        this.image = image;
        this.releaseDate = releaseDate;
        this.favBtn = favBtn;
    }

    @Ignore
    public Movie() {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    public String getMovieID() {
        return movieID;
    }
    public Boolean getFavBtn() {
        return favBtn;
    }

    public void setFavBtn(Boolean favBtn) {
        this.favBtn = favBtn;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }
}
