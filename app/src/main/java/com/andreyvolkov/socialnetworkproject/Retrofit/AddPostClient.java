package com.andreyvolkov.socialnetworkproject.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AddPostClient {
    @POST("/posts/")
    Call<AddPost> postToAdd(@Query("body") String body);
}
