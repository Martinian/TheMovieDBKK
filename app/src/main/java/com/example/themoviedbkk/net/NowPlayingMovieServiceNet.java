package com.example.themoviedbkk.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface NowPlayingMovieServiceNet {

    @GET("/3/movie/now_playing")
    @Headers("Content-Type: application/json")
    Call<ResponseBody> getPlayingMovieData(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") String page);
}
