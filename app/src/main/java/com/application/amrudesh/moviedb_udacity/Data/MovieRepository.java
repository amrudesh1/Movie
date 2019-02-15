package com.application.amrudesh.moviedb_udacity.Data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.application.amrudesh.moviedb_udacity.Model.Movie;
import com.application.amrudesh.moviedb_udacity.Model.MovieDao;
import java.util.List;

public class MovieRepository {
    private MovieDao movieDao;
    private LiveData<List<Movie>> allMovies;

    public MovieRepository(Application application)
    {
        MovieDatabase movieDatabase = MovieDatabase.getINSTANCE(application);
        movieDao = movieDatabase.movieDao();
        allMovies = movieDao.getAllMovies();

    }
    public void insertMovies(Movie movie)
    {
        new InsertMovieTask(movieDao).execute(movie);

    }
    public void deleteMovie(Movie movie)
    {
        new DeleteMovieTask(movieDao).execute(movie);
    }
    public LiveData<List<Movie>> getAllMovies()
    {
        return  allMovies;
    }

    private static class InsertMovieTask extends AsyncTask<Movie,Void,Void>
    {
        private MovieDao movieDao;
        private InsertMovieTask(MovieDao movieDao)
        {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.Insert(movies[0]);
            return null;
        }
    }
    private static class DeleteMovieTask extends AsyncTask<Movie,Void,Void>
    {
        private MovieDao movieDao;
        private DeleteMovieTask(MovieDao movieDao)
        {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.delete(movies[0]);
            return null;
        }
    }



}
