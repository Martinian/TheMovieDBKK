package com.example.themoviedbkk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.themoviedbkk.details.DetailsPresenter;
import com.example.themoviedbkk.details.DetailsView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class DetailsActivity extends AppCompatActivity implements DetailsView,HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

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
        String poster_path = intent.getStringExtra("poster_path");


        detailsPresenter = new DetailsPresenter(this,filmId, releaseDate, voteAverageId, title, overview, poster_path);


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



            }
        });

    }

    @Override
    public void showReleaseDate(String releaseDate) {

        this.runOnUiThread(new Runnable() {
            public void run() {



            }
        });

    }

    @Override
    public void showVoteAverageId(double voteAverageId) {

        this.runOnUiThread(new Runnable() {
            public void run() {



            }
        });

    }

    @Override
    public void showTitle(String title) {

        this.runOnUiThread(new Runnable() {
            public void run() {



            }
        });

    }

    @Override
    public void showOverview(String overview) {

        this.runOnUiThread(new Runnable() {
            public void run() {



            }
        });

    }

    @Override
    public void showPosterPath(String posterPath) {

        this.runOnUiThread(new Runnable() {
            public void run() {



            }
        });

    }
}