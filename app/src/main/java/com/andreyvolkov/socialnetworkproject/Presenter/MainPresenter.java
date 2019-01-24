package com.andreyvolkov.socialnetworkproject.Presenter;

import com.andreyvolkov.socialnetworkproject.Callbacks.ViewCallback;
import com.andreyvolkov.socialnetworkproject.MainView;
import com.andreyvolkov.socialnetworkproject.Model.APIClient;
import com.andreyvolkov.socialnetworkproject.Retrofit.PlaceholderPosts;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> implements ViewCallback {

    APIClient client;

    public MainPresenter() {
         client = new APIClient(this);
         client.getPosts();
    }

    @Override
    public void returnValue(ArrayList<PlaceholderPosts> posts) {
        getViewState().initRecyclerView(posts);
    }

    @Override
    public void showMessage(String message) {
        getViewState().showMessage(message);
    }


}
