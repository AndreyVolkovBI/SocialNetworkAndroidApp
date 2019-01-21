package com.andreyvolkov.socialnetworkproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.andreyvolkov.socialnetworkproject.Recyclers.RecyclerNewsFeedAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private String BASE_URL = "http://jsonplaceholder.typicode.com/";

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initRetrofit();
    }

    private void init() {
        recyclerView = findViewById(R.id.main_recycler_view);
    }

    private void initRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        PlaceholderClient client = retrofit.create(PlaceholderClient.class);
        Call<List<PlaceholderPosts>> call = client.reposForPlaceholder();

        call.enqueue(new Callback<List<PlaceholderPosts>>() {
            @Override
            public void onResponse(Call<List<PlaceholderPosts>> call, Response<List<PlaceholderPosts>> response) {
                List<PlaceholderPosts> posts = response.body();
                initRecyclerView((ArrayList<PlaceholderPosts>) posts);
            }

            @Override
            public void onFailure(Call<List<PlaceholderPosts>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initRecyclerView(ArrayList<PlaceholderPosts> posts) {
        posts.add(0, new PlaceholderPosts());
        RecyclerNewsFeedAdapter adapter = new RecyclerNewsFeedAdapter(getApplicationContext(), posts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}
