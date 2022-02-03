package com.example.themoviedbkk.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface SearchMovieServiceNet {

    @GET("/3/search/movie")
    @Headers("Content-Type: application/json")
    Call<ResponseBody> getPlayingMovieDataQuery(@Query("api_key") String apiKey, @Query("language") String language,
                                           @Query("query") String query, @Query("page") String page);
}
