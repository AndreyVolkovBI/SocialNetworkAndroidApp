package com.andreyvolkov.socialnetworkproject.Dagger;

import com.andreyvolkov.socialnetworkproject.Presenter.AddPostPresenter;

import dagger.Component;

@Component
public interface AddPostPresenterComponent {
    void inject(AddPostPresenter addPostPresenter);
}
