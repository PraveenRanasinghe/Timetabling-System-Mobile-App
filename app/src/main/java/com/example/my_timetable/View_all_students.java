package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.my_timetable.API.ApiCalls;
import com.example.my_timetable.API.RetrofitAPI;
import com.example.my_timetable.Adapters.ViewStudentsAdapter;
import com.example.my_timetable.Model.UDto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class View_all_students extends AppCompatActivity {


    private RecyclerView recyclerView;


    @Override
    protected void onStart() {
        super.onStart();

        recyclerView =findViewById(R.id.viewAllStudentsRecyclerView);


        SharedPreferences prefs = getSharedPreferences("SHARED", Context.MODE_PRIVATE);
        String name = prefs.getString("token", null);
        String jwt = "Bearer " + name;

        Call<List<UDto>> getUsers = RetrofitAPI.getRetrofit().create(ApiCalls.class).getAllStudents(jwt);

        getUsers.enqueue(new Callback<List<UDto>>() {
            @Override
            public void onResponse(Call<List<UDto>> call, Response<List<UDto>> response) {
                if(response.isSuccessful()){
                    List<UDto> user = response.body();
                    ViewStudentsAdapter adapter = new ViewStudentsAdapter(user);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(View_all_students.this));

                }
            }

            @Override
            public void onFailure(Call<List<UDto>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Operation Failed ! "+t, Toast.LENGTH_LONG).show();
                System.out.println(t);
            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_students);

        FloatingActionButton fab=findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(View_all_students.this, Admin.class);
                startActivity(intent);
            }
        });
    }
}