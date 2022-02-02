package com.example.themoviedbkk;

import android.widget.ImageView;

import com.example.themoviedbkk.models.Films;
import com.example.themoviedbkk.models.Result;

import java.util.List;

public interface MainView {
    void getFilms(List<Result> mResults);
    void afterClickOnItem(int position);
    void afterClickOnItemIageView(ImageView imageLike, int position);
    void setStateImageView(ImageView imageLike);
}
