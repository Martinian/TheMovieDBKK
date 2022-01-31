package com.example.themoviedbkk.details;

import com.example.themoviedbkk.DetailsActivity;

public class DetailsPresenter {

    private DetailsView detailsView;
    private int filmId;
    private String releaseDate;
    private double voteAverageId;
    private String title;
    private String overview;
    private String posterPath;


    public DetailsPresenter(DetailsView detailsView, int filmId, String releaseDate, double voteAverageId, String title, String overview, String posterPath) {

        this.detailsView = detailsView;
        this.filmId = filmId;
        this.releaseDate = releaseDate;
        this.voteAverageId = voteAverageId;
        this.title = title;
        this.overview = overview;
        this.posterPath = posterPath;

    }
}
