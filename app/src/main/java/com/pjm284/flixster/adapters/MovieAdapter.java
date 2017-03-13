package com.pjm284.flixster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pjm284.flixster.R;
import com.pjm284.flixster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends
        RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> mMovies;

    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public MovieAdapter(Context context, List<Movie> movies) {
        mMovies = movies;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View movieView = inflater.inflate(R.layout.item_movie, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(movieView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Movie movie = mMovies.get(position);

        // Set item views based on your views and data model
        ImageView ivMovieImage = viewHolder.ivMovieImage;
        // clear out previous image
        ivMovieImage.setImageResource(0);

        String imagePath;
        int placeholder;
        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            imagePath = movie.getPosterPath();
            placeholder = R.drawable.placeholder_portrait;
        } else {
            imagePath = movie.getBackdropPath();
            placeholder = R.drawable.placeholder_landscape;
        }

        Picasso.with(getContext()).load(imagePath)
                .placeholder(placeholder)
                .into(ivMovieImage);

        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvOverview.setText(movie.getOverview());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivMovieImage;
        public TextView tvTitle;
        public TextView tvOverview;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            ivMovieImage = (ImageView) itemView.findViewById(R.id.ivMovieImage);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvOverview = (TextView) itemView.findViewById(R.id.tvOverview);
        }
    }
}