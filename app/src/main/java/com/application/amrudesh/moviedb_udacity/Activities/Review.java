package com.application.amrudesh.moviedb_udacity.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.application.amrudesh.moviedb_udacity.Data.ReviewAdapter;
import com.application.amrudesh.moviedb_udacity.Model.Movie;
import com.application.amrudesh.moviedb_udacity.Model.ReviewMod;
import com.application.amrudesh.moviedb_udacity.R;
import com.application.amrudesh.moviedb_udacity.Util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Review extends AppCompatActivity {
    String url;
    RequestQueue queue;
    RecyclerView recyclerView;
    ReviewAdapter reviewAdapter;
    TextView content,descri;
    private List<ReviewMod> reviewModList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        Movie movie;
        queue = Volley.newRequestQueue(this);
        movie =(Movie) getIntent().getSerializableExtra("movie");
        String id = movie.getMovieID();
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
        url = Constants.search_base_id_left + id +"/reviews"+Constants.getSearch_base_id_right+"&language=en-US&page=1";
        Log.i("TAG",url);
        setUI();
        getReviewData();


    }

    private void setUI() {
        content = (TextView) findViewById(R.id.content);
        descri= (TextView) findViewById(R.id.description);
        reviewModList = new ArrayList<>();
        reviewAdapter = new ReviewAdapter(this,reviewModList);
        recyclerView = (RecyclerView) findViewById(R.id.reviewRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(reviewAdapter);


    }

    private void getReviewData() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = new JSONArray(response.getString("results"));
                    for (int i=0; i < jsonArray.length();i++)
                    {
                        ReviewMod review = new ReviewMod();
                        JSONObject parObj = jsonArray.getJSONObject(i);
                        review.setAuthor(parObj.getString("author"));
                        review.setDescription(parObj.getString("content"));
                        review.setReviewLink(parObj.getString("url"));
                        reviewModList.add(review);
                    }

                    reviewAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("TAG",error.toString());
            }

        });
        queue.add(jsonObjectRequest);
    }

}
