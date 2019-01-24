package com.andreyvolkov.socialnetworkproject.Callbacks;

import com.andreyvolkov.socialnetworkproject.Retrofit.PlaceholderPosts;

import java.util.ArrayList;

public interface ViewCallback {
    void returnValue(ArrayList<PlaceholderPosts> posts);
    void showMessage(String message);
}
