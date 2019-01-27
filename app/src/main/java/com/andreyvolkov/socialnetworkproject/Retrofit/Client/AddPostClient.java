package com.andreyvolkov.socialnetworkproject.Retrofit.Client;

import com.andreyvolkov.socialnetworkproject.Retrofit.Entity.AddPost;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AddPostClient {
    @POST("/posts/")
    Call<AddPost> postToAdd(@Query("body") String body);
}
