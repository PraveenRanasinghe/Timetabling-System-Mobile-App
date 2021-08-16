package com.example.my_timetable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_timetable.API.ApiCalls;
import com.example.my_timetable.Model.Timetable;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class student extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private CardView cardView;
    private TextView moduleName;
    private TextView scheduledDate;
    private TextView startTime;
    private TextView endTime;
    private TextView classRoomId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);



        drawerLayout = findViewById(R.id.drawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        recyclerView =findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        cardView = findViewById(R.id.studentCardView);
        moduleName = findViewById(R.id.moduleName);
        scheduledDate= findViewById(R.id.scheduledDate);
        startTime =findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);
        classRoomId = findViewById(R.id.classRoomId);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiCalls apiCalls = retrofit.create(ApiCalls.class);
        Call<List<Timetable>> listCall = apiCalls.getTodayTimetableToStudent();
        listCall.enqueue(new Callback<List<Timetable>>() {
            @Override
            public void onResponse(Call<List<Timetable>> call, Response<List<Timetable>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Operation Failed !", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Timetable> timetables = response.body();

                for(Timetable timetable:timetables){
                    moduleName.setText(timetable.getModule().getModuleName());
                    scheduledDate.setText(timetable.getScheduledDate().toString());
                    startTime.setText(timetable.getStartTime().toString());
                    endTime.setText(timetable.getEndTime().toString());
                    classRoomId.setText(timetable.getClassRoom().getClassRoomID());
                }
            }

            @Override
            public void onFailure(Call<List<Timetable>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Operation Failed! "+t, Toast.LENGTH_SHORT).show();
            }
        });

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
                        Intent intent = new Intent(student.this, student.class);
                        startActivity(intent);
                        return true;

                    case R.id.myAccount:
                        Intent myAccountIntent = new Intent(student.this, my_account.class);
                        startActivity(myAccountIntent);
                        return true;
                    case R.id.WeeklyTimetable:
                        Intent weeklyTimetable = new Intent(student.this,weekly_timetable.class);
                        startActivity(weeklyTimetable);
                        return true;

                }
                return false;
            }
        });
    }
}