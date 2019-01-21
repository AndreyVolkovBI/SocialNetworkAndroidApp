package com.andreyvolkov.socialnetworkproject.Recyclers;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andreyvolkov.socialnetworkproject.PlaceholderPosts;
import com.andreyvolkov.socialnetworkproject.R;

import java.util.ArrayList;

public class RecyclerNewsFeedAdapter extends RecyclerView.Adapter<RecyclerNewsFeedAdapter.ViewHolder> {

    private Context context;
    private ArrayList<PlaceholderPosts> placeholderPosts = new ArrayList<>();

    private boolean isFirst = true;

    public RecyclerNewsFeedAdapter(Context context, ArrayList<PlaceholderPosts> placeholderPosts) {
        this.placeholderPosts = placeholderPosts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        ViewHolder holder;

        if (isFirst) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_post_button, parent, false);
            holder = new ViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
            holder = new ViewHolder(view);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (!isFirst){
            holder.postItemUserId.setText(placeholderPosts.get(position).getId().toString());
            holder.postItemTitle.setText(placeholderPosts.get(position).getTitle());
            holder.postItemContent.setText(placeholderPosts.get(position).getBody());
        } else {
            isFirst = false;
        }
    }


    @Override
    public int getItemCount() {
        return placeholderPosts.size();
    }

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView postItemUserId;
    TextView postItemTitle;
    TextView postItemContent;

    public ViewHolder(View itemView) {
        super(itemView);
        postItemUserId = itemView.findViewById(R.id.post_item_user_id);
        postItemTitle = itemView.findViewById(R.id.post_item_title);
        postItemContent = itemView.findViewById(R.id.post_item_content);
    }
}
}
