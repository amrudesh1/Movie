package com.application.amrudesh.moviedb_udacity.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.application.amrudesh.moviedb_udacity.Data.MovieAdapter;
import com.application.amrudesh.moviedb_udacity.Data.MovieViewModel;
import com.application.amrudesh.moviedb_udacity.Model.Movie;
import com.application.amrudesh.moviedb_udacity.R;

import java.util.ArrayList;
import java.util.List;

public class fav extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private List<Movie> mov;
    MovieViewModel movieViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        recyclerView = (RecyclerView) findViewById(R.id.fav_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mov = new ArrayList<>();
        movieAdapter = new MovieAdapter(this, mov);
        recyclerView.setAdapter(movieAdapter);
        setUpViewModel();

    }


    private void setUpViewModel(){

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                movieAdapter.setMovies(movies);


            }
        });

    }
}
