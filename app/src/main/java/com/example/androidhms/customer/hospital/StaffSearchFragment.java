package com.example.androidhms.customer.hospital;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhms.databinding.FragmentCustomerStaffSearchBinding;
import com.example.androidhms.staff.vo.StaffVO;
import com.example.conn.ApiClient;
import com.example.conn.RetrofitMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class StaffSearchFragment extends Fragment {
    private FragmentCustomerStaffSearchBinding bind;
    private StaffListAdapter staffListAdapter;
    private ArrayList<StaffVO> staff;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentCustomerStaffSearchBinding.inflate(inflater, container, false);

        ApiClient.setBASEURL("http://192.168.0.116/hms/");





        bind.btnSearch.setOnClickListener(v -> {
            Log.d("로그", "검색어 : " +  bind.etSearchWord.getText().toString());
            new RetrofitMethod().setParams("searchWord",  bind.etSearchWord.getText().toString())
                    .sendPost("staffsearch_by_name.cu", (isResult, data) -> {
                        staff = new Gson().fromJson(data, new TypeToken<ArrayList<StaffVO>>(){}.getType());
                        if (staff.size() == 0) {
                            Toast.makeText(getContext(), "검색결과가 없습니다", Toast.LENGTH_SHORT);
                        }
                        staffListAdapter = new StaffListAdapter(inflater, getContext(), staff);
                        bind.rcvStaffSearch.setAdapter(staffListAdapter);
                        bind.rcvStaffSearch.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));



                    });
        });


        return bind.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind = null;
    }

}