package com.digel.dhanie_sub3.TvShow;

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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.digel.dhanie_sub3.ActivityDetail;
import com.digel.dhanie_sub3.R;
import com.digel.dhanie_sub3.Rest.Response.TvResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TabTvShowFragment extends Fragment {

    private RecyclerView tvList;
    private ProgressBar cpb;
    private TvViewModel model;
    private ArrayList<DataTvShow> articleArrayList = new ArrayList<>();

    public TabTvShowFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tv_show_fragment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cpb = view.findViewById(R.id.cpb);
        cpb.setVisibility(View.VISIBLE);
        tvList = view.findViewById(R.id.rv_tv_id);
        setModel();
        Objects.requireNonNull(model.getTvLive()).observe(this, new Observer<TvResponse>() {
            @Override
            public void onChanged(TvResponse newsResponse) {
                List<DataTvShow> newsArticles = newsResponse.getTv();
                articleArrayList.addAll(newsArticles);
                tvList.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()), DividerItemDecoration.VERTICAL));
                tvList.setLayoutManager(new LinearLayoutManager(getActivity()));
                tvList.setAdapter(new TvAdapter(getActivity(), articleArrayList, new TvAdapter.OnItemClickListenerTv() {
                    @Override
                    public void onItemClick(DataTvShow item) {
                        Intent it = new Intent(getActivity(), ActivityDetail.class);
                        it.putExtra(ActivityDetail.KEY_TV, item);
                        it.putExtra("type", "tv");
                        startActivity(it);
                    }
                }));

                tvList.setHasFixedSize(true);
                cpb.setVisibility(View.GONE);
            }
        });
    }

    private void setModel() {
        if (model == null) {
            model = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(TvViewModel.class);
            model.context = this.getContext();
        }
        if (model.getTvLive() != null && model.getTvLive().hasActiveObservers()) {
            model.getTvLive().removeObservers(this);
        }
    }

    @Override
    public void onDestroy() {
        model.Cancel();
        super.onDestroy();
    }
}
