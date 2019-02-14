package com.application.amrudesh.moviedb_udacity.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.support.v7.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.application.amrudesh.moviedb_udacity.Data.MovieAdapter;
import com.application.amrudesh.moviedb_udacity.Model.Movie;
import com.application.amrudesh.moviedb_udacity.R;
import com.application.amrudesh.moviedb_udacity.Util.Constants;
import com.application.amrudesh.moviedb_udacity.Util.Prefs;
import com.claudiodegio.msv.MaterialSearchView;
import com.claudiodegio.msv.OnSearchViewListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements OnSearchViewListener {
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private List<Movie> movieList;
    private RequestQueue requestQueue;
    private MaterialSearchView searchView;
    String search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        searchView = (MaterialSearchView) findViewById(R.id.sv);
        searchView.setOnSearchViewListener(MainActivity.this);
        requestQueue = Volley.newRequestQueue(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Prefs prefs = new Prefs(MainActivity.this);
        String search = prefs.getSearch();

        movieList = new ArrayList<>();
        //getMovies(search);
        movieList = getMovies(search);
        movieAdapter = new MovieAdapter(this, movieList);
        recyclerView.setAdapter(movieAdapter);


    }


    public List<Movie> getMovies(String searchTerm) {


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Constants.COMPLETE_URL + searchTerm, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray movieArray = response.getJSONArray("results");
                    for (int i = 0; i < movieArray.length(); i++) {
                        JSONObject movieObj = movieArray.getJSONObject(i);
                        Movie movie = new Movie();
                        movie.setTitle(movieObj.getString("title"));
                        movie.setReleaseDate(movieObj.getString("release_date"));
                        movie.setImage(movieObj.getString("poster_path"));
                        movie.setPlot(movieObj.getString("overview"));
                        movie.setRating(movieObj.getString("vote_average"));
                        movie.setMovieID(movieObj.getString("id"));


                        movieList.add(movie);
                        //TODO:Remove The LOG from Program

                        Log.i("Movie", movie.getTitle());
                        Log.i("Movie", "Year Released:"+ movie.getReleaseDate());
                        Log.i("Movie", movie.getImage());
                        Log.i("Movie", movie.getPlot());
                        Log.i("Movie", movie.getRating());
                        Log.i("Movie",movie.getMovieID());



                    }
                    movieAdapter.notifyDataSetChanged();// This is very Important
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
        return movieList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }


    @Override
    public void onSearchViewShown() {

    }

    @Override
    public void onSearchViewClosed() {

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        if (!s.isEmpty()) {
            Prefs prefs = new Prefs(MainActivity.this);
            prefs.setSearch(s);
            movieList.clear();
            getMovies(s);


        }
        return true;
    }

    @Override
    public void onQueryTextChange(String s) {

    }
}
