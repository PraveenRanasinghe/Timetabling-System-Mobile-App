package com.example.my_timetable.API;
import com.example.my_timetable.MainActivity;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitAPI {


    public retrofit2.Retrofit getRetrofit() {
        return retrofit;
    }

    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request newRequest  = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer ")
                    .build();
            return chain.proceed(newRequest);
        }
    }).build();

    retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
            .baseUrl("http://192.168.1.12:8080/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

}
