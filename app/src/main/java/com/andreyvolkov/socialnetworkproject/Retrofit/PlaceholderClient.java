package com.andreyvolkov.socialnetworkproject.Retrofit;

import com.andreyvolkov.socialnetworkproject.Retrofit.PlaceholderPosts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlaceholderClient {
    @GET("/posts")
    Call<List<PlaceholderPosts>> postsForPlaceholder();
}
