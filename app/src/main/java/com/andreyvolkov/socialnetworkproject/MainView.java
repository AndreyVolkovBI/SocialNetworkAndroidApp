package com.andreyvolkov.socialnetworkproject;

import com.andreyvolkov.socialnetworkproject.Retrofit.PlaceholderPosts;
import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

public interface MainView extends MvpView {
    void initRecyclerView(ArrayList<PlaceholderPosts> posts);
    void showMessage(String message);
}
