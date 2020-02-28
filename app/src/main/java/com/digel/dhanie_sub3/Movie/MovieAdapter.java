package com.digel.dhanie_sub3.Movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.digel.dhanie_sub3.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private Context mContext;
    private List<DataMovie> movieData;

    public interface OnItemClickListener {
        void onItemClick(DataMovie item);
    }

    private final OnItemClickListener listener;

    MovieAdapter(Context context, List<DataMovie> movieData, OnItemClickListener listener) {
        this.mContext = context;
        this.movieData = movieData;
        this.listener = listener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rc_item_movie, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        holder.onBind(movieData.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return movieData.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView overview;
        TextView vote_average;
        TextView release_date;
        ImageView poster_path;
        ImageView bacdrp_path;


        MovieHolder(View view) {
            super(view);
            title = view.findViewById(R.id.txt_title);
            overview = view.findViewById(R.id.txt_description);
            vote_average = view.findViewById(R.id.txt_rate);
            release_date = view.findViewById(R.id.txt_release);
            poster_path = view.findViewById(R.id.img_info);
            bacdrp_path = view.findViewById(R.id.img_detail);
        }

        void onBind(final DataMovie dataMovie, final OnItemClickListener listener) {
            title.setText(dataMovie.getTitle());
            overview.setText(dataMovie.getOverview());
            vote_average.setText(dataMovie.getVote_average());
            release_date.setText(dataMovie.getRelease_date());
            if (dataMovie.getPoster_path() != null && !dataMovie.getPoster_path().isEmpty())
                Glide.with(mContext).load(dataMovie.getPoster_path()).into(poster_path);
            if (dataMovie.getBackdrop_path() != null && !dataMovie.getBackdrop_path().isEmpty())
                Glide.with(mContext).load(dataMovie.getBackdrop_path()).into(bacdrp_path);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(dataMovie);
                }
            });

        }
    }


}
