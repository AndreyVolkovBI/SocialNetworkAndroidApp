package com.andreyvolkov.socialnetworkproject.Dagger;

import com.andreyvolkov.socialnetworkproject.Activities.MainActivity;
import com.andreyvolkov.socialnetworkproject.Presenter.AddPostPresenter;
import com.andreyvolkov.socialnetworkproject.Presenter.MainPresenter;

import dagger.Component;

@Component
public interface MainPresenterComponent {
    void inject(MainPresenter mainPresenter);
}
