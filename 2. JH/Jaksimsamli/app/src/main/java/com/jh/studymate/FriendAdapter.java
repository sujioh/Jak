package com.jh.studymate;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {

    List<Friend> mFriend;
    String stEmail;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvEmail;
        public ImageView ivUser;
        public Button btnChat;

        public ViewHolder(View itemView) {
            super(itemView);
            tvEmail = (TextView) itemView.findViewById(R.id.tvEmail);
            ivUser = (ImageView)itemView.findViewById(R.id.ivUser);
            btnChat = (Button)itemView.findViewById(R.id.btnChat);
        }
    }


    public FriendAdapter(List<Friend> mFriend, Context context) {
        this.mFriend = mFriend;
        this.context = context;

    }

    @Override
    public FriendAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_friend, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.tvEmail.setText(mFriend.get(position).getEmail());


        String stPhoto = mFriend.get(position).getPhoto();
        if(TextUtils.isEmpty(stPhoto)) {

            Picasso.with(context)
                    .load(R.drawable.beb2)
                    .fit()
                    .centerInside()
                    .into(holder.ivUser);

        } else {
            Picasso.with(context)
                    .load(stPhoto)
                    .fit()
                    .centerInside()
                    .into(holder.ivUser);
        }


        holder.btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String stFriendId = mFriend.get(position).getKey();
                Intent in = new Intent(context, ChatActivity.class);
                in.putExtra("friendUid", stFriendId);
                context.startActivity(in);

            }
        });

    }


    @Override
    public int getItemCount() {
        return mFriend.size();
    }
}

