package com.andreyvolkov.socialnetworkproject.Callbacks;

import com.andreyvolkov.socialnetworkproject.Retrofit.Entity.PlaceholderComments;

import java.util.ArrayList;

public interface CommentsCallback {
    void onSuccess(ArrayList<PlaceholderComments> comments);
    void onError(String message);
}
