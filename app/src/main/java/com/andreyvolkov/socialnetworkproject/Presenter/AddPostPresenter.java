package com.andreyvolkov.socialnetworkproject.Presenter;

import android.view.View;

import com.andreyvolkov.socialnetworkproject.AddPostView;
import com.andreyvolkov.socialnetworkproject.Callbacks.AddPostActivityCallback;
import com.andreyvolkov.socialnetworkproject.Model.APIClient;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class AddPostPresenter extends MvpPresenter<AddPostView> implements AddPostActivityCallback {

    APIClient client;

    public AddPostPresenter() {
        this.client = new APIClient(this);
    }

    public void sendRequest(String id, String title, String content) {
        client.postPost(id, title, content);
    }

    @Override
    public void onSuccess(String message) {
        getViewState().showMessage(message);
    }

    @Override
    public void onError(String message) {
        getViewState().showMessage(message);
    }
}
