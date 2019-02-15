package com.application.amrudesh.moviedb_udacity.Model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert
    void Insert(Movie movie);

    @Delete
    void delete(Movie movie);

    @Query("SELECT * FROM movieDB ORDER BY releaseDate ASC")
    LiveData<List<Movie>> getAllMovies();
}
