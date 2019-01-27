package com.andreyvolkov.socialnetworkproject.Recyclers;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andreyvolkov.socialnetworkproject.Activities.CommentsActivity;
import com.andreyvolkov.socialnetworkproject.Activities.MainActivity;
import com.andreyvolkov.socialnetworkproject.R;
import com.andreyvolkov.socialnetworkproject.Retrofit.Entity.PlaceholderComments;
import com.andreyvolkov.socialnetworkproject.Retrofit.Entity.PlaceholderPosts;

import java.util.ArrayList;

public class RecyclerCommentsAdapter extends RecyclerView.Adapter<RecyclerCommentsAdapter.ViewHolder> {

    private Context context;
    private CommentsActivity commentsActivity;
    private ArrayList<PlaceholderComments> placeholderComments;

    public RecyclerCommentsAdapter(Context context, CommentsActivity commentsActivity, ArrayList<PlaceholderComments> placeholderComments) {
        this.placeholderComments = placeholderComments;
        this.context = context;
        this.commentsActivity = commentsActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final String userId = context.getResources().getString(R.string.recycler_comments_text) + " "
                + String.valueOf(placeholderComments.get(position).getId());

        holder.commentItemPostId.setText(userId);
        holder.commentItemName.setText(placeholderComments.get(position).getName());
        holder.commentItemEmail.setText(placeholderComments.get(position).getEmail());
        holder.commentItemBody.setText(placeholderComments.get(position).getBody());


        holder.commentItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mainActivity.startCommentActivityFromRecyclerViewByPostId(placeholderPosts.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return placeholderComments.size();
    }

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView commentItemPostId;
    TextView commentItemName;
    TextView commentItemEmail;
    TextView commentItemBody;

    RelativeLayout commentItemLayout;

    public ViewHolder(View itemView) {
        super(itemView);
        commentItemPostId = itemView.findViewById(R.id.comment_item_post_id);
        commentItemName = itemView.findViewById(R.id.comment_item_name);
        commentItemEmail = itemView.findViewById(R.id.comment_item_email);
        commentItemBody = itemView.findViewById(R.id.comment_item_body);

        commentItemLayout = itemView.findViewById(R.id.comment_item_layout);
    }
}
}
