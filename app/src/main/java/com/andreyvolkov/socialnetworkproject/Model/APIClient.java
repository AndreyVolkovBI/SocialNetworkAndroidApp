package com.andreyvolkov.socialnetworkproject.Model;

import com.andreyvolkov.socialnetworkproject.Callbacks.AddPostActivityCallback;
import com.andreyvolkov.socialnetworkproject.Callbacks.CommentsCallback;
import com.andreyvolkov.socialnetworkproject.Callbacks.MainActivityCallback;
import com.andreyvolkov.socialnetworkproject.Retrofit.Client.PlaceholderCommentsClient;
import com.andreyvolkov.socialnetworkproject.Retrofit.Entity.AddPost;
import com.andreyvolkov.socialnetworkproject.Retrofit.Client.AddPostClient;
import com.andreyvolkov.socialnetworkproject.Retrofit.Client.PlaceholderPostsClient;
import com.andreyvolkov.socialnetworkproject.Retrofit.Entity.PlaceholderComments;
import com.andreyvolkov.socialnetworkproject.Retrofit.Entity.PlaceholderPosts;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private MainActivityCallback mainCallback;
    private AddPostActivityCallback addCallback;
    private CommentsCallback commentsCallback;

    @Inject
    public APIClient() {}

    private String BASE_URL = "http://jsonplaceholder.typicode.com/";

    private Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private Retrofit retrofit = builder.build();

    public void setAPIClient(MainActivityCallback callback) {
        this.mainCallback = callback;
    }

    public void setAPIClient(AddPostActivityCallback callback){
        this.addCallback = callback;
    }

    public void setAPIClient(CommentsCallback callback) {
        this.commentsCallback = callback;
    }

    public void getPosts(){

        PlaceholderPostsClient client = retrofit.create(PlaceholderPostsClient.class);
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
                addCallback.onSuccess("Пост успешно отправлен!");
            }

            @Override
            public void onFailure(Call<AddPost> call, Throwable t) {
                addCallback.onError("Пост не был отправлен на сервер!");
            }
        });
    }

    public void getCommentsByPostId(final Integer postId){

        PlaceholderCommentsClient client = retrofit.create(PlaceholderCommentsClient.class);
        Call<List<PlaceholderComments>> call = client.commentsForPlaceholder();

        call.enqueue(new Callback<List<PlaceholderComments>>() {
            @Override
            public void onResponse(Call<List<PlaceholderComments>> call, Response<List<PlaceholderComments>> response) {
                List<PlaceholderComments> comments = response.body();
                commentsCallback.onSuccess(getListOfCommentsByPostId(comments, postId));
            }

            @Override
            public void onFailure(Call<List<PlaceholderComments>> call, Throwable t) {
                commentsCallback.onError("Произошла ошибка при запросе к серверу!");
            }
        });

    }

    private ArrayList<PlaceholderComments> getListOfCommentsByPostId
            (List<PlaceholderComments> comments, Integer postId) {

        ArrayList<PlaceholderComments> placeholderComments = new ArrayList<>();
        for(int i = 0; i < comments.size(); i++) {
            if (comments.get(i).getPostId().equals(postId)) {
                placeholderComments.add(comments.get(i));
            }
        }

        return placeholderComments;
    }
}
