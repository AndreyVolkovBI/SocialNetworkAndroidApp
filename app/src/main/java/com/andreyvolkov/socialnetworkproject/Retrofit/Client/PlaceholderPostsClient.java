package com.andreyvolkov.socialnetworkproject.Retrofit.Client;

import com.andreyvolkov.socialnetworkproject.Retrofit.Entity.PlaceholderPosts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlaceholderPostsClient {
    @GET("/posts")
    Call<List<PlaceholderPosts>> postsForPlaceholder();
}
