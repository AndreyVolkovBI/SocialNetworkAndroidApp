package com.andreyvolkov.socialnetworkproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.andreyvolkov.socialnetworkproject.MainView;
import com.andreyvolkov.socialnetworkproject.Retrofit.Entity.PlaceholderPosts;
import com.andreyvolkov.socialnetworkproject.Presenter.MainPresenter;
import com.andreyvolkov.socialnetworkproject.R;
import com.andreyvolkov.socialnetworkproject.Recyclers.RecyclerNewsFeedAdapter;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    public MainPresenter mainPresenter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.main_recycler_view);
    }

    @Override
    public void initRecyclerView(ArrayList<PlaceholderPosts> posts) {
        RecyclerNewsFeedAdapter adapter = new RecyclerNewsFeedAdapter(getApplicationContext(), this, posts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    public void startAddPostActivityFromRecyclerView() {
        Intent i = new Intent(getApplicationContext(), AddPostActivity.class);
        startActivity(i);
    }

    public void startCommentActivityFromRecyclerViewByPostId(Integer postId) {
        Intent i = new Intent(getApplicationContext(), CommentsActivity.class);
        i.putExtra("postId", postId);
        startActivity(i);
    }
}
