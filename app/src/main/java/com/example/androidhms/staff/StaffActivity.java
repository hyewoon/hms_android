package com.example.androidhms.staff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ActivityStaffBinding;
import com.example.androidhms.staff.outpatient.OutpatientActivity;
import com.example.androidhms.staff.lookup.LookupActivity;
import com.example.androidhms.staff.messenger.MessengerActivity;
import com.example.androidhms.staff.schedule.ScheduleActivity;
import com.example.androidhms.staff.vo.StaffVO;
import com.example.androidhms.staff.ward.DoctorWardActivity;
import com.example.androidhms.staff.ward.NurseWardActivity;

public class StaffActivity extends AppCompatActivity {

    private ActivityStaffBinding bind;
    private StaffVO staff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityStaffBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        Intent intent = getIntent();
        staff = (StaffVO) intent.getSerializableExtra("staff");

        bind.tvName.setText(staff.getName());
        bind.clLookup.setOnClickListener(onMenuClick());
        bind.clOutpatient.setOnClickListener(onMenuClick());
        bind.clWard.setOnClickListener(onMenuClick());
        bind.clSchedule.setOnClickListener(onMenuClick());
        bind.clMessanger.setOnClickListener(onMenuClick());
    }

    private View.OnClickListener onMenuClick() {
        return v -> {
            Intent intent = null;
            if (v.getId() == R.id.cl_lookup) {
                intent = new Intent(StaffActivity.this, LookupActivity.class);
            } else if (v.getId() == R.id.cl_outpatient) {
                intent = new Intent(StaffActivity.this, OutpatientActivity.class);
            } else if (v.getId() == R.id.cl_ward) {
                // 진료과 간호사, 병동 간호사 구분
                if (staff.getDepartment_id() < 150) {
                    intent = new Intent(StaffActivity.this, DoctorWardActivity.class);
                } else {
                    intent = new Intent(StaffActivity.this, NurseWardActivity.class);
                }
            } else if (v.getId() == R.id.cl_messanger) {
                intent = new Intent(StaffActivity.this, MessengerActivity.class);
            } else if (v.getId() == R.id.cl_schedule) {
                intent = new Intent(StaffActivity.this, ScheduleActivity.class);
            }
            intent.putExtra("staff", staff);
            startActivity(intent);
        };
    }
}