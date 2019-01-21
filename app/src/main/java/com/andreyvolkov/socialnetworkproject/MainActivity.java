package com.andreyvolkov.socialnetworkproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private String BASE_URL = "http://jsonplaceholder.typicode.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        PlaceholderClient client = retrofit.create(PlaceholderClient.class);
        Call<List<PlaceholderRepo>> call = client.reposForPlaceholder();

        call.enqueue(new Callback<List<PlaceholderRepo>>() {
            @Override
            public void onResponse(Call<List<PlaceholderRepo>> call, Response<List<PlaceholderRepo>> response) {
                List<PlaceholderRepo> repos = response.body();

                String name = repos.get(0).getBody();
                System.out.print("Hello!");
            }

            @Override
            public void onFailure(Call<List<PlaceholderRepo>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });
    }
}
