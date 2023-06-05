package com.example.moviedetails;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailMoviePage extends AppCompatActivity {

    private TextView tvDetailTitle;
    private TextView tvDetailDescription;
    private ImageView imgDetailMovie;

    private TextView tvPopularity;

    private TextView tvAdult;

    private TextView tvVote_count;

    private TextView tvVote_average;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailmovie);

        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        tvDetailDescription = findViewById(R.id.tvDetailDescription);
        imgDetailMovie = findViewById(R.id.imgDetailMovie);
        tvPopularity = findViewById(R.id.tvPopularity);
        tvAdult = findViewById(R.id.tvAdult);
        tvVote_average = findViewById(R.id.tvVote_avarage);
        tvVote_count = findViewById(R.id.tvVote_count);

        Intent intent = getIntent();
        if (intent.hasExtra("myteam")){
            ModelMovie modelMovie = intent.getParcelableExtra("myteam");
            tvDetailTitle.setText(modelMovie.getOriginal_title());
            tvDetailDescription.setText(modelMovie.getOverview());
            tvPopularity.setText(modelMovie.getPopularity());
            tvAdult.setText(modelMovie.getAdult());
            tvVote_count.setText(modelMovie.getVote_count());
            tvVote_average.setText((modelMovie.getVote_average()));
            String backdropUrl = "https://image.tmdb.org/t/p/w500" + modelMovie.getBackdrop_path();
            Glide.with(DetailMoviePage.this).load(backdropUrl).into(imgDetailMovie);
        }
    }
}