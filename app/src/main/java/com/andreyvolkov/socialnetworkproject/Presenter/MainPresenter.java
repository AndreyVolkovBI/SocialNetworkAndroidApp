package com.andreyvolkov.socialnetworkproject.Presenter;

import com.andreyvolkov.socialnetworkproject.Callbacks.MainActivityCallback;
import com.andreyvolkov.socialnetworkproject.Dagger.DaggerMainPresenterComponent;
import com.andreyvolkov.socialnetworkproject.Dagger.MainPresenterComponent;
import com.andreyvolkov.socialnetworkproject.MainView;
import com.andreyvolkov.socialnetworkproject.Model.APIClient;
import com.andreyvolkov.socialnetworkproject.Retrofit.Entity.PlaceholderPosts;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> implements MainActivityCallback {

    @Inject
    APIClient client;

    public MainPresenter() {
        MainPresenterComponent component = DaggerMainPresenterComponent.create();
        component.inject(this);
        client.setAPIClient(this);
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
