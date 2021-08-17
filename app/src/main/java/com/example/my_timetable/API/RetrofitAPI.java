package com.example.my_timetable.API;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAPI {

    public retrofit2.Retrofit getRetrofit() {
        return retrofit;
    }

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
            .baseUrl("http://192.168.1.11:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
}
