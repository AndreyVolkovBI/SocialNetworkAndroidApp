package com.andreyvolkov.socialnetworkproject.Presenter;

import com.andreyvolkov.socialnetworkproject.AddPostView;
import com.andreyvolkov.socialnetworkproject.Callbacks.AddPostActivityCallback;
import com.andreyvolkov.socialnetworkproject.Dagger.AddPostPresenterComponent;
import com.andreyvolkov.socialnetworkproject.Dagger.DaggerAddPostPresenterComponent;
import com.andreyvolkov.socialnetworkproject.Model.APIClient;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

@InjectViewState
public class AddPostPresenter extends MvpPresenter<AddPostView> implements AddPostActivityCallback {

    @Inject
    APIClient client;

    public AddPostPresenter() {
        AddPostPresenterComponent component = DaggerAddPostPresenterComponent.create();
        component.inject(this);
        client.setAPIClient(this);
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
