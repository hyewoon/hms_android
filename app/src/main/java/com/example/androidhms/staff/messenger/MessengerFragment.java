package com.example.androidhms.staff.messenger;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidhms.R;
import com.example.androidhms.databinding.FragmentMessengerBinding;
import com.example.androidhms.databinding.FragmentMessengerStaffBinding;
import com.example.androidhms.staff.vo.StaffVO;
import com.example.androidhms.util.HmsFirebase;

public class MessengerFragment extends Fragment {

    private FragmentMessengerBinding bind;
    private HmsFirebase fb;
    private Bundle bundle;
    private StaffVO staff;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentMessengerBinding.inflate(inflater, container, false);
        fb = new HmsFirebase(this.getContext(), firebaseHandler());
        bundle = getArguments();
        staff = (StaffVO) bundle.getSerializable("staff");

        bind.tvName.setText(staff.getName());

        return bind.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind = null;
    }

    private Handler firebaseHandler() {
        return new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {

            }
        };
    }
}