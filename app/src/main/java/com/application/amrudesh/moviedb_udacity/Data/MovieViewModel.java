package com.application.amrudesh.moviedb_udacity.Data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import com.application.amrudesh.moviedb_udacity.Model.Movie;
import java.util.List;

public class MovieViewModel extends AndroidViewModel {
    private MovieRepository movieRepository;
    private LiveData<List<Movie>> allMovies;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
        allMovies = movieRepository.getAllMovies();

    }
    public void insert(Movie movie)
    {
        movieRepository.insertMovies(movie);
    }
    public void delete(Movie movie)
    {
        movieRepository.deleteMovie(movie);
    }
    public LiveData<List<Movie>> getAllMovies()
    {
        return  allMovies;
    }

}
