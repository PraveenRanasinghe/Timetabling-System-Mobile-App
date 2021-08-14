package com.example.my_timetable.API;

import com.example.my_timetable.Model.Timetable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiCalls {

    @GET("viewStudentHome")
    Call<List<Timetable>> getTodayTimetableToStudent();


}
