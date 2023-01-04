package com.example.androidhms.util;

import android.content.Context;
import android.os.Handler;

import androidx.annotation.NonNull;

import com.example.androidhms.staff.vo.ChatRoomVO;
import com.example.androidhms.staff.vo.ChatVO;
import com.example.androidhms.staff.vo.StaffVO;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HmsFirebase {

    public static final int GET_CHATROOM_SUCCESS = 1;
    public static final int GET_CHAT_SUCCESS = 2;
    public static final int GET_CHATROOM_LIST_SUCCESS = 3;
    private static final String RB_URL = "https://hmsmessenger-3a156-default-rtdb.asia-southeast1.firebasedatabase.app/";
    private final DatabaseReference dbRef;
    private final Handler handler;
    private ValueEventListener getChatListener, getChatRoomListener;

    public HmsFirebase(Context context, Handler handler) {
        FirebaseApp.initializeApp(context);
        FirebaseDatabase fbDb = FirebaseDatabase.getInstance(RB_URL);
        dbRef = fbDb.getReference();
        this.handler = handler;
    }

    public void getChatRoom(ArrayList<StaffVO> staffList) {
        dbRef.child("chatRoom").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String key;
                if (staffList.get(0).getStaff_id() < staffList.get(1).getStaff_id()) {
                    key = staffList.get(0).getStaff_id() + "--" + staffList.get(1).getStaff_id();
                } else key = staffList.get(1).getStaff_id() + "--" + staffList.get(0).getStaff_id();
                dbRef.child("chatRoom").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (!snapshot.exists()) {
                            HashMap<String, Object> map = new HashMap<>();
                            ArrayList<ChatVO> chatList = new ArrayList<>();
                            chatList.add(new ChatVO("0", "chatStart", "chatStart"));
                            map.put("member", staffList);
                            map.put("chat", chatList);
                            dbRef.child("chatRoom").child(key).setValue(map)
                                    .addOnSuccessListener(unused -> handler.sendMessage(handler.obtainMessage(GET_CHATROOM_SUCCESS, null)));
                        } else {
                            handler.sendMessage(handler.obtainMessage(GET_CHATROOM_SUCCESS, key));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

    public void sendChat(String key, ChatVO vo) {
        dbRef.child("chatRoom").child(key).child("chat").push().setValue(vo);
    }

    public void getChat(StaffVO staff, String key) {
        getChatListener = GetChatListener(staff, key);
        dbRef.child("chatRoom").child(key).child("chat").addValueEventListener(getChatListener);
    }

    public void getChatRoom(int id) {
        getChatRoomListener = GetChatRoomListener(id);
        dbRef.child("chatRoom").addValueEventListener(getChatRoomListener);
    }

    public void removeChat(String key) {
        dbRef.child("chatRoom").child(key).child("chat").removeEventListener(getChatListener);
    }

    public void removeGetChatRoom() {
        dbRef.child("chatRoom").removeEventListener(getChatRoomListener);
    }

    private ValueEventListener GetChatListener(StaffVO staff, String key) {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ChatVO> chatList = new ArrayList<>();
                for (DataSnapshot child : snapshot.getChildren()) {
                    chatList.add(
                            new ChatVO(child.child("id").getValue(String.class),
                                    child.child("name").getValue(String.class),
                                    child.child("content").getValue(String.class),
                                    child.child("time").getValue(String.class)
                            ));
                }
                handler.sendMessage(handler.obtainMessage(GET_CHAT_SUCCESS, chatList));
                dbRef.child("chatRoom").child(key).child("member").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot data : snapshot.getChildren()) {
                            if (staff.getStaff_id() == data.child("staff_id").getValue(Long.class)) {
                                staff.setLastChatCheckTime();
                                dbRef.child("chatRoom").child(key).child("member").child(data.getKey()).setValue(staff);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        };
    }

    private ValueEventListener GetChatRoomListener(int id) {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ChatRoomVO> chatRoomList = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    for (DataSnapshot member : data.child("member").getChildren()) {
                        if (id == member.child("staff_id").getValue(Long.class)) {
                            chatRoomList.add()
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
    }

}
