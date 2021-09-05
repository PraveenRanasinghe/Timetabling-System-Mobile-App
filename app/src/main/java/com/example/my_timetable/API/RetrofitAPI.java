package com.example.my_timetable.API;

import com.example.my_timetable.MainActivity;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitAPI {


    public static Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.12:8080/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return retrofit;
    }

}
