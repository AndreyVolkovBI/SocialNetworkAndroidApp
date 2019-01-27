package com.andreyvolkov.socialnetworkproject.Callbacks;

import com.andreyvolkov.socialnetworkproject.Retrofit.PlaceholderPosts;

import java.util.ArrayList;

public interface MainActivityCallback {
    void onSuccess(ArrayList<PlaceholderPosts> posts);
    void onError(String message);
}
