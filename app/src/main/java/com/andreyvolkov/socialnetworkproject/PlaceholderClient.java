package com.andreyvolkov.socialnetworkproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlaceholderClient {

    @GET("/posts")
    Call<List<PlaceholderPosts>> reposForPlaceholder();

}
