package com.digel.dhanie_sub3.Movie;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.digel.dhanie_sub3.ActivityDetail;
import com.digel.dhanie_sub3.R;
import com.digel.dhanie_sub3.Rest.Response.MovieResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TabMovieFragment extends Fragment {

    private RecyclerView moviesList;
    private ProgressBar cpb;
    private MovieViewModel model;
    private ArrayList<DataMovie> articleArrayList = new ArrayList<>();
    public TabMovieFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.movie_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cpb = view.findViewById(R.id.cpb);
        cpb.setVisibility(View.VISIBLE);
        moviesList = view.findViewById(R.id.rc_movie);
        setModel();
        Objects.requireNonNull(model.getMovieLive()).observe(this, new Observer<MovieResponse>() {
            @Override
            public void onChanged(MovieResponse newsResponse) {
                List<DataMovie> newsArticles = newsResponse.getMovies();
                articleArrayList.addAll(newsArticles);
                moviesList.setLayoutManager(new LinearLayoutManager(getActivity()));
                moviesList.setAdapter(new MovieAdapter(getActivity(), articleArrayList, new MovieAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(DataMovie item) {
                        Intent it = new Intent(getActivity(), ActivityDetail.class);
                        it.putExtra(ActivityDetail.KEY_MOVIES, item);
                        it.putExtra("data", "movie");
                        it.putExtra("type","movie");
                        startActivity(it);
                    }
                }));
                moviesList.setHasFixedSize(true);
                cpb.setVisibility(View.GONE);
            }
        });

    }

    private void setModel () {
        if (model == null) {
            model = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MovieViewModel.class);
            model.context = this.getContext();
        }
        if (model.getMovieLive() != null && model.getMovieLive().hasActiveObservers()) {
            model.getMovieLive().removeObservers(this);
        }
    }

    @Override
    public void onDestroyView() {
        model.Cancel();
        super.onDestroyView();
    }
}
