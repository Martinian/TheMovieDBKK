package com.example.themoviedbkk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.os.Bundle;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Inject
    Application application;
    @Inject
    Retrofit retrofit;

    @BindView(R.id.list_films)
    RecyclerView symbolsList;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
    }
}