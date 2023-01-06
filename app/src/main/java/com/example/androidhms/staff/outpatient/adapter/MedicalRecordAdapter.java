package com.example.androidhms.staff.outpatient.adapter;

import static android.content.ContentValues.TAG;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhms.R;
import com.example.androidhms.databinding.ItemStaffMedicalRecordBinding;
import com.example.androidhms.staff.outpatient.MedicalRecordFragment;
import com.example.androidhms.staff.vo.MedicalRecordVO;

import java.util.ArrayList;

public class MedicalRecordAdapter extends RecyclerView.Adapter<MedicalRecordAdapter.MedicalRecordViewHolder> {

    private MedicalRecordFragment fragment;
    private ArrayList<MedicalRecordVO> mrList;
    private int selectedPosition = -1;

    public MedicalRecordAdapter(MedicalRecordFragment fragment, ArrayList<MedicalRecordVO> mrList) {
        this.fragment = fragment;
        this.mrList = mrList;
    }

    @NonNull
    @Override
    public MedicalRecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MedicalRecordViewHolder(fragment.getLayoutInflater()
                .inflate(R.layout.item_staff_medical_record, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MedicalRecordViewHolder holder, int position) {
        MedicalRecordVO vo = mrList.get(position);
        holder.bind.tvDate.setText(vo.getTreatment_date());
        holder.bind.tvPatientName.setText(vo.getPatient_name());
        holder.bind.tvStaffName.setText(vo.getStaff_name());
        holder.bind.tvTreatmentName.setText(vo.getTreatment_name());
        if (position == selectedPosition) {
            holder.bind.view.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(fragment.getContext(), R.color.second_color)));
            holder.bind.tvPatientName.setTextColor(ContextCompat.getColor(fragment.getContext(), R.color.white));
            holder.bind.tvStaffName.setTextColor(ContextCompat.getColor(fragment.getContext(), R.color.white));
            holder.bind.tvTreatmentName.setTextColor(ContextCompat.getColor(fragment.getContext(), R.color.white));
            holder.bind.tvDate.setTextColor(ContextCompat.getColor(fragment.getContext(), R.color.white));
        } else {
            holder.bind.view.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(fragment.getContext(), R.color.white)));
            holder.bind.tvPatientName.setTextColor(ContextCompat.getColor(fragment.getContext(), R.color.text_color));
            holder.bind.tvStaffName.setTextColor(ContextCompat.getColor(fragment.getContext(), R.color.text_color));
            holder.bind.tvTreatmentName.setTextColor(ContextCompat.getColor(fragment.getContext(), R.color.text_color));
            holder.bind.tvDate.setTextColor(ContextCompat.getColor(fragment.getContext(), R.color.text_color));
        }
    }

    @Override
    public int getItemCount() {
        return mrList.size();
    }

    public class MedicalRecordViewHolder extends RecyclerView.ViewHolder {

        public ItemStaffMedicalRecordBinding bind;

        public MedicalRecordViewHolder(@NonNull View itemView) {
            super(itemView);
            bind = ItemStaffMedicalRecordBinding.bind(itemView);
            itemView.setOnClickListener(v -> {
                if (getAdapterPosition() != RecyclerView.NO_POSITION
                        && mrList.get(getAdapterPosition()).getAdmission().equals("N"))
                    fragment.onMedicalRecordClick(getAdapterPosition());
                    selectedPosition = getAdapterPosition();
                    notifyDataSetChanged();
            });

        }
    }

}