package com.example.androidhms.staff.messenger.adapter;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhms.R;
import com.example.androidhms.databinding.RvChatBinding;
import com.example.androidhms.databinding.RvMychatBinding;
import com.example.androidhms.staff.messenger.ChatActivity;
import com.example.androidhms.staff.vo.ChatVO;
import com.example.androidhms.util.Util;

import java.util.ArrayList;

public class ChatRoomAdapter extends RecyclerView.Adapter<ChatRoomAdapter.ChatRoomViewHolder> {

    private ChatActivity activity;
    private ArrayList<ChatVO> chatList;
    private String myId;

    public ChatRoomAdapter(ChatActivity activity, ArrayList<ChatVO> chatList, String myId) {
        this.activity = activity;
        this.chatList = chatList;
        this.myId = myId;
        chatList.remove(0);
    }

    @NonNull
    @Override
    public ChatRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) return new ChatRoomViewHolder(activity.getLayoutInflater().inflate(R.layout.rv_chat, parent, false));
        else return new ChatRoomViewHolder(activity.getLayoutInflater().inflate(R.layout.rv_mychat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatRoomViewHolder holder, int position) {
        ChatVO vo = chatList.get(position);
        String time = Util.getChatTime(vo.getTime());
        if (vo.getId().equals(myId)) {
            if (position != 0 && vo.getName().equals(chatList.get(position - 1).getName())) {
                holder.myBind.tvName.setVisibility(View.GONE);
            } else holder.myBind.tvName.setText(vo.getName());
            holder.myBind.tvContent.setText(vo.getContent());
            holder.myBind.tvTime.setText(time);
        } else {
            if (position != 0 && vo.getName().equals(chatList.get(position - 1).getName())) {
                holder.bind.tvName.setVisibility(View.GONE);
            } else holder.bind.tvName.setText(vo.getName());
            holder.bind.tvContent.setText(vo.getContent());
            holder.bind.tvTime.setText(time);
        }
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (chatList.get(position).getId().equals(myId)) return 1;
        else return 0;
    }

    public class ChatRoomViewHolder extends RecyclerView.ViewHolder {

        public RvChatBinding bind;
        public RvMychatBinding myBind;

        public ChatRoomViewHolder(@NonNull View itemView) {
            super(itemView);
            bind = RvChatBinding.bind(itemView);
            myBind = RvMychatBinding.bind(itemView);
        }
    }

}
