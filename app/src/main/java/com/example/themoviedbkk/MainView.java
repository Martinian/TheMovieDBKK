package com.example.themoviedbkk;

import com.example.themoviedbkk.models.Films;
import com.example.themoviedbkk.models.Result;

import java.util.List;

public interface MainView {
    void getFilms(List<Result> mResults);
}
