package com.andreyvolkov.socialnetworkproject.Callbacks;

import com.andreyvolkov.socialnetworkproject.Retrofit.PlaceholderPosts;

import java.util.ArrayList;

public interface AddPostActivityCallback {
    void onSuccess(String message);
    void onError(String message);
}
