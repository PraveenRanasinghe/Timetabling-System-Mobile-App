package com.example.my_timetable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.my_timetable.API.ApiCalls;
import com.example.my_timetable.API.RetrofitAPI;
import com.example.my_timetable.Adapters.LecturerTimetableAdapter;
import com.example.my_timetable.Model.Timetable;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Lecturer extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RecyclerView recyclerView;


    @Override
    protected void onStart() {
        super.onStart();

        recyclerView =findViewById(R.id.lecRecyclerView);

        SharedPreferences prefs = getSharedPreferences("SHARED", Context.MODE_PRIVATE);
        String name = prefs.getString("token", null);
        String jwt = "Bearer " + name;

        Call<List<Timetable>> getTimetables = RetrofitAPI.getRetrofit().create(ApiCalls.class).getTodayTimetableToLecturer(jwt);

        getTimetables.enqueue(new Callback<List<Timetable>>() {
            @Override
            public void onResponse(Call<List<Timetable>> call, Response<List<Timetable>> response) {
                if(response.isSuccessful()){
                    List<Timetable> timetableList = response.body();
                    LecturerTimetableAdapter adapter = new LecturerTimetableAdapter(timetableList);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Lecturer.this));

                    System.out.println(adapter.getItemCount());

                }
            }

            @Override
            public void onFailure(Call<List<Timetable>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Operation Failed ! "+t, Toast.LENGTH_LONG).show();
                System.out.println(t);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer);

        drawerLayout = findViewById(R.id.lecDrawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.o, R.string.c
        );

        toggle.syncState();

        drawerLayout.addDrawerListener(toggle);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                switch (item.getItemId()) {
                    case R.id.home:
                        Intent intent = new Intent(Lecturer.this, Lecturer.class);
                        startActivity(intent);
                        return true;

                    case R.id.myAccount:
                        Intent myAccountIntent = new Intent(Lecturer.this, My_account_staff.class);
                        startActivity(myAccountIntent);
                        return true;

                    case R.id.myModules:
                        Intent myModules = new Intent(Lecturer.this, MyModulesLec.class);
                        startActivity(myModules);
                        return true;

                    case R.id.WeeklyTimetable:
                        Intent weeklyTimetable = new Intent(Lecturer.this, Weekly_timetable_for_lecturer.class);
                        startActivity(weeklyTimetable);
                        return true;

                    case R.id.nav_signout:{
                        SharedPreferences preferences =getSharedPreferences("SHARED", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear();
                        editor.apply();
                        finish();
                        Intent i = new Intent(Lecturer.this, MainActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(), "You logged out from the System Successfully!", Toast.LENGTH_LONG).show();
                        break;}

                }
                return false;
            }
        });
    }
}