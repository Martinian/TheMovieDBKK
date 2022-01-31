package com.example.themoviedbkk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.os.Bundle;

import com.example.themoviedbkk.models.Result;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements MainView{

    @Inject
    Application application;
    @Inject
    Retrofit retrofit;

    @BindView(R.id.list_films)
    RecyclerView symbolsList;

    MainPresenter presenter;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        presenter = new MainPresenter(retrofit,this ,application);
        presenter.startConnect();
    }


    @Override
    public void getFilms(List<Result> mResults) {

    }
}