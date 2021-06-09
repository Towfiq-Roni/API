package com.example.api;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AcademicData extends Fragment {

    RecyclerView recyclerView;
    AcademicDataAdapter academicDataAdapter;
    List<AcademicDataModel> academicDataList;
    CallAPI callAPI;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_academic_data, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewId2);
        progressBar = view.findViewById(R.id.progressBarId);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://apigenerate.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        callAPI = retrofit.create(CallAPI.class);

        Call<List<AcademicDataModel>> call = callAPI.getAcademicData();

        call.enqueue(new Callback<List<AcademicDataModel>>() {
            @Override
            public void onResponse(Call<List<AcademicDataModel>> call, Response<List<AcademicDataModel>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getContext(), "ResponseCode: "+response.code(), Toast.LENGTH_SHORT).show();
                }

                academicDataList = response.body();

                academicDataAdapter = new AcademicDataAdapter(getContext(), academicDataList);
                recyclerView.setAdapter(academicDataAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<AcademicDataModel>> call, Throwable t) {
                Toast.makeText(getContext(), "ErrorMsg: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}