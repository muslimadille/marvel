package com.muslimadel2018.marvel.data;

import com.muslimadel2018.marvel.pojo.Response;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PostsInterface {
    @GET("characters")
      Call<Response> getResponse(
            @Query("ts") String ts,
            @Query("apikey") String apikey,
            @Query("hash") String hash
    );

}
