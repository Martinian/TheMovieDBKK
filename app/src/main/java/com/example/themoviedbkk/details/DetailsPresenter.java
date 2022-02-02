package com.example.themoviedbkk.details;

import android.app.Activity;
import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.themoviedbkk.DetailsActivity;
import com.example.themoviedbkk.R;
import com.example.themoviedbkk.database.DatabaseFilmsMemory;
import com.example.themoviedbkk.database.GetStateImageViewFroDb;
import com.example.themoviedbkk.database.UpdateRecordDatabase;
import com.squareup.picasso.Picasso;

public class DetailsPresenter {

    private final ImageView imageFilmDa;
    private final Application application;
    private final DatabaseFilmsMemory provideDatabase;
    private final ImageView imageLikeDa;
    private DetailsView detailsView;
    private int filmId;
    private String releaseDate;
    private double voteAverageId;
    private String title;
    private String overview;
    private String posterPath;


    public DetailsPresenter(DetailsView detailsView, int filmId, String releaseDate, double voteAverageId, String title, String overview, String posterPath,
                            ImageView imageFilmDa, Application application, LinearLayout dummyImageLikeDa, ImageView imageLikeDa,
                            DatabaseFilmsMemory provideDatabase) {

        this.application = application;
        this.detailsView = detailsView;
        this.filmId = filmId;
        this.releaseDate = releaseDate;
        this.voteAverageId = voteAverageId;
        this.title = title;
        this.overview = overview;
        this.posterPath = posterPath;
        this.imageFilmDa = imageFilmDa;
        this.provideDatabase = provideDatabase;
        this.imageLikeDa = imageLikeDa;

        dummyImageLikeDa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new UpdateRecordDatabase(provideDatabase, imageLikeDa, filmId);
            }
        });

        imageLikeDa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new UpdateRecordDatabase(provideDatabase, imageLikeDa, filmId);
            }
        });;

    }

    public void displayAndConnect() {

        new GetStateImageViewFroDb(provideDatabase, imageLikeDa, filmId);

        detailsView.showFilmId(filmId);
        detailsView.showReleaseDate(releaseDate);
        detailsView.showVoteAverageId(voteAverageId);
        detailsView.showTitle(title);
        detailsView.showOverview(overview);

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.plus02);
        Glide.with((Activity) detailsView).load(application.getResources().getString(R.string.image_film_url) + posterPath).apply(options).into(imageFilmDa);

    }
}
