package com.example.themoviedbkk.details;

import android.widget.ImageView;

import com.example.themoviedbkk.database.DatabaseFilmsMemory;
import com.example.themoviedbkk.models.Result;

import java.util.List;

public interface DetailsActivityLaunch {

    void launchDetailsActivity(int position, List<Result> results);
    void changeStateImageLike(DatabaseFilmsMemory provideDatabase, ImageView imageLike, int filmId);
    void setStateImageViewFromDb(DatabaseFilmsMemory provideDatabase, ImageView imageLike, int filmId);
}
