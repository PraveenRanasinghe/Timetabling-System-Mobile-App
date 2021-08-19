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
import com.example.my_timetable.Adapters.LecturerTimetableAdapter;
import com.example.my_timetable.Adapters.StudentTimetableAdapter;
import com.example.my_timetable.Model.Timetable;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class weekly_timetable_for_lecturer extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onStart() {
        super.onStart();

        recyclerView =findViewById(R.id.weeklyRecyclerViewLec);


        SharedPreferences prefs = getSharedPreferences("SHARED", Context.MODE_PRIVATE);
        String token = prefs.getString("token", null);
        String jwt = "Bearer " + token;

        Call<List<Timetable>> getTimetables = RetrofitAPI.getRetrofit().create(ApiCalls.class).getAllTimetablesToLecturer(jwt);

        getTimetables.enqueue(new Callback<List<Timetable>>() {
            @Override
            public void onResponse(Call<List<Timetable>> call, Response<List<Timetable>> response) {
                if(response.isSuccessful()){
                    List<Timetable> timetableList = response.body();
                    LecturerTimetableAdapter adapter = new LecturerTimetableAdapter(timetableList);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(weekly_timetable_for_lecturer.this));

                    System.out.println(adapter.getItemCount());

                }
            }

            @Override
            public void onFailure(Call<List<Timetable>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Operation Failed!"+t, Toast.LENGTH_LONG).show();
                System.out.println(t);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_timetable_for_lecturer);
    }
}