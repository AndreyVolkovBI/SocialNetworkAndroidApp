package com.andreyvolkov.socialnetworkproject.Recyclers;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andreyvolkov.socialnetworkproject.Activities.MainActivity;
import com.andreyvolkov.socialnetworkproject.Retrofit.Entity.PlaceholderPosts;
import com.andreyvolkov.socialnetworkproject.R;

import java.util.ArrayList;

public class RecyclerNewsFeedAdapter extends RecyclerView.Adapter<RecyclerNewsFeedAdapter.ViewHolder> {

    private Context context;
    private MainActivity mainActivity;
    private ArrayList<PlaceholderPosts> placeholderPosts;

    private final int TYPE_ITEM_BUTTON = 0;
    private final int TYPE_ITEM_POST = 1;

    public RecyclerNewsFeedAdapter(Context context, MainActivity mainActivity, ArrayList<PlaceholderPosts> placeholderPosts) {
        this.placeholderPosts = placeholderPosts;
        this.context = context;
        this.mainActivity = mainActivity;
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
                final String userId = context.getResources().getString(R.string.recycler_news_feed_post_item_user_id) + " "
                        + String.valueOf(placeholderPosts.get(position).getId());
                holder.postItemUserId.setText(userId);
                holder.postItemTitle.setText(placeholderPosts.get(position).getTitle());
                holder.postItemContent.setText(placeholderPosts.get(position).getBody());
                holder.postItemLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mainActivity.startCommentActivityFromRecyclerViewByPostId(placeholderPosts.get(position).getId());
                    }
                });
                break;
            case TYPE_ITEM_BUTTON:
                holder.addPostButtonLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mainActivity.startAddPostActivityFromRecyclerView();
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

    RelativeLayout addPostButtonLayout;

    TextView postItemUserId;
    TextView postItemTitle;
    TextView postItemContent;
    RelativeLayout postItemLayout;

    public ViewHolder(View itemView) {
        super(itemView);
        addPostButtonLayout = itemView.findViewById(R.id.add_post_button_layout);

        postItemUserId = itemView.findViewById(R.id.post_item_user_id);
        postItemTitle = itemView.findViewById(R.id.post_item_title);
        postItemContent = itemView.findViewById(R.id.post_item_content);
        postItemLayout = itemView.findViewById(R.id.post_item_layout);
    }
}
}
