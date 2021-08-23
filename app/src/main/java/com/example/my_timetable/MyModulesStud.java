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
import com.example.my_timetable.Adapters.ViewBatchModulesAdapter;
import com.example.my_timetable.Adapters.ViewLecturerModulesAdapter;
import com.example.my_timetable.Model.Module;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyModulesStud extends AppCompatActivity {


    private RecyclerView recyclerView;


    @Override
    protected void onStart() {
        super.onStart();

        recyclerView =findViewById(R.id.viewMyModulesStudRecyclerView);


        SharedPreferences prefs = getSharedPreferences("SHARED", Context.MODE_PRIVATE);
        String name = prefs.getString("token", null);
        String jwt = "Bearer " + name;

        Call<List<Module>> getModules = RetrofitAPI.getRetrofit().create(ApiCalls.class).getBatchModules(jwt);

        getModules.enqueue(new Callback<List<Module>>() {
            @Override
            public void onResponse(Call<List<Module>> call, Response<List<Module>> response) {
                if(response.isSuccessful()){
                    List<Module> moduleList = response.body();
                    ViewBatchModulesAdapter adapter = new ViewBatchModulesAdapter(moduleList);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MyModulesStud.this));
                }
            }

            @Override
            public void onFailure(Call<List<Module>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Operation Failed ! "+t, Toast.LENGTH_LONG).show();
                System.out.println(t);
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_modules_stud);
    }
}