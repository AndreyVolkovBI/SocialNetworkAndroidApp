package com.andreyvolkov.socialnetworkproject.Recyclers;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andreyvolkov.socialnetworkproject.Activities.AddPostActivity;
import com.andreyvolkov.socialnetworkproject.Retrofit.PlaceholderPosts;
import com.andreyvolkov.socialnetworkproject.R;

import java.util.ArrayList;

public class RecyclerNewsFeedAdapter extends RecyclerView.Adapter<RecyclerNewsFeedAdapter.ViewHolder> {

    private Context context;
    private ArrayList<PlaceholderPosts> placeholderPosts = new ArrayList<>();

    private final int TYPE_ITEM_BUTTON = 0;
    private final int TYPE_ITEM_POST = 1;

    public RecyclerNewsFeedAdapter(Context context, ArrayList<PlaceholderPosts> placeholderPosts) {
        this.placeholderPosts = placeholderPosts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        ViewHolder holder;

        switch (viewType){
            case TYPE_ITEM_BUTTON:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_post_button, parent, false);
                holder = new ViewHolder(view);
                break;
            case TYPE_ITEM_POST:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
                holder = new ViewHolder(view);
                break;
            default:
                holder = null;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        switch (getItemViewType(position)) {
            case TYPE_ITEM_POST:
                String userId = context.getResources().getString(R.string.post_item_user_id) + " "
                        + String.valueOf(placeholderPosts.get(position).getId());
                holder.postItemUserId.setText(userId);
                holder.postItemTitle.setText(placeholderPosts.get(position).getTitle());
                holder.postItemContent.setText(placeholderPosts.get(position).getBody());
                break;
            case TYPE_ITEM_BUTTON:
                holder.addPostButtonLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(context, AddPostActivity.class);
                        context.startActivity(i);
                    }
                });
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_ITEM_BUTTON;
        else
            return TYPE_ITEM_POST;
    }

    @Override
    public int getItemCount() {
        return placeholderPosts.size();
    }

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView postItemUserId;
    TextView postItemTitle;
    TextView postItemContent;
    RelativeLayout addPostButtonLayout;

    public ViewHolder(View itemView) {
        super(itemView);
        addPostButtonLayout = itemView.findViewById(R.id.add_post_button_layout);
        postItemUserId = itemView.findViewById(R.id.post_item_user_id);
        postItemTitle = itemView.findViewById(R.id.post_item_title);
        postItemContent = itemView.findViewById(R.id.post_item_content);
    }
}
}
