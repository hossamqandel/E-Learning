package com.android.edraak.Adapter.Multi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.edraak.Model.ChatModel;
import com.android.edraak.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    private final int sMESSAGE_TYPE_IN_SENDER = 0;
    private final int sMESSAGE_TYPE_IN_RECEIVER = 1;

    String currentUserId = FirebaseAuth.getInstance().getUid();

    private List<ChatModel> list;

    public void setList(List<ChatModel> list) {
        this.list = list;
        notifyItemChanged(list.size()-1);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == sMESSAGE_TYPE_IN_SENDER){
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_me, parent, false));
        }
        else{
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_other, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.message.setText(list.get(position).getMessage());
            holder.name.setText(list.get(position).getUserName());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView message;
        TextView name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.text_gchat_message);
            name = itemView.findViewById(R.id.text_user_name);

        }
    }


    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getSenderId().equals(currentUserId)) {
            return sMESSAGE_TYPE_IN_SENDER;
        } else {
            return sMESSAGE_TYPE_IN_RECEIVER;
        }
    }
}