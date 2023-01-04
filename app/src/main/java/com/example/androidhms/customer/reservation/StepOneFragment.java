package com.example.androidhms.customer.reservation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.androidhms.databinding.FragmentCustomerStepOneBinding;


public class StepOneFragment extends Fragment {
    FragmentCustomerStepOneBinding bind;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentCustomerStepOneBinding.inflate(inflater, container, false);


        ReservationActivity reservationActivity = new ReservationActivity();



        return bind.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind = null;
    }

}