package com.example.themoviedbkk.modules;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.themoviedbkk.R;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

    @Inject
    Application application;


    @Singleton
    @Provides
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(application.getCacheDir(), cacheSize);
    }



    @Provides
    @Singleton
    Retrofit provideRetrofit(Cache cache) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @NonNull
                    @Override
                    public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
                        Request original = chain.request();
                        Request request = original.newBuilder()
                                .header("Content-Type", "application/json")
                                .build();

                        return chain.proceed(request);
                    }
                })
                .cache(cache)
                .build();

        return  new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

}
