package com.example.api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PersonalDataAdapter extends RecyclerView.Adapter<PersonalDataAdapter.MyViewHolder>{

    Context context;
    List<PersonalDataModel> personalDataList;

    public PersonalDataAdapter(Context context, List<PersonalDataModel> personalDataList) {
        this.context = context;
        this.personalDataList = personalDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.personal_data_sample_view, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        PersonalDataModel data = personalDataList.get(position);

        holder.name.setText(data.getName());
        holder.fName.setText(data.getFather_name());
        holder.mName.setText(data.getMother_name());
        holder.address.setText(data.getAddress());

    }

    @Override
    public int getItemCount() {
        return personalDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, fName, mName, address;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.stdName);
            fName = itemView.findViewById(R.id.stdFName);
            mName = itemView.findViewById(R.id.stdMName);
            address = itemView.findViewById(R.id.stdAddress);
        }
    }
}
