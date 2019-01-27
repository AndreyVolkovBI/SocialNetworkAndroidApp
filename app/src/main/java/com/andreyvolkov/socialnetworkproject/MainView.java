package com.andreyvolkov.socialnetworkproject;

import com.andreyvolkov.socialnetworkproject.Retrofit.Entity.PlaceholderPosts;
import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

public interface MainView extends MvpView {
    void initRecyclerView(ArrayList<PlaceholderPosts> posts);
    void showMessage(String message);
}
