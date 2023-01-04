package com.example.androidhms.staff.messenger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityMessengerBinding;
import com.example.androidhms.staff.vo.StaffVO;
import com.example.androidhms.util.ActivityUtil;

public class MessengerActivity extends AppCompatActivity {

    private ActivityMessengerBinding bind;
    private MessengerFragment messengerFragment;
    private MessengerStaffFragment messengerStaffFragment;
    private ActivityUtil util;
    private StaffVO staff;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMessengerBinding.inflate(getLayoutInflater());
        bind.toolbar.ivLeft.setOnClickListener((v) -> finish());
        setContentView(bind.getRoot());
        Intent intent = getIntent();
        staff = (StaffVO) intent.getSerializableExtra("staff");

        bundle = new Bundle();
        bundle.putSerializable("staff", staff);
        util = new ActivityUtil(this);
        messengerStaffFragment = new MessengerStaffFragment();
        messengerStaffFragment.setArguments(bundle);

        util.addFragment(bind.flContainer.getId(), messengerStaffFragment);
        util.showFragment(messengerStaffFragment);

        bind.ivMessenger.setOnClickListener(onBnvClick());
        bind.ivMessengerStaff.setOnClickListener(onBnvClick());
    }

    private View.OnClickListener onBnvClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.iv_messenger_staff) {
                    if (messengerFragment != null) util.hideFragment(messengerFragment);
                    util.showFragment(messengerStaffFragment);
                    bind.ivMessengerStaff.setColorFilter(
                            ContextCompat.getColor(MessengerActivity.this, R.color.text_color));
                    bind.ivMessenger.setColorFilter(
                            ContextCompat.getColor(MessengerActivity.this, R.color.gray));
                } else if (v.getId() == R.id.iv_messenger) {
                    if (messengerFragment == null) {
                        messengerFragment = new MessengerFragment();
                        messengerFragment.setArguments(bundle);
                        util.addFragment(bind.flContainer.getId(), messengerFragment);
                    }
                    util.hideFragment(messengerStaffFragment);
                    util.showFragment(messengerFragment);
                    bind.ivMessenger.setColorFilter(
                            ContextCompat.getColor(MessengerActivity.this, R.color.text_color));
                    bind.ivMessengerStaff.setColorFilter(
                            ContextCompat.getColor(MessengerActivity.this, R.color.gray));
                }
            }
        };
    }
}