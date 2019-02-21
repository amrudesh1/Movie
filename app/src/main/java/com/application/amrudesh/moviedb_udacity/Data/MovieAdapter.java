package com.application.amrudesh.moviedb_udacity.Data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.amrudesh.moviedb_udacity.Activities.Details;
import com.application.amrudesh.moviedb_udacity.Model.Movie;
import com.application.amrudesh.moviedb_udacity.R;
import com.application.amrudesh.moviedb_udacity.Util.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private List<Movie> movieList;
    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        movieList = movies;
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_disp,viewGroup,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder viewHolder, int i) {

        Movie movie = movieList.get(i);
        String posterLink = Constants.image_url + movie.getImage();

        viewHolder.title.setText(movie.getTitle());
        viewHolder.release.setText(movie.getReleaseDate());

        Picasso.get()
                .load(posterLink)
                .placeholder(android.R.drawable.ic_delete)
                .into(viewHolder.poster);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    public void setMovies(List<Movie> movies)
    {
        movieList.clear();
        this.movieList = movies;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView poster;
        TextView release;


        public ViewHolder(View itemView, final Context ctx) {
            super(itemView);
            context = ctx;

            title = (TextView) itemView.findViewById(R.id.movie_title);
            poster = (ImageView) itemView.findViewById(R.id.image_id);
            release = (TextView) itemView.findViewById(R.id.releaseDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Movie movie = movieList.get(getAdapterPosition());
                    Intent intent = new Intent(ctx, Details.class);
                    intent.putExtra("movie",movie);
                    ctx.startActivity(intent);

                }
            });
        }


        @Override
        public void onClick(View v) {

        }
    }
}
