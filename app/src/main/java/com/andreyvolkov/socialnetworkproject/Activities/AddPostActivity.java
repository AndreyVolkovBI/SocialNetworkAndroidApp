package com.andreyvolkov.socialnetworkproject.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andreyvolkov.socialnetworkproject.AddPostView;
import com.andreyvolkov.socialnetworkproject.Presenter.AddPostPresenter;
import com.andreyvolkov.socialnetworkproject.R;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class AddPostActivity extends MvpAppCompatActivity implements AddPostView {

    @InjectPresenter
    public AddPostPresenter addPostPresenter;

    private Button addPostSendButton;
    private EditText addPostId;
    private EditText addPostTitle;
    private EditText addPostContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        init();
    }

    private void init() {
        addPostSendButton = findViewById(R.id.add_post_send_button);
        addPostId = findViewById(R.id.add_post_id);
        addPostTitle = findViewById(R.id.add_post_title);
        addPostContent = findViewById(R.id.add_post_content);

        addPostSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = addPostId.getText().toString();
                String title = addPostTitle.getText().toString();
                String content = addPostContent.getText().toString();
                sendRequest(id, title, content);
            }
        });
    }


    @Override
    public void sendRequest(String id, String title, String content) {
        addPostPresenter.sendRequest(id, title, content);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
