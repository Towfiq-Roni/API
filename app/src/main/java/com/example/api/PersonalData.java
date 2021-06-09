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

public class PersonalData extends Fragment {

    RecyclerView recyclerView;
    PersonalDataAdapter personalDataAdapter;
    CallAPI callAPI;
    List<PersonalDataModel> personalDataList;;
    View view;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personal_data, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewId);
        progressBar = view.findViewById(R.id.progressBar);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://apigenerate.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        callAPI = retrofit.create(CallAPI.class);

        Call<List<PersonalDataModel>> call = callAPI.getPersonalData();

        call.enqueue(new Callback<List<PersonalDataModel>>() {
            @Override
            public void onResponse(Call<List<PersonalDataModel>> call, Response<List<PersonalDataModel>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getContext(), "ResponseCode: "+response.code(), Toast.LENGTH_SHORT).show();
                }

                personalDataList = response.body();

                personalDataAdapter = new PersonalDataAdapter(getContext(), personalDataList);
                recyclerView.setAdapter(personalDataAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<PersonalDataModel>> call, Throwable t) {
                Toast.makeText(getContext(), "ErrorMsg: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}