package com.andreyvolkov.socialnetworkproject.Model;

import com.andreyvolkov.socialnetworkproject.Callbacks.AddPostActivityCallback;
import com.andreyvolkov.socialnetworkproject.Callbacks.MainActivityCallback;
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

    private MainActivityCallback mainCallback;
    private AddPostActivityCallback addCallback;
    private String BASE_URL = "http://jsonplaceholder.typicode.com/";

    private Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private Retrofit retrofit = builder.build();

    public APIClient(MainActivityCallback callback) {
        this.mainCallback = callback;
    }

    public APIClient(AddPostActivityCallback callback){
        this.addCallback = callback;
    }

    public void getPosts(){

        PlaceholderClient client = retrofit.create(PlaceholderClient.class);
        Call<List<PlaceholderPosts>> call = client.postsForPlaceholder();

        call.enqueue(new Callback<List<PlaceholderPosts>>() {
            @Override
            public void onResponse(Call<List<PlaceholderPosts>> call, Response<List<PlaceholderPosts>> response) {
                List<PlaceholderPosts> posts = response.body();
                assert posts != null;
                posts.add(0, new PlaceholderPosts());
                mainCallback.onSuccess((ArrayList<PlaceholderPosts>) posts);
            }

            @Override
            public void onFailure(Call<List<PlaceholderPosts>> call, Throwable t) {
                mainCallback.onError("Произошла ошибка при запросе к серверу!");
            }
        });
    }

    public void postPost(String id, String title, String content) {
        AddPostClient client = retrofit.create(AddPostClient.class);
        String body = "title='" + title + "', body='" + content + "', userId=" + id + "}";
        Call<AddPost> call = client.postToAdd(body);

        call.enqueue(new Callback<AddPost>() {
            @Override
            public void onResponse(Call<AddPost> call, Response<AddPost> response) {
                AddPost post = response.body();
                addCallback.onSuccess("Пост успешно отправлен!");
            }

            @Override
            public void onFailure(Call<AddPost> call, Throwable t) {
                addCallback.onError("Пост не был отправлен на сервер!");
            }
        });
    }
}
