package com.andreyvolkov.socialnetworkproject;

import com.arellomobile.mvp.MvpView;

public interface AddPostView extends MvpView {
    void sendRequest(String id, String title, String content);
    void showMessage(String message);
}
