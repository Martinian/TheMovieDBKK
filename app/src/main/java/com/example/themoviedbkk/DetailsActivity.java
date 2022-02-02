package com.example.themoviedbkk;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.themoviedbkk.database.DatabaseFilmsMemory;
import com.example.themoviedbkk.details.DetailsPresenter;
import com.example.themoviedbkk.details.DetailsView;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class DetailsActivity extends AppCompatActivity implements DetailsView,HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Inject
    Application application;
    @Inject
    DatabaseFilmsMemory provideDatabase;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.text_film_id)
    public TextView textFilmId;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.text_release_date)
    public TextView textReleaseDate;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.text_vote_average)
    public TextView textVoteAverage;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.text_title)
    public TextView textTitle;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.text_overview)
    public TextView textOverview;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.image_toolbar_like)
    public ImageView imageToolbarLike;


    DetailsPresenter detailsPresenter;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        unbinder = ButterKnife.bind(this);

        Intent intent= getIntent();

        int    filmId = intent.getIntExtra("film_id",0);
        String releaseDate = intent.getStringExtra("release_date");
        double voteAverageId = intent.getDoubleExtra("vote_average_id",0D);
        String title = intent.getStringExtra("title");
        String overview = intent.getStringExtra("overview");
        String posterPath = intent.getStringExtra("poster_path");


        detailsPresenter = new DetailsPresenter(this,filmId, releaseDate, voteAverageId, title, overview,
                                                 posterPath, imageToolbarLike, application);
        detailsPresenter.displayAndConnect();

        Log.i ("PICC_KK " ,application.getResources().getString(R.string.image_film_url)  + posterPath);
//        Picasso.get().load( application.getResources().getString(R.string.image_film_url) + posterPath).placeholder(R.drawable.ic_launcher_background).centerCrop().resize(100,100).into(imageToolbarLike);

        Glide.with(this)
                .load(application.getResources().getString(R.string.image_film_url) + posterPath)
                .override(100,100)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageToolbarLike);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public void showFilmId(int filmId) {

        this.runOnUiThread(new Runnable() {
            public void run() {

                textFilmId.setText(String.valueOf(filmId));
            }
        });

    }

    @Override
    public void showReleaseDate(String releaseDate) {

        this.runOnUiThread(new Runnable() {
            public void run() {

                textReleaseDate.setText(releaseDate);
            }
        });

    }

    @Override
    public void showVoteAverageId(double voteAverageId) {

        this.runOnUiThread(new Runnable() {
            public void run() {

                textVoteAverage.setText(String.valueOf(voteAverageId));
            }
        });

    }

    @Override
    public void showTitle(String title) {

        this.runOnUiThread(new Runnable() {
            public void run() {

                textTitle.setText(title);
            }
        });

    }

    @Override
    public void showOverview(String overview) {

        this.runOnUiThread(new Runnable() {
            public void run() {

                textOverview.setText(overview);
            }
        });

    }

}