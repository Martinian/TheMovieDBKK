package com.example.themoviedbkk.modules;

import com.example.themoviedbkk.DetailsActivity;
import com.example.themoviedbkk.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();
    @ContributesAndroidInjector
    abstract DetailsActivity bindDetailsActivity();
}
