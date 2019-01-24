package com.andreyvolkov.socialnetworkproject.Model;

import com.andreyvolkov.socialnetworkproject.Callbacks.ViewCallback;
import com.andreyvolkov.socialnetworkproject.Retrofit.AddPost;
import com.andreyvolkov.socialnetworkproject.Retrofit.AddPostClient;
import com.andreyvolkov.socialnetworkproject.Retrofit.PlaceholderClient;
import com.andreyvolkov.socialnetworkproject.Retrofit.PlaceholderPosts;
import com.andreyvolkov.socialnetworkproject.Presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private ViewCallback callback;

    public APIClient(MainPresenter mainPresenter) {
        this.callback = mainPresenter;
    }

    private String BASE_URL = "http://jsonplaceholder.typicode.com/";

    private Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private Retrofit retrofit = builder.build();

    public void getPosts(){

        PlaceholderClient client = retrofit.create(PlaceholderClient.class);
        Call<List<PlaceholderPosts>> call = client.postsForPlaceholder();

        call.enqueue(new Callback<List<PlaceholderPosts>>() {
            @Override
            public void onResponse(Call<List<PlaceholderPosts>> call, Response<List<PlaceholderPosts>> response) {
                List<PlaceholderPosts> posts = response.body();
                assert posts != null;
                posts.add(0, new PlaceholderPosts());
                callback.returnValue((ArrayList<PlaceholderPosts>) posts);
            }

            @Override
            public void onFailure(Call<List<PlaceholderPosts>> call, Throwable t) {
                callback.showMessage("Произошла ошибка при запросе к серверу!");
            }
        });
    }

    private void postPost() {
        AddPostClient client = retrofit.create(AddPostClient.class);
        Call<AddPost> call = client.postToAdd();

        call.enqueue(new Callback<AddPost>() {
            @Override
            public void onResponse(Call<AddPost> call, Response<AddPost> response) {
                callback.showMessage("Пост успешно отправлен!");
            }

            @Override
            public void onFailure(Call<AddPost> call, Throwable t) {
                callback.showMessage("Пост не был отправлен на сервер!");
            }
        });

    }
}
