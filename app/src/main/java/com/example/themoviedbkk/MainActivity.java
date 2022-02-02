package com.example.themoviedbkk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.themoviedbkk.database.DatabaseFilmsMemory;
import com.example.themoviedbkk.models.Result;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements MainView{

    @Inject
    Application application;
    @Inject
    DatabaseFilmsMemory provideDatabase;
    @Inject
    Retrofit retrofit;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.list_films)
    RecyclerView listFilms;

    MainPresenter presenter;
    MainAdapter mainAdapter;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        Context context = MainActivity.this;
        presenter = new MainPresenter(retrofit,this ,application,context);
        presenter.startConnect();
    }


    @Override
    public void getFilms(List<Result> mResults) {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        listFilms.setLayoutManager(mLayoutManager);
        mainAdapter = new MainAdapter(mResults,this);
        listFilms.setItemAnimator(new DefaultItemAnimator());
        listFilms.setAdapter(mainAdapter);

    }

    @Override
    public void afterClickOnItemIageView(ImageView imageLike, int filmId) {

        presenter.changeStateImageLike(provideDatabase, imageLike, filmId);
    }

    @Override
    public void setStateImageView(ImageView imageLike, int filmId) {

        presenter.setStateImageViewFromDb(provideDatabase, imageLike, filmId);
    }

    @Override
    public void afterClickOnItem(int position) {

        presenter.launchDetailsActivity(position, mainAdapter.getResults());
    }

    @Override
    public void onBackPressed() {

        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);

    }


}