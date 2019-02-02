package com.andreyvolkov.socialnetworkproject.Dagger;

import com.andreyvolkov.socialnetworkproject.Presenter.AddPostPresenter;
import com.andreyvolkov.socialnetworkproject.Presenter.CommentsPresenter;

import dagger.Component;

@Component
public interface CommentsPresenterComponent {
    void inject(CommentsPresenter commentsPresenter);
}
