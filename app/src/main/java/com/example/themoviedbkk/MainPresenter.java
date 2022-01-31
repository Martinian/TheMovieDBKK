package com.example.themoviedbkk;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.themoviedbkk.details.DetailsActivityLaunch;
import com.example.themoviedbkk.models.Films;
import com.example.themoviedbkk.models.Result;
import com.example.themoviedbkk.net.NowPlayingMovieServiceNet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainPresenter implements DetailsActivityLaunch {

    private final Retrofit retrofit;
    private final Application application;
    private MainView view;


    public MainPresenter(Retrofit retrofit, MainView view ,Application application){

        this.retrofit = retrofit;
        this.view = view;
        this.application = application;

    }

    public void startConnect() {

        NowPlayingMovieServiceNet service = retrofit.create(NowPlayingMovieServiceNet.class);
        Call<ResponseBody> result = service.getPlayingMovieData(application.getResources().getString(R.string.api_key),
                                                                application.getResources().getString(R.string.language),
                                                                application.getResources().getString(R.string.page));

        result.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                try {
                    Gson gson = new Gson();
                    assert response.body() != null;
                    view.getFilms(gson.fromJson(response.body().string(), Films.class).getResults());

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {

            }

        });

    }

    @Override
    public void launchDetailsActivity(int position, List<Result> results) {

        Intent i_DetailsActivity = new Intent(application.getApplicationContext(), DetailsActivity.class);

        i_DetailsActivity.putExtra("film_id", results.get(position).getId());
        i_DetailsActivity.putExtra("release_date", results.get(position).getReleaseDate());
        i_DetailsActivity.putExtra("vote_average", results.get(position).getVoteAverage());
        i_DetailsActivity.putExtra("title", results.get(position).getTitle());
        i_DetailsActivity.putExtra("overview", results.get(position).getOverview());
        i_DetailsActivity.putExtra("poster_path", results.get(position).getPosterPath());

        application.getApplicationContext().startActivity(i_DetailsActivity);

    }
}