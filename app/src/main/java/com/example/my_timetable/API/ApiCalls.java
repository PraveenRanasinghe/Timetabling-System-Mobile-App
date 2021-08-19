package com.example.my_timetable.API;

import com.example.my_timetable.Model.JwtRequest;
import com.example.my_timetable.Model.JwtResponse;
import com.example.my_timetable.Model.Timetable;
import com.example.my_timetable.Model.User;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiCalls {

    @GET("/todayLecturersForStudent")
    Call<List<Timetable>> getTodayTimetableToStudent(@Header("Authorization") String authorization);

    @GET("/allTimetablesForStudent")
    Call<List<Timetable>> getAllTimetablesToStudent();

    @GET("/todayLecturersForLecturer")
    Call<List<Timetable>> getTodayTimetableToLecturer();

    @GET("/allTimetablesForLecturer")
    Call<List<Timetable>> getAllTimetablesToLecturer();

    @POST("/mobileAuthentication")
    Call<JwtResponse> authenticateUser(@Body JwtRequest users);

}
