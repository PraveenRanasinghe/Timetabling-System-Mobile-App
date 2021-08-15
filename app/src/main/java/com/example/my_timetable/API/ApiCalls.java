package com.example.my_timetable.API;

import com.example.my_timetable.Model.JwtResponse;
import com.example.my_timetable.Model.Timetable;
import com.example.my_timetable.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiCalls {

    @GET("viewStudentHome")
    Call<List<Timetable>> getTodayTimetableToStudent();

    @GET("lecturerHome")
    Call<List<Timetable>> getTodayTimetableToLecturer();

    @POST("/mobileAuthentication")
    Call<JwtResponse> authenticateUser(@Body User users);

}
