package com.application.amrudesh.moviedb_udacity.Data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.application.amrudesh.moviedb_udacity.Model.ReviewMod;
import com.application.amrudesh.moviedb_udacity.R;

import org.w3c.dom.Text;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    Context context;
    List<ReviewMod> reviewModList;


    public ReviewAdapter(Context context,List<ReviewMod> reviewModList) {
        this.reviewModList = reviewModList;
        this.context = context;
    }


    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.reviewrecycler,viewGroup,false);
        return new ViewHolder(context,view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ViewHolder viewHolder, int i) {
        ReviewMod reviewMod = reviewModList.get(i);
        String author =reviewMod.getAuthor();
        String desc = reviewMod.getDescription();
        String link = reviewMod.getReviewLink();

        viewHolder.auth.setText(reviewMod.getAuthor());
        viewHolder.descri.setText(reviewMod.getDescription());



    }

    @Override
    public int getItemCount() {
        return reviewModList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView auth,descri;

        public ViewHolder(final Context ctx,View itemView) {
            super(itemView);
            context = ctx;
            auth =(TextView) itemView.findViewById(R.id.content);
            descri=(TextView) itemView.findViewById(R.id.description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    String links = reviewModList.get(pos).getReviewLink();
                    Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                    httpIntent.setData(Uri.parse(links));
                    ctx.startActivity(httpIntent);

                }
            });
        }
    }
}
