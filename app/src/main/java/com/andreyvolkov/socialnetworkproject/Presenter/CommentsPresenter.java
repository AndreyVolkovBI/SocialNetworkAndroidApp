package com.andreyvolkov.socialnetworkproject.Presenter;

import com.andreyvolkov.socialnetworkproject.AddPostView;
import com.andreyvolkov.socialnetworkproject.Callbacks.AddPostActivityCallback;
import com.andreyvolkov.socialnetworkproject.Callbacks.CommentsCallback;
import com.andreyvolkov.socialnetworkproject.CommentsView;
import com.andreyvolkov.socialnetworkproject.Dagger.CommentsPresenterComponent;
import com.andreyvolkov.socialnetworkproject.Dagger.DaggerAddPostPresenterComponent;
import com.andreyvolkov.socialnetworkproject.Dagger.DaggerCommentsPresenterComponent;
import com.andreyvolkov.socialnetworkproject.Model.APIClient;
import com.andreyvolkov.socialnetworkproject.Retrofit.Entity.PlaceholderComments;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

@InjectViewState
public class CommentsPresenter extends MvpPresenter<CommentsView> implements CommentsCallback {

    @Inject
    APIClient client;

    public CommentsPresenter() {
        CommentsPresenterComponent component = DaggerCommentsPresenterComponent.create();
        component.inject(this);
        client.setAPIClient(this);
    }

    public void sendRequest(Integer postId) {
        client.getCommentsByPostId(postId);
    }

    @Override
    public void onSuccess(ArrayList<PlaceholderComments> comments) {
        getViewState().initRecyclerView(comments);

    }

    @Override
    public void onError(String message) {
        getViewState().showMessage(message);
    }
}
