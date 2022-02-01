package com.example.themoviedbkk.details;

import android.widget.ImageView;

import com.example.themoviedbkk.DetailsActivity;
import com.squareup.picasso.Picasso;

public class DetailsPresenter {

    private final ImageView imageToolbarLike;
    private DetailsView detailsView;
    private int filmId;
    private String releaseDate;
    private double voteAverageId;
    private String title;
    private String overview;
    private String posterPath;


    public DetailsPresenter(DetailsView detailsView, int filmId, String releaseDate, double voteAverageId, String title, String overview, String posterPath, 
                            ImageView imageToolbarLike) {

        this.detailsView = detailsView;
        this.filmId = filmId;
        this.releaseDate = releaseDate;
        this.voteAverageId = voteAverageId;
        this.title = title;
        this.overview = overview;
        this.posterPath = posterPath;
        this.imageToolbarLike = imageToolbarLike;

    }

    public void displayAndConnect() {

        detailsView.showFilmId(filmId);
        detailsView.showReleaseDate(releaseDate);
        detailsView.showVoteAverageId(voteAverageId);
        detailsView.showTitle(title);
        detailsView.showOverview(overview);



    }
}
