package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.my_timetable.API.ApiCalls;
import com.example.my_timetable.API.RetrofitAPI;
import com.example.my_timetable.Adapters.AdminTimetableAdapter;
import com.example.my_timetable.Adapters.ViewStudentsAdapter;
import com.example.my_timetable.Model.TimetableDTO;
import com.example.my_timetable.Model.UDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAllScheduledClasses extends AppCompatActivity {


    private RecyclerView recyclerView;


    @Override
    protected void onStart() {
        super.onStart();

        recyclerView =findViewById(R.id.viewAllTimetablesRecyclerView);


        SharedPreferences prefs = getSharedPreferences("SHARED", Context.MODE_PRIVATE);
        String name = prefs.getString("token", null);
        String jwt = "Bearer " + name;

        Call<List<TimetableDTO>> getUsers = RetrofitAPI.getRetrofit().create(ApiCalls.class).getAllTimetablesToAdmin(jwt);

        getUsers.enqueue(new Callback<List<TimetableDTO>>() {
            @Override
            public void onResponse(Call<List<TimetableDTO>> call, Response<List<TimetableDTO>> response) {
                if(response.isSuccessful()){
                    List<TimetableDTO> user = response.body();
                    AdminTimetableAdapter adapter = new AdminTimetableAdapter(user);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ViewAllScheduledClasses.this));

                }
            }

            @Override
            public void onFailure(Call<List<TimetableDTO>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Operation Failed ! "+t, Toast.LENGTH_LONG).show();
                System.out.println(t);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_scheduled_classes);
    }
}