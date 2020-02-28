package com.digel.dhanie_sub3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.digel.dhanie_sub3.Movie.DataMovie;
import com.digel.dhanie_sub3.TvShow.DataTvShow;

import java.util.Objects;


public class ActivityDetail extends AppCompatActivity {
    public static final String KEY_MOVIES = "movies";
    public static final String KEY_TV = "tv";
    String type = "";

    ImageView img_detail, img_info, bt_back;
    TextView txt_title, txt_rate, txt_release, txt_description, textView, textView2;
    DataTvShow tvData;
    DataMovie movie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_movie);

        bt_back = findViewById(R.id.bt_back);
        img_detail = findViewById(R.id.img_detail);
        img_info = findViewById(R.id.img_info);
        txt_title = findViewById(R.id.txt_title);
        txt_rate = findViewById(R.id.txt_rate);
        txt_release = findViewById(R.id.txt_release);
        txt_description = findViewById(R.id.txt_description);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        Intent it = getIntent();
        type = it.getStringExtra("type");
        if (Objects.equals(type, "movie")) {
            movie = it.getParcelableExtra(KEY_MOVIES);
            if (movie != null)
                setData(movie.getTitle(), movie.getVote_average(), movie.getRelease_date(), movie.getOverview(), movie.getPoster_path(), movie.getBackdrop_path());
        } else {
            tvData = it.getParcelableExtra(KEY_TV);
            if (tvData != null)
                setData(tvData.getTitle(), tvData.getVote_average(), tvData.getRelease_date(), tvData.getOverview(), tvData.getPoster_path(), tvData.getBackdrop_path());
        }
        textView.setText(getResources().getString(R.string.lbl_release_date));
        textView2.setText(getResources().getString(R.string.lbl_overview));
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setData(String title, String rate, String realease, String desc, String poster, String bg) {
        Glide.with(this).load(bg).into(img_detail);
        Glide.with(this).load(poster).into(img_info);
        txt_title.setText(title);
        txt_rate.setText(rate);
        txt_release.setText(realease);
        txt_description.setText(desc);
    }

}
