package com.application.amrudesh.moviedb_udacity.Data;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.amrudesh.moviedb_udacity.Model.Movie;
import com.application.amrudesh.moviedb_udacity.Model.Youtube;
import com.application.amrudesh.moviedb_udacity.R;
import com.application.amrudesh.moviedb_udacity.Util.Constants;

import org.w3c.dom.Text;

import java.util.List;

public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeAdapter.ViewHolder>
    {
     List<Youtube>youtubeList;
     Context context;

     public YoutubeAdapter(Context context,List<Youtube> youtubeList) {
        this.youtubeList = youtubeList;
        this.context = context;
    }


    @Override
    public YoutubeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.youtube,viewGroup,false);
        return new ViewHolder(context,view);
    }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            Youtube youtube = youtubeList.get(i);
            String links= youtube.getLinks();
            String names =youtube.getNames();

            viewHolder.names.setText(youtube.getNames());


        }
        @Override
    public int getItemCount() {
       return youtubeList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView names;
        ImageView imageView;
        public ViewHolder(Context ctx,View itemView) {
            super(itemView);
            context = ctx;
            names = (TextView) itemView.findViewById(R.id.trailerName);
            imageView=(ImageView) itemView.findViewById(R.id.youtube_image);





        }
    }
}