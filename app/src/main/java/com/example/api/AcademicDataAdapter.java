package com.example.api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AcademicDataAdapter extends RecyclerView.Adapter<AcademicDataAdapter.MyViewHolderTwo> {

    Context context;
    List<AcademicDataModel> academicDataList;

    public AcademicDataAdapter(Context context, List<AcademicDataModel> academicDataList) {
        this.context = context;
        this.academicDataList = academicDataList;
    }

    @NonNull
    @Override
    public AcademicDataAdapter.MyViewHolderTwo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.academic_data_sample_view, parent, false);

        return new AcademicDataAdapter.MyViewHolderTwo(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AcademicDataAdapter.MyViewHolderTwo holder, int position) {

        AcademicDataModel academicDataModel = academicDataList.get(position);

        holder.name.setText(academicDataModel.getName());
        holder.studentId.setText(academicDataModel.getStudent_id());
        holder.cgpa.setText(academicDataModel.getCgpa());
    }

    @Override
    public int getItemCount() {
        return academicDataList.size();
    }

    class MyViewHolderTwo extends RecyclerView.ViewHolder{

        TextView name, studentId, cgpa;

        public MyViewHolderTwo(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.stdPName);
            studentId = itemView.findViewById(R.id.stdId);
            cgpa = itemView.findViewById(R.id.stdCgpa);
        }
    }

}
