package com.digel.dhanie_sub3.TvShow;

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

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvHolder> {
    private Context mContext;
    private List<DataTvShow> tvData;

    public interface OnItemClickListenerTv {
        void onItemClick(DataTvShow item);
    }

    private OnItemClickListenerTv listenerTv;

    TvAdapter(Context context, List<DataTvShow> tvData, OnItemClickListenerTv onItemClickListenerTv) {
        this.mContext = context;
        this.tvData = tvData;
        this.listenerTv = onItemClickListenerTv;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rc_item_tv, parent, false);
        return new TvHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvHolder holder, int position) {
        holder.onBind(tvData.get(position), listenerTv );
    }

    @Override
    public int getItemCount() {
        return tvData.size();
    }

    class TvHolder extends RecyclerView.ViewHolder {

        final View mView;

        TextView title;
        TextView overview;
        TextView vote_average;
        TextView release_date;
        ImageView poster_path;

        TvHolder(View view) {
            super(view);
            mView = view;
            title = view.findViewById(R.id.txt_title);
            overview = view.findViewById(R.id.txt_description);
            vote_average = view.findViewById(R.id.txt_rate);
            release_date = view.findViewById(R.id.txt_release);
            poster_path = view.findViewById(R.id.img_info);
        }

        void onBind(final DataTvShow dataTv, final OnItemClickListenerTv listener) {
            title.setText(dataTv.getTitle());
            overview.setText(dataTv.getOverview());
            vote_average.setText(dataTv.getVote_average());
            release_date.setText(dataTv.getRelease_date());
            if (dataTv.getPoster_path() != null && !dataTv.getPoster_path().isEmpty())
                Glide.with(mContext).load(dataTv.getPoster_path()).into(poster_path);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(dataTv);
                }
            });
        }
    }


}
