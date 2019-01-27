package com.andreyvolkov.socialnetworkproject.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.andreyvolkov.socialnetworkproject.CommentsView;
import com.andreyvolkov.socialnetworkproject.Presenter.CommentsPresenter;
import com.andreyvolkov.socialnetworkproject.R;
import com.andreyvolkov.socialnetworkproject.Retrofit.Entity.PlaceholderComments;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;

public class CommentsActivity extends MvpAppCompatActivity implements CommentsView {

    @InjectPresenter
    public CommentsPresenter commentsPresenter;
    private RecyclerView recyclerView;
    private Integer postId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        Bundle arguments = getIntent().getExtras();
        postId = (Integer) arguments.get("postId");
        commentsPresenter.sendRequest(postId);
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.comments_recycler_view);
    }

    @Override
    public void initRecyclerView(ArrayList<PlaceholderComments> comments) {
        System.out.print(comments);
        System.out.print(comments);
    }

    @Override
    public void showMessage(String message) {

    }
}
