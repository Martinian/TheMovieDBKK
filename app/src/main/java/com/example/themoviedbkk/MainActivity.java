package com.example.themoviedbkk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.themoviedbkk.database.DatabaseFilmsMemory;
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

        presenter = new MainPresenter(retrofit,this ,application);
        presenter.startConnect();
    }


    @Override
    public void getFilms(List<Result> mResults) {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        listFilms.setLayoutManager(mLayoutManager);
        mainAdapter = new MainAdapter(mResults);
        listFilms.setItemAnimator(new DefaultItemAnimator());
        listFilms.setAdapter(mainAdapter);
        listFilms.addOnItemTouchListener(new ScanRecyclerTouchListener(getApplicationContext(), listFilms, new ScanRecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Log.d("CLIKNAL - Movies", "  kliknął !");

                presenter.launchDetailsActivity(position, mainAdapter.getResults());

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    @Override
    public void onBackPressed() {

        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);

    }
}