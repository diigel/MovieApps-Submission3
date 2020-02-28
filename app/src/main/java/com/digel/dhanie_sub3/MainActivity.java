package com.digel.dhanie_sub3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.digel.dhanie_sub3.Movie.TabMovieFragment;
import com.digel.dhanie_sub3.TvShow.TabTvShowFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floting;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    private int[] tabIcons = {
            R.drawable.ic_movie,
            R.drawable.tv_show
    };

    private int[] tabIconsActive = {
            R.drawable.ic_movie,
            R.drawable.tv_show
    };

    private int[] tabTitle = {
            R.string.tab_text_1,
            R.string.tab_text_2
    };


    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        floting = findViewById(R.id.floating);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                final int width = viewPager.getWidth();
                if (position == 0) {
                    int translationX = (int) ((-(width - floting.getWidth()) / 50f) * positionOffset);
                    floting.setTranslationX(translationX);
                    floting.setTranslationY(0);
                } else if (position == 1) {
                    int translationY = (int) (floting.getHeight() * positionOffset);
                    floting.setTranslationY(translationY);
                    floting.setTranslationX(-(width - floting.getWidth()) / 50f);
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        floting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(mIntent);
            }
        });

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            LinearLayout tab = (LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.tab_custom, null);
            ImageView tab_icon = tab.findViewById(R.id.custom_tab_imageView);
            TextView txt_icon = tab.findViewById(R.id.custom_tab_textView_count);
            if (i == 0) {
                tab_icon.setImageResource(tabIconsActive[i]);
                txt_icon.setText(tabTitle[i]);
                txt_icon.setVisibility(View.GONE);
            } else {
                tab_icon.setImageResource(tabIcons[i]);
                txt_icon.setText(tabTitle[i]);
                txt_icon.setVisibility(View.VISIBLE);
            }

            Objects.requireNonNull(tabLayout.getTabAt(i)).setCustomView(tab);
            int item = viewPager.getCurrentItem();
            viewPager.setCurrentItem(item, true);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab1) {
                int position = tab1.getPosition();
                ImageView tab_icon = Objects.requireNonNull(tab1.getCustomView()).findViewById(R.id.custom_tab_imageView);
                tab_icon.setImageResource(tabIconsActive[position]);
                TextView tab_title = Objects.requireNonNull(tab1.getCustomView()).findViewById(R.id.custom_tab_textView_count);
                tab_title.setVisibility(View.GONE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab1) {
                int position = tab1.getPosition();
                ImageView tab_icon = Objects.requireNonNull(tab1.getCustomView()).findViewById(R.id.custom_tab_imageView);
                TextView tab_title = Objects.requireNonNull(tab1.getCustomView()).findViewById(R.id.custom_tab_textView_count);
                tab_icon.setImageResource(tabIcons[position]);
                tab_title.setText(tabTitle[position]);
                tab_title.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void setupViewPager(ViewPager viewPager) {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFrag(new TabMovieFragment(), getResources().getString(R.string.tab_text_1));
        viewPagerAdapter.addFrag(new TabTvShowFragment(), getResources().getString(R.string.tab_text_2));
        viewPager.setAdapter(viewPagerAdapter);
    }
}
