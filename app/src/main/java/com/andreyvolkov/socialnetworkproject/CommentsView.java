package com.andreyvolkov.socialnetworkproject;

import com.andreyvolkov.socialnetworkproject.Retrofit.Entity.PlaceholderComments;
import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

public interface CommentsView extends MvpView {
    void initRecyclerView(ArrayList<PlaceholderComments> comments);
    void showMessage(String message);
}
