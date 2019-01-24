package com.andreyvolkov.socialnetworkproject.Retrofit;

import retrofit2.Call;
import retrofit2.http.POST;

public interface AddPostClient {
    @POST("/posts")
    Call<AddPost> postToAdd();
}
