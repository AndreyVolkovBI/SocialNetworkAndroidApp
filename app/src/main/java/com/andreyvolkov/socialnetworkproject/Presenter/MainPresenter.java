package com.andreyvolkov.socialnetworkproject.Presenter;

import com.andreyvolkov.socialnetworkproject.Callbacks.MainActivityCallback;
import com.andreyvolkov.socialnetworkproject.MainView;
import com.andreyvolkov.socialnetworkproject.Model.APIClient;
import com.andreyvolkov.socialnetworkproject.Retrofit.PlaceholderPosts;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> implements MainActivityCallback {

    APIClient client;

    public MainPresenter() {
         client = new APIClient(this);
         client.getPosts();
    }

    @Override
    public void onSuccess(ArrayList<PlaceholderPosts> posts) {
        getViewState().initRecyclerView(posts);
    }

    @Override
    public void onError(String message) {
        getViewState().showMessage(message);
    }


}
