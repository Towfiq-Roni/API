package com.example.api;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CallAPI {

    @GET("personal_data.php")
    Call<List<PersonalDataModel>> getPersonalData();

    @GET("academic_data.php")
    Call<List<AcademicDataModel>> getAcademicData();
}
