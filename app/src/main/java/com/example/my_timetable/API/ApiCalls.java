package com.example.my_timetable.API;

import com.example.my_timetable.Model.Batch;
import com.example.my_timetable.Model.Classroom;
import com.example.my_timetable.Model.DtoUser;
import com.example.my_timetable.Model.JwtRequest;
import com.example.my_timetable.Model.JwtResponse;
import com.example.my_timetable.Model.Module;
import com.example.my_timetable.Model.Timetable;
import com.example.my_timetable.Model.TimetableDTO;
import com.example.my_timetable.Model.UDto;
import com.example.my_timetable.Model.User;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiCalls {


    @POST("/mobileAuthentication")
    Call<JwtResponse> authenticateUser(@Body JwtRequest users);

    @GET("/getStudentAccount")
    Call<DtoUser> getMyAccount(@Header("Authorization") String authorization);

    @GET("/getAllLecturers")
    Call<List<UDto>> getLecturersToList(@Header("Authorization")String authorization);

    @GET("/getAllLecturers")
    Call<List<Classroom>> getAllClassesToList(@Header("Authorization")String authorization);

    @GET("/getAllTimetablesForAdmin")
    Call<List<TimetableDTO>> getAllTimetablesToAdmin(@Header("Authorization")String authorization);

    @GET("/myModules")
    Call<List<Module>> getMyModulesToLec(@Header("Authorization") String authorization);

    @GET("/batchModules")
    Call<List<Module>> getBatchModules(@Header("Authorization") String authorization);

    @GET("/todayLecturersForStudent")
    Call<List<Timetable>> getTodayTimetableToStudent(@Header("Authorization") String authorization);

    @GET("/allTimetablesForStudent")
    Call<List<Timetable>> getAllTimetablesToStudent(@Header("Authorization") String authorization);

    @GET("/todayLecturersForLecturer")
    Call<List<Timetable>> getTodayTimetableToLecturer(@Header("Authorization") String authorization);

    @GET("/allTimetablesForLecturer")
    Call<List<Timetable>> getAllTimetablesToLecturer(@Header("Authorization") String authorization);

    @GET("/viewAllStudents")
    Call<List<UDto>> getAllStudents(@Header("Authorization") String authorization);

    @GET("/viewAllLecturers")
    Call<List<User>> getAllLecturers(@Header("Authorization") String authorization);

    @GET("/viewAllModules")
    Call<List<Module>> getAllModules(@Header("Authorization") String authorization);

    @GET("/viewAllBatches")
    Call<List<Batch>> getAllBatches(@Header("Authorization") String authorization);

    @GET("/viewAllClassRooms")
    Call<List<Classroom>> getAllClassRooms(@Header("Authorization") String authorization);

    @POST("/addStudents")
    Call<User> addStudents(@Header("Authorization") String authorization,@Body User user);

    @POST("/addLecturers")
    Call<User> addLecturers(@Header("Authorization") String authorization,@Body User user);

    @POST("/addClassrooms")
    Call<Classroom> addClassroom(@Header("Authorization") String authorization,@Body Classroom classroom);

    @POST("/addBatch")
    Call<Batch> addBatch(@Header("Authorization") String authorization,@Body Batch batch);

    @POST("/addModule")
    Call<Module> addModule(@Header("Authorization") String authorization,@Body Module module);

    @POST("/scheduleClasses")
    Call<Timetable> scheduleClasses(@Header("Authorization") String authorization, @Body Timetable timetable);


    @POST("/UpdateAccount")
    Call<DtoUser> updateAdminAccount(@Header("Authorization") String authorization,@Body DtoUser user);



}
